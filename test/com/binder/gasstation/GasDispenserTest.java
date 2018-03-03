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
class GasDispenserTest extends TestCase {

    GasDispenser myDispenser;
    TankData[] myTankList = new TankData[3];
    TankData myCarData;

    @BeforeEach
    protected void setUp() {
        myDispenser = new GasDispenser(TestData.DIFF_PRESSURE);
        myCarData = new TankData();
    }

    @AfterEach
    protected void tearDown() {
        myDispenser = null;
        myCarData = null;
    }

    @Test
    void testAutoFill() {
        myTankList[0] = new TankData();
        myTankList[0].setData(TestData.VOLUME_LOWER, TestData.MAX_PRESSURE, 0.1);
        myTankList[1] = new TankData();
        myTankList[1].setData(TestData.VOLUME_MID, TestData.MAX_PRESSURE, 0.1);
        myTankList[2] = new TankData();
        myTankList[2].setData(TestData.VOLUME_HIGHER, TestData.MAX_PRESSURE, 0.1);
        myCarData.setData(TestData.CAR_TANK_VOLUME, TestData.CAR_TANK_MAX_PRESSURE, TestData.DIFF_PRESSURE);
        Inflatable[] srcTanks = TankFactory.createTank(myTankList, true);
        CarTank myCar = TankFactory.createCar(myCarData);
        do {
            myCar.reset();
            myDispenser.autoFill(srcTanks, myCar);
        } while (myCar.isFull());
        assertEquals(true, srcTanks[2].getCurrPressure() > TestData.CAR_TANK_MAX_PRESSURE);
        assertEquals(true,
                srcTanks[2].getCurrPressure() < TestData.CAR_TANK_MAX_PRESSURE + TestData.DIFF_PRESSURE);
        assertEquals(true, srcTanks[1].getCurrPressure() < TestData.CAR_TANK_MAX_PRESSURE);
        assertEquals(true, srcTanks[0].getCurrPressure() < TestData.CAR_TANK_MAX_PRESSURE);
        assertEquals(true, myDispenser.getMeter() > 0.0);
        assertEquals(true, myDispenser.getCount() > 0);
        myDispenser.reset();
        assertEquals(0, myDispenser.getCount());
        assertEquals(0.0, myDispenser.getMeter());
    }

}