package com.lsh.ofc.core.base;

import java.util.concurrent.Callable;

public abstract class AbstractTask<V> implements Callable<V> {

    private final String sessionId;

    public AbstractTask() {
        this(true);
    }

    public AbstractTask(boolean extend) {
        if (extend) {
            this.sessionId = SessionId.get();
        } else {
            this.sessionId = null;
        }
    }

    @Override
    public final V call() throws Exception {
        try {
            if (this.sessionId == null) {
                SessionId.reset();
            } else {
                SessionId.set(this.sessionId);
            }
            return execute();
        } finally {
            SessionId.clear();
        }
    }

    protected abstract V execute() throws Exception;
}
