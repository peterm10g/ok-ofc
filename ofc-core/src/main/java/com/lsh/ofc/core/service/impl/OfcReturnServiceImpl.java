package com.lsh.ofc.core.service.impl;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.dao.OfcReturnDetailDAO;
import com.lsh.ofc.core.dao.OfcReturnHeadDAO;
import com.lsh.ofc.core.entity.OfcReturnDetail;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.OfcOperateLogService;
import com.lsh.ofc.core.service.OfcReturnService;
import com.lsh.ofc.core.util.OFCUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class OfcReturnServiceImpl implements OfcReturnService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcReturnHeadDAO ofcReturnHeadDAO;

    @Autowired
    private OfcReturnDetailDAO ofcBillDetailDAO;

    @Autowired
    private OfcOperateLogService ofcOperateLogService;

    @Override
    public List<OfcReturnHead> findList(OfcReturnHead filter, boolean fillDetails) throws BusinessException {
        List<OfcReturnHead> bills = this.ofcReturnHeadDAO.findList(filter);
        if (CollectionUtils.isEmpty(bills)) {
            return Collections.emptyList();
        }
        if (fillDetails) {
            for (OfcReturnHead bill : bills) {
                OfcReturnDetail param = new OfcReturnDetail();
                param.setReturnCode(bill.getReturnCode());
                bill.setDetails(this.ofcBillDetailDAO.findList(param));
            }
        }
        return bills;
    }

    @Override
    public int count(OfcReturnHead filter) throws BusinessException {
        return this.ofcReturnHeadDAO.count(filter);
    }

    @Transactional
    @Override
    public void insert(OfcReturnHead order) throws BusinessException {
        if (order == null) {
            throw EC.ERROR.exception("入参订单信息为空！");
        }
        List<OfcReturnDetail> details = order.getDetails();
        if (CollectionUtils.isEmpty(details)) {
            throw EC.ERROR.exception("入参订单明细为空！");
        }

        int ts = OFCUtils.currentTime();
        order.setCreateTime(ts);
        order.setUpdateTime(ts);
        order.setValid(Valid.enable.getValue());
        this.ofcReturnHeadDAO.insert(order);
        for (OfcReturnDetail detail : details) {
            detail.setCreateTime(ts);
            detail.setUpdateTime(ts);
            this.ofcBillDetailDAO.insert(detail);
        }
        //记录操作日志
        this.ofcOperateLogService.insert(order.getReturnCode().toString(), BillType.RETURN, OfcOperateEnum.RETURN_NEW, null);
    }
}
