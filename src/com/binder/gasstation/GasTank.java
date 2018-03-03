package com.binder.gasstation;

/**
 * 用于创建符合ITank接口的储气罐对象。
 * @author : pgxn
 * @version : Create in 2018/02/21
 **/
public class GasTank implements Inflatable {

    private static final double ACCURACY = 0.001;

    private double maxVolume;
    private double maxPressure;
    double currPressure;

    GasTank(double maxVolume, double maxPressure) {
        this.maxVolume = maxVolume;
        this.maxPressure = maxPressure;
        this.currPressure = 0.0;
    }

    GasTank(double maxVolume, double maxPressure, double currPressure) {
        this(maxVolume, maxPressure);
        this.currPressure = currPressure;
    }

    @Override
    public double getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public double getMaxPressure() {
        return this.maxPressure;
    }

    @Override
    public double getCurrPressure() {
        return this.currPressure;
    }

    @Override
    public double getCurrNm() {
        return this.currPressure * maxVolume / Inflatable.ATM;
    }

    @Override
    public double getAvalNm() {
        return (this.maxPressure - this.currPressure) * maxVolume / Inflatable.ATM;
    }

    @Override
    public double charge(double gasIn) {
        if (gasIn > 0) {
            double tempPressure = (this.getCurrNm() + gasIn) * Inflatable.ATM / this.maxVolume;
            if (tempPressure > this.maxPressure) {
                this.currPressure = this.maxPressure;
                return (tempPressure - this.maxPressure) * this.maxVolume / Inflatable.ATM;
            } else {
                this.currPressure = tempPressure;
                return 0.0;
            }
        } else {
            this.release(-gasIn);
            return 0.0;
        }
    }

    @Override
    public void release(double gasOut) {
        if (gasOut > 0) {
            if (gasOut >= this.getCurrNm()) {
                this.currPressure = 0.0;
            } else {
                this.currPressure = (this.getCurrNm() - gasOut) * Inflatable.ATM / this.maxVolume;
            }
        }
    }

    @Override
    public boolean isFull() {
        return (this.maxPressure - this.currPressure <= ACCURACY);
    }

/*    @Override
    public String toString() {
        return "GasTank{}";
    }*/
}
