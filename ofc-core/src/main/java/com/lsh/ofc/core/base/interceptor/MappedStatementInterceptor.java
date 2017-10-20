package com.lsh.ofc.core.base.interceptor;

import com.lsh.ofc.core.base.AbstractTask;
import com.lsh.ofc.core.base.SessionId;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangdong on 16/11/28.
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MappedStatementInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(MappedStatementInterceptor.class);

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object ret = invocation.proceed();
            this.executor.submit(new LogTask(invocation, start, System.currentTimeMillis()));
            return ret;
        } catch (Throwable t) {
            this.executor.submit(new LogTask(invocation, start, System.currentTimeMillis(), t));
            throw t;
        }
    }

    @Override
    public Object plugin(Object obj) {
        return Plugin.wrap(obj, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private static class LogTask extends AbstractTask<Boolean> {

        private static final String SEPARATOR = "\1\t";

        private static final String DATE_FORMAT = "0000-00-00 00:00:00.000";

        private static final char SPACE = ' ';

        private static final char SINGLE_QUOTE = '\'';

        private static final char INTERROGATION = '?';

        private static final ThreadLocal<Calendar> CALENDAR = new ThreadLocal<Calendar>() {
            @Override
            protected Calendar initialValue() {
                return Calendar.getInstance();
            }
        };

        private final String sessionId;

        private final Invocation invocation;

        private final long startTime;

        private final long endTime;

        private final Throwable throwable;

        public LogTask(final Invocation invocation, final long startTime, final long endTime) {
            this(invocation, startTime, endTime, null);
        }

        public LogTask(final Invocation invocation, final long startTime, final long endTime, final Throwable throwable) {
            this.invocation = invocation;
            this.startTime = startTime;
            this.endTime = endTime;
            this.throwable = throwable;
            this.sessionId = SessionId.get();
        }

        @Override
        public Boolean execute() throws Exception {
            SessionId.set(this.sessionId);
            Object[] args = this.invocation.getArgs();
            if (ArrayUtils.isEmpty(args)) {
                return false;
            }
            Object arg0 = args[0];
            if (arg0 == null || !(arg0 instanceof MappedStatement)) {
                return false;
            }
            MappedStatement mappedStatement = (MappedStatement) arg0;
            Object parameter = null;
            if (args.length > 1) {
                parameter = args[1];
            }

            String sqlId = mappedStatement.getId();
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Configuration configuration = mappedStatement.getConfiguration();

            StringBuilder builder = new StringBuilder("SQL日志==>[").append(sqlId).append("]");
            builder.append(SEPARATOR).append("SQL：").append(this.formatSql(configuration, boundSql));
            builder.append(SEPARATOR).append("时间：").append(this.formatDate(this.startTime));
            builder.append(SEPARATOR).append("耗时：").append(this.endTime - this.startTime).append("ms");
            if (this.throwable == null) {
                MappedStatementInterceptor.logger.info(builder.toString());
            } else {
                MappedStatementInterceptor.logger.error(builder.toString(), this.throwable);
            }
            return true;
        }

        private String formatSql(Configuration configuration, BoundSql boundSql) {
            char[] chars = boundSql.getSql().toCharArray();
            List<char[]> params = this.getParamsAsCharArray(configuration, boundSql);
            int size = 0;
            for (char[] param : params) {
                if (param == null) {
                    continue;
                }
                size += param.length;
            }
            char[] array = new char[chars.length + size];
            int i = 0;
            Iterator<char[]> it = params.iterator();
            for (char ch : chars) {
                if (ch == INTERROGATION && it.hasNext()) {
                    char[] param = it.next();
                    int length = param.length;
                    System.arraycopy(param, 0, array, i, length);
                    i += length;
                } else if (ch > SPACE) {
                    array[i++] = ch;
                } else if (i > 0 && array[i - 1] != SPACE) {
                    array[i++] = SPACE;
                }
            }
            return new String(Arrays.copyOf(array, i + 1));
        }

        private List<char[]> getParamsAsCharArray(Configuration configuration, BoundSql boundSql) {
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject != null) {
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    return Collections.singletonList(this.toCharArray(parameterObject));
                }
            }
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (!CollectionUtils.isEmpty(parameterMappings)) {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                List<char[]> list = new ArrayList<>(parameterMappings.size());
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        list.add(this.toCharArray(metaObject.getValue(propertyName)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        list.add(this.toCharArray(boundSql.getAdditionalParameter(propertyName)));
                    }
                }
                return list;
            }
            return Collections.emptyList();
        }

        private char[] toCharArray(Object obj) {
            char[] chars;
            if (obj == null) {
                return null;
            } else if (obj instanceof Date) {
                chars = formatDate((Date) obj);
            } else if (obj instanceof String) {
                chars = obj.toString().toCharArray();
            } else {
                return obj.toString().toCharArray();
            }
            char[] ret = new char[chars.length + 2];
            System.arraycopy(chars, 0, ret, 1, chars.length);
            ret[0] = SINGLE_QUOTE;
            ret[ret.length - 1] = SINGLE_QUOTE;
            return ret;
        }

        private char[] formatDate(long ts) {
            Calendar cal = CALENDAR.get();
            cal.setTimeInMillis(ts);
            return formatDate(cal);
        }

        private char[] formatDate(Date date) {
            Calendar cal = CALENDAR.get();
            cal.setTime(date);
            return formatDate(cal);
        }

        private char[] formatDate(Calendar cal) {
            char[] chars = DATE_FORMAT.toCharArray();
            this.getChars(chars, 0, 3, cal.get(Calendar.YEAR));
            this.getChars(chars, 5, 6, cal.get(Calendar.MONTH) + 1);
            this.getChars(chars, 8, 9, cal.get(Calendar.DATE));
            this.getChars(chars, 11, 12, cal.get(Calendar.HOUR_OF_DAY));
            this.getChars(chars, 14, 15, cal.get(Calendar.MINUTE));
            this.getChars(chars, 17, 18, cal.get(Calendar.SECOND));
            this.getChars(chars, 20, 22, cal.get(Calendar.MILLISECOND));
            return chars;
        }

        private void getChars(char[] chars, int s, int e, int v) {
            for (int i = e; i >= s; i--, v /= 10) {
                chars[i] = (char) ((v % 10) + 48);
            }
        }
    }
}
