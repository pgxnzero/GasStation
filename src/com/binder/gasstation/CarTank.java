package com.binder.gasstation;

/**
 * 在GasTank的基础上，增加CarTank的属性。 主要包括最小压力（minPressure）的定义，并增加了reset方法。
 * @author : pgxn
 * @version : Create in 2018/02/21
 **/
class CarTank extends GasTank {

    private double minPressure;

    CarTank(double maxVolume, double maxPressure) {
        super(maxVolume, maxPressure);
        this.minPressure = 0.0;
    }

    CarTank(double maxVolume, double maxPressure, double minPressure) {
        this(maxVolume, maxPressure);
        this.minPressure = minPressure;
    }

    @Override
    public void release(double gasOut) {
        super.release(gasOut);
        if (this.currPressure < this.minPressure) {
            this.currPressure = this.minPressure;
        }
    }

    void reset() {
        this.release(this.getCurrNm());
    }

}
