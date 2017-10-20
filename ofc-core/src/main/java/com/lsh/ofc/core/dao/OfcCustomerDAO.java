package com.lsh.ofc.core.dao;

import com.lsh.ofc.core.base.BaseDAO;
import com.lsh.ofc.core.entity.OfcCustomer;
import org.springframework.stereotype.Repository;

/**
 * OFC客户信息DAO
 * Created by huangdong on 16/10/1.
 */
@Repository
public interface OfcCustomerDAO extends BaseDAO<OfcCustomer, Long> {
}
