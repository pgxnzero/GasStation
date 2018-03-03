package com.binder.gasstation;

import junit.framework.TestCase;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.JVM)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GasTankTest extends TestCase {

    GasTank myGasTank;

    @BeforeAll
    public void setUp() {
        this.myGasTank = new GasTank(TestData.VOLUME_LOWER,
                TestData.MAX_PRESSURE, 0.0);
    }

    @AfterAll
    protected void tearDown() {
        this.myGasTank = null;
    }

    @Test
    public void testSetup() {
        assertEquals(TestData.VOLUME_LOWER, this.myGasTank.getMaxVolume());
        assertEquals(TestData.MAX_PRESSURE, this.myGasTank.getMaxPressure());
        assertEquals(0.0, this.myGasTank.getCurrPressure());
        assertEquals(0.0, this.myGasTank.getCurrNm());
        assertEquals(TestData.VOLUME_LOWER * TestData.MAX_PRESSURE / Inflatable.ATM,
                this.myGasTank.getAvalNm());
        assertEquals(false, this.myGasTank.isFull());
    }

    @Test
    public void testChargeAndRelease() {
        //testCharge
        assertEquals(0.0, this.myGasTank.charge(this.myGasTank.getAvalNm()));
        assertEquals(TestData.MAX_PRESSURE, this.myGasTank.getCurrPressure());
        assertEquals(TestData.VOLUME_LOWER * TestData.MAX_PRESSURE / Inflatable.ATM,
                this.myGasTank.getCurrNm());
        assertNotEquals(0.0, this.myGasTank.charge(1.0));
        assertEquals(true, this.myGasTank.isFull());
        //testRelease
        this.myGasTank.release(this.myGasTank.getCurrNm() / 2);
        assertEquals(false, this.myGasTank.isFull());
        this.myGasTank.charge(-100000.0);
        assertEquals(0.0, this.myGasTank.getCurrPressure());
    }

}