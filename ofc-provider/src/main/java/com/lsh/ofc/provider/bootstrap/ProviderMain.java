package com.lsh.ofc.provider.bootstrap;

public class ProviderMain {

    public static void main(String[] args) {
        // 第一个参数用来标记程序名称
        String[] argAry = null;
        if (args != null && args.length > 1) {
            argAry = new String[args.length - 1];
            System.arraycopy(args, 1, argAry, 0, args.length - 1);
        }
        com.alibaba.dubbo.container.Main.main(argAry);
    }
}
