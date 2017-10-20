package com.lsh.ofc.core.service;

import com.lsh.ofc.core.entity.OfcOperateLog;
import com.lsh.ofc.core.enums.BillType;
import com.lsh.ofc.core.enums.OfcOperateEnum;

import java.util.List;

/**
 * OFC操作日志Service
 * Created by huangdong on 16/11/1.
 */
public interface OfcOperateLogService {

    /**
     * 插入操作日志
     *
     * @param billCode
     * @param billType
     * @param operate
     * @param remark
     */
    void insert(String billCode, BillType billType, OfcOperateEnum operate, String remark);

    /**
     * 根据类型获取任务
     *
     * @param billType
     * @param offsetId
     * @param fetchSize
     * @return
     */
    List<OfcOperateLog> fetchLogs(BillType billType, long offsetId, int fetchSize);
}
