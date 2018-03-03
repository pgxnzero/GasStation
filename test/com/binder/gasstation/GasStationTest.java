package com.binder.gasstation;

import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runners.MethodSorters;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FixMethodOrder(MethodSorters.JVM)
class GasStationTest extends TestCase {

    TankData[] myTankList = new TankData[3];
    TankData myCarData = new TankData();

    @BeforeEach
    protected void setUp() {
        myTankList[0] = new TankData();
        myTankList[0].setData(TestData.VOLUME_LOWER, TestData.MAX_PRESSURE, 0.1);
        myTankList[1] = new TankData();
        myTankList[1].setData(TestData.VOLUME_MID, TestData.MAX_PRESSURE, 0.1);
        myTankList[2] = new TankData();
        myTankList[2].setData(TestData.VOLUME_HIGHER, TestData.MAX_PRESSURE, 0.1);
        myCarData.setData(TestData.CAR_TANK_VOLUME, TestData.CAR_TANK_MAX_PRESSURE, TestData.DIFF_PRESSURE);
    }

    @AfterEach
    protected void tearDown() {
    }

    @Test
    protected void testGetResult() {
        ResultData myResult = GasStation.getResult(myTankList, myCarData, TestData.DIFF_PRESSURE);
        System.out.println(myResult);
        assertEquals(true, myResult.count > 0);
        assertEquals(true, myResult.meter > 0.0);
        assertEquals(true, myResult.ratio > 1.0);
        assertEquals(true, myResult.ratio < 100.0);
    }
}