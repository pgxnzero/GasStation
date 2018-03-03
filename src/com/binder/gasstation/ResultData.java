package com.binder.gasstation;

import java.text.DecimalFormat;

/**
 * 用于存放一批运算结果，以便按用户需要获取，避免再次进行运算
 * @author : pgxn E-mail:pgxn@qq.com
 * @version : Create in 19:52 2018/2/25
 **/
public final class ResultData {

    public long count;
    public double meter;
    public double ratio;

    public ResultData(long count, double meter, double ratio) {
        this.count = count;
        this.meter = meter;
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("###,##0.0");
        df.setDecimalSeparatorAlwaysShown(true);
        return "可加" + count + "车, 总加气量" + df.format(meter) +
                "标立, 储气罐利用率" + df.format(ratio) + "%";
    }
}
