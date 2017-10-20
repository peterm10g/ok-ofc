package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcOrderDetail;
import org.springframework.stereotype.Repository;

/**
 * OFC订单明细DAO
 * Created by huangdong on 16/9/9.
 */
@Repository
public interface OfcOrderDetailDAO extends BaseDAO<OfcOrderDetail, Long> {
}
