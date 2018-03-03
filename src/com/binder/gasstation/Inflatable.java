package com.binder.gasstation;

/**
 * 定义“可充气容器”的接口
 * @author : pgxn E-mail:pgxn@qq.com
 * @version : Create in 12:06 2018/2/21
 **/
interface Inflatable {

    /** 定义标准状态下的大气压力，用于换算标准立方米，单位兆帕（MPaA） */
    double ATM = 0.1;

    /**
     * @return 容器的最大容积，单位：立方米（m3）
     **/
    double getMaxVolume();

    /**
     * @return 容器的设计压力/最大工作压力，单位：兆帕（MPaA）
     **/
    double getMaxPressure();

    /**
     * @return 容器的工作压力（当前的压力），单位：兆帕（MPaA）
     **/
    double getCurrPressure();

    /**
     * @return 容器内气体的量，单位：标准立方米（Nm3）
     **/
    double getCurrNm();

    /**
     * @return 容器还能够容纳的气体的量（即：剩余容量），单位：标准立方米（Nm3）
     **/
    double getAvalNm();

    /**
     * 用于向自身充气
     * @param gasIn: 充气的量，单位：标准立方米（Nm3）
     * @return 如果自身已被充满，则返回剩余的气量，否则返回值为0，单位：标准立方米（Nm3）
     **/
    double charge(double gasIn);

    /**
     * 用于向外放气
     * @param gasOut: 放气的量，单位：标准立方米（Nm3）
     **/
    void release(double gasOut);

    /**
     * @return true：已满，false：未满
     **/
    boolean isFull();

}
