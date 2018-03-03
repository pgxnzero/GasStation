package com.binder.gasstation;

/**
 * 定义加气机的接口标准。
 * @author : pgxn E-mail:pgxn@qq.com
 * @version : Create in 18:34 2018/2/21
 **/
interface IDispenser {

    /**
     * 在加气机完成初始化之后，用于向目标Tank自动加气。
     * @param srcTank: 站内的源储气罐对象，必须符合Inflatable接口标准。
     * @param tgtTank: 需要加气的储气罐对象，必须符合Inflatable接口标准。
     * @return true表示成功向tgtTank充气，false表示未向tgtTank充气（如：自身挂载的储气罐压力不足、tgtTank已满等）。
     **/
    boolean autoFill(Inflatable[] srcTank,Inflatable tgtTank);

    /**
     * 用于获取加气机的累计加气次数
     * @return 加气机的累计加气次数
     **/
    long getCount();

    /**
     * 用于获取加气机的累计加气量
     * @return 加气机的累计加气量，单位：标准立方米（Nm3）
     **/
    double getMeter();

    /**
     * 用于重置加气机（清零累计加气次数和加气量）
     **/
    void reset();
}
