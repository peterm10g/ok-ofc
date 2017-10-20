package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.base.AbstractTask;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.EsService;
import com.lsh.ofc.proxy.service.EsServiceProxy;
import com.lsh.oms.api.model.es.OfcSoInfo4ES;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class EsServiceImpl implements EsService {

    private static final Logger logger = Logger.getLogger(EsServiceImpl.class);

    @Autowired
    private EsServiceProxy esServiceProxy;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public static OfcSoInfo4ES getOfcSoInfo(OfcSoHead ofcSoHead, OfcObdHead ofcObdHead, String tuPrefix) {
        OfcSoInfo4ES ofcSoInfo = new OfcSoInfo4ES();

        ofcSoInfo.setSoBillCode(ofcSoHead.getSoBillCode());
        ofcSoInfo.setWarehouseCode(ofcSoHead.getWarehouseCode());
        ofcSoInfo.setOrderCode(ofcSoHead.getOrderCode().toString());
        ofcSoInfo.setSoCode(ofcSoHead.getSoCode());
        ofcSoInfo.setSoStatus(Long.valueOf(ofcSoHead.getSoStatus()));
        ofcSoInfo.setSkuOrderQty(ofcSoHead.getTotalSkuOrderQty());
        ofcSoInfo.setSkuSoQty(ofcSoHead.getTotalSkuSupplyQty());
        ofcSoInfo.setSkuObdQty(ofcSoHead.getTotalSkuDeliverQty());

        if (ofcObdHead != null) {
            ofcSoInfo.setObdCode(ofcObdHead.getObdCode());
            String ext = ofcObdHead.getExt();
            if (StringUtils.hasText(ext)) {
                JSONObject extObject = JSONObject.parseObject(ext);
                String tuCode = extObject.getString(Constants.OBD_H_WAYBILL_CODE);

                if (StringUtils.hasText(tuPrefix)) {
                    tuCode = tuPrefix + tuCode;
                }

                ofcSoInfo.setTuCode(tuCode);
            }
        }

        return ofcSoInfo;
    }

    @Override
    public String selectIndex(String query, Boolean isCount) throws Exception {
        return this.esServiceProxy.selectIndex(query, isCount);
    }

    @Override
    public <T> Boolean createIndexSync(T index) throws Exception {
        return this.createIndex(index, false);
    }

    @Override
    public <T> Boolean editIndexSync(T index) throws Exception {
        return this.editIndex(index, false);
    }

    @Override
    public <T> Boolean saveOrUpdateIndexSync(T index) throws Exception {
        return this.execute(index, false, EsOperateType.CREATE_OR_EDIT);
    }

    private <T> Boolean createIndex(T index, Boolean isAsync) throws Exception {
        return this.execute(index, isAsync, EsOperateType.CREATE);
    }

    private <T> Boolean editIndex(T index, Boolean isAsync) throws Exception {
        return this.execute(index, isAsync, EsOperateType.EDIT);
    }

    private <T> Boolean execute(T index, Boolean isAsync, EsOperateType esOperateType) throws Exception {
        if (index == null) {
            throw EC.ERROR.exception("请求参数为空");
        }

        String operateType = esOperateType.getDesc();

        logger.info(operateType + "index start, content:" + JSON.toJSONString(index));

        if (isAsync) {
            Future<Boolean> future = this.executor.submit(new EsServiceImpl().new OfcEsTask(index, esOperateType));
            try {
                if (!future.get()) {
                    logger.error(operateType + " index error");
                    return false;
                }
                return true;
            } catch (Exception e) {
                String msg = operateType + " index error,error message:" + e.getMessage();
                logger.error(msg, e);
                throw new BusinessException(EC.ERROR.getCode(), msg, e);
            }
        } else {
            try {
                return this.execute(index, esOperateType);
            } catch (Exception e) {
                String msg = operateType + " index error,error message:" + e.getMessage();
                logger.error(msg, e);
                throw e;
            }
        }
    }

    private Boolean execute(Object index, EsOperateType esOperateType) throws Exception {
        Boolean isSuccess;
        if (esOperateType == EsOperateType.CREATE) {
            isSuccess = esServiceProxy.createIndex(index);
        } else if (esOperateType == EsOperateType.EDIT) {
            isSuccess = esServiceProxy.editIndex(index);
        } else {
            isSuccess = esServiceProxy.saveOrUpdateIndex(index);
        }
        return isSuccess;
    }

    enum EsOperateType {
        CREATE(1, "Create"),
        EDIT(2, "Edit"),
        CREATE_OR_EDIT(3, "CreateOrEdit");

        private int index;
        private String desc;

        EsOperateType(int index, String desc) {
            this.index = index;
            this.desc = desc;
        }

        public int getIndex() {
            return index;
        }

        public String getDesc() {
            return desc;
        }
    }

    class OfcEsTask extends AbstractTask<Boolean> {
        private Object index;
        private EsOperateType esOperateType;

        public OfcEsTask(Object index, EsOperateType esOperateType) {
            this.index = index;
            this.esOperateType = esOperateType;
        }

        @Override
        protected Boolean execute() throws Exception {
            return EsServiceImpl.this.execute(index, esOperateType);
        }
    }

}
