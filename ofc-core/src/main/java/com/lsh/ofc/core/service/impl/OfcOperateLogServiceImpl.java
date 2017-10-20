package com.lsh.ofc.core.service.impl;

import com.lsh.ofc.core.dao.OfcOperateLogDAO;
import com.lsh.ofc.core.entity.OfcOperateLog;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.OfcOperateEnum;
import com.lsh.ofc.core.service.OfcOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfcOperateLogServiceImpl implements OfcOperateLogService {

    @Autowired
    private OfcOperateLogDAO ofcOperateLogDAO;

    @Override
    public void insert(String billCode, BillType billType, OfcOperateEnum operate, String remark) {
        OfcOperateLog log = new OfcOperateLog();
        log.setBillCode(billCode);
        log.setBillType(billType.name());
        log.setOperateType(operate.getType());
        log.setOperateDesc(operate.getDesc());
        this.ofcOperateLogDAO.insert(log);
    }

    @Override
    public List<OfcOperateLog> fetchLogs(BillType billType, long offsetId, int fetchSize) {
        return this.ofcOperateLogDAO.fetchLogs(billType.name(), offsetId, fetchSize);
    }
}
