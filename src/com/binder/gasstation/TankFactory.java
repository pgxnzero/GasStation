package com.binder.gasstation;

/**
 * 用于按照TankData中设定的参数生成一套Tank对象
 * @author : pgxn
 * @version : Create in 2018/02/21
 **/
abstract class TankFactory {

    /**
     * 用于按照TankData中设定的参数生成一套Tank对象
     * @return 返回一个Tank数组, 包括LowerTank、MidTank、HigherTank和CarTank
     **/
    static Inflatable[] createTank(TankData[] dataList, boolean isFilled) {
        Inflatable[] tankSet = new Inflatable[dataList.length];
        for (int i = 0; i < dataList.length; i++) {
            if (isFilled) {
                tankSet[i] = new GasTank(dataList[i].maxVolume, dataList[i].maxPressure, dataList[i].maxPressure);
            } else {
                tankSet[i] = new GasTank(dataList[i].maxVolume, dataList[i].maxPressure, dataList[i].minPressure);
            }
        }
        return tankSet;
    }

    static CarTank createCar(TankData myData) {
        return new CarTank(myData.maxVolume, myData.maxPressure, myData.minPressure);
    }
}
