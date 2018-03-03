package com.binder.gasstation;

/**
 * 用于根据挂载GasTank的数量，自动向CarTank加气。
 * @author : pgxn
 * @version : Create in 2018/02/21
 **/
class GasDispenser implements IDispenser {

    private static final double ACCURACY = 0.001;

    private double tgtPressureDiff;
    private long count;
    private double meter;

    GasDispenser(double tgtPressureDiff) {
        this.tgtPressureDiff = tgtPressureDiff;
        this.count = 0;
        this.meter = 0.0;
    }

    private boolean basicFill(Inflatable srcTank, Inflatable tgtTank) {
        double P1 = srcTank.getCurrPressure();
        double V1 = srcTank.getMaxVolume();
        double P2 = tgtTank.getCurrPressure();
        double V2 = tgtTank.getMaxVolume();
        double Pd = this.tgtPressureDiff;
        double Px, gasIn, gasLeft;
        if ((P1 > P2 + Pd) & !tgtTank.isFull()) {
            Px = (P1 * V1 + P2 * V2 + Pd * V2) / (V1 + V2);
            gasIn = (P1 - Px) * V1 / Inflatable.ATM;
            gasLeft = tgtTank.charge(gasIn);
            srcTank.release(gasIn - gasLeft);
            if (gasIn - gasLeft > ACCURACY) {
                meter += (gasIn - gasLeft);
                //if (tgtTank.isFull()) {
                //    this.count += 1;
                //}
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean autoFill(Inflatable[] srcTanks, Inflatable tgtTank) {
        boolean isFilled = false;
        for (Inflatable srcTank : srcTanks) {
            isFilled = isFilled | basicFill(srcTank, tgtTank);
        }
        if (isFilled & tgtTank.isFull()) {
            this.count += 1;
        }
        return isFilled;
    }
    @Override
    public void reset() {
        this.count = 0;
        this.meter = 0.0;
    }
    @Override
    public long getCount() {
        return this.count;
    }

    @Override
    public double getMeter() {
        return this.meter;
    }


}
