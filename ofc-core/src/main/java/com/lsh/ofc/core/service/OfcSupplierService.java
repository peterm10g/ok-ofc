package com.lsh.ofc.core.service;

import com.lsh.ofc.core.entity.OfcSupplier;

import java.util.List;

/**
 * OFC供货商Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcSupplierService {

    /**
     * 根据供货商编号获取OFC供货商信息
     *
     * @param code
     * @return
     */
    OfcSupplier getSupplierByCode(String code, Integer regionCode);

    /**
     * 获取所有Supplier信息
     *
     * @return
     */
    List<OfcSupplier> findList(OfcSupplier params);

}
