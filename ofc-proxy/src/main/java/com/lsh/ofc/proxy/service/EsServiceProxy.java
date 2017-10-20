package com.lsh.ofc.proxy.service;

import com.lsh.oms.api.service.es.EsSyncInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Es服务代理
 * Created by panxudong on 16/12/05.
 */
@Service
public class EsServiceProxy {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EsSyncInterface esSyncInterface;

    public <T> Boolean createIndex(T index) throws Exception {
        esSyncInterface.createIndex(index);
        return true;
    }

    public <T> Boolean editIndex(T index) throws Exception {
        esSyncInterface.editIndex(index);
        return true;
    }

    public <T> Boolean batchCreateIndex(List<T> indexList) throws Exception {
        esSyncInterface.batchCreateIndex(indexList);
        return true;
    }

    public String selectIndex(String query, Boolean isCount) {
        return esSyncInterface.selectOfcIndex(query, isCount);
    }

    public <T> Boolean saveOrUpdateIndex(T index) throws Exception {
        esSyncInterface.saveOrUpdateIndex(index);
        return true;
    }

}
