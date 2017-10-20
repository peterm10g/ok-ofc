package com.lsh.ofc.core.handler;

import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.service.OfcTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by panxudong on 17/3/30.
 */
@Component
public class AsyncTaskHandler {

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskHandler.class);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    @Autowired
    private OfcTaskService ofcTaskService;

    public void buildAsyncTask(OfcSoHead ofcSoHead, OfcTaskType ofcTaskType) {
        List<OfcSoHead> list = new ArrayList<>();
        list.add(ofcSoHead);
        executor.submit(new AsyncTask(list, ofcTaskType));
    }

    public void buildAsyncTask(List<OfcSoHead> ofcSoHeadList, OfcTaskType ofcTaskType) {
        executor.submit(new AsyncTask(ofcSoHeadList, ofcTaskType));
    }

    class AsyncTask implements Runnable {
        private List<OfcSoHead> list;
        private OfcTaskType ofcTaskType;

        public AsyncTask(List<OfcSoHead> list, OfcTaskType ofcTaskType) {
            this.list = list;
            this.ofcTaskType = ofcTaskType;
        }

        private OfcTask buildOfcTask(OfcSoHead ofcSoHead) {
            OfcTask task = new OfcTask();
            task.setRefId(ofcSoHead.getOrderCode());
            task.setType(this.ofcTaskType.getValue());
            task.setStatus(OfcTaskStatus.NEW.getValue());
            task.setContent(ofcSoHead.getSoBillCode());
            return task;
        }

        @Override
        public void run() {
            logger.info("[AsyncTask][Async execute task start][Size:" + list.size() + "]");
            String beforeMsg = "[AsyncTask][Execute add " + this.ofcTaskType.getDesc() + "]";
            for (OfcSoHead ofcSoHead : this.list) {
                String msg = beforeMsg + "[" + ofcSoHead.getSoBillCode() + "]";

                logger.info(msg + "[start]");
                OfcTask ofcTask = this.buildOfcTask(ofcSoHead);
                ofcTaskService.addTask(ofcTask);
                logger.info(msg + "[end]");
            }
            logger.info("[AsyncTask][Async execute task end]");
        }
    }
}
