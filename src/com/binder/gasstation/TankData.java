package com.binder.gasstation;

/**
 * @author : pgxn E-mail:pgxn@qq.com
 * @version : Create in 17:22 2018/2/21
 **/

public final class TankData {

    double maxVolume;
    double maxPressure;
    double minPressure;

    public TankData() {
        this.maxVolume = 0.0;
        this.maxPressure = 0.0;
        this.minPressure = 0.0;
    }

    public void setData(double maxVolume, double maxPressure, double minPressure) {
        this.maxVolume = maxVolume;
        this.maxPressure = maxPressure;
        this.minPressure = minPressure;
    }

}
