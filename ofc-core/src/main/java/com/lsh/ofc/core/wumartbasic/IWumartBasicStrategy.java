package com.lsh.ofc.core.wumartbasic;

import com.lsh.ofc.proxy.context.WumartBasicContext;

/**
 * Created by panxudong on 17/7/27.
 */
public interface IWumartBasicStrategy {

    boolean validate(WumartBasicContext context);

    String getConfigValue(WumartBasicContext context);

}
