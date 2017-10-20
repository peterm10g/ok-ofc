package com.lsh.ofc.core.service;

/**
 * OFC Elasticsearch Service
 * Created by panxudong on 16/12/06.
 */
public interface EsService {

    String selectIndex(String query, Boolean isCount) throws Exception;

    <T> Boolean createIndexSync(T index) throws Exception;

    <T> Boolean editIndexSync(T index) throws Exception;

    <T> Boolean saveOrUpdateIndexSync(T index) throws Exception;

}
