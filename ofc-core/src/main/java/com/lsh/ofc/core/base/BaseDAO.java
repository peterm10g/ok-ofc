package com.lsh.ofc.core.base;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Base DAO
 * Created by huangdong on 16/8/28.
 */
public interface BaseDAO<T extends Serializable, ID extends Serializable> {

    int insert(T entity);

    int update(T entity);

    int updateByFilter(@Param("update") T entity, @Param("expect") T filter);

    int delete(ID id);

    int deleteByFilter(T filter);

    T get(ID id);

    T findOne(T params);

    List<T> findList(T params);

    int count(T params);
}
