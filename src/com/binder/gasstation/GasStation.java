package com.binder.gasstation;

/**
 * 用于进行加气过程个参数的研究，是整个包的汇总类和外部调用口
 * @author : pgxn E-mail:pgxn@qq.com
 * @version : Create in 13:50 2018/2/25
 **/
public class GasStation {

    /**
     * 用于得到在给定的加气参数下，能够取得什么样的结果
     * @param stationTanks: 站内储气罐的参数，格式必须符合TankData
     * @param carTank:      车辆燃料气罐的参数，格式必须符合TankData
     * @param diffPressure: 自动切换是的压差，单位：兆帕(MPaG)
     * @return : 返回加气运算的结果，格式符合ResultData
     **/
    public static ResultData getResult(TankData[] stationTanks, TankData carTank, double diffPressure) {
        final double ATM = 0.1;

        double totalNM = 0.0;

        GasDispenser myDispenser = new GasDispenser(diffPressure);

        for (TankData myTank : stationTanks) {
            totalNM += myTank.maxVolume * (myTank.maxPressure - ATM) / ATM;
        }
        Inflatable[] myStationTanks = TankFactory.createTank(stationTanks, true);
        CarTank myCar = TankFactory.createCar(carTank);
        do {
            myCar.reset();
            myDispenser.autoFill(myStationTanks, myCar);
        } while (myCar.isFull());
        return new ResultData(myDispenser.getCount(), myDispenser.getMeter(),
                myDispenser.getMeter() / totalNM * 100);
    }
}
