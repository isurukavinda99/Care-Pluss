package com.example.careplus;

import com.example.careplus.PMS.Pms_bmi_calculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    private Pms_bmi_calculator bmi_Test;

    @Before

    public void setUp(){

        bmi_Test = new Pms_bmi_calculator();
        bmi_Test.calculateBMI(150, 40);
        bmi_Test.findBmiCategory(17.5);

    }

    @Test

    public void testBmiResult(){

        double expectedResult = 17.5;

    }

    @Test
    public void testBmiCategory(){
        String expectedResult = "Underweight";
    }

}
