package com.example.careplus;

import com.example.careplus.PMS.Pms_bmi_calculator;
import com.example.careplus.mms.Mms_supplementManager;

import org.junit.Before;
import com.example.careplus.prms.Prms_separate_methods;

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
    private Mms_supplementManager supplement_manger;
    private Prms_separate_methods prms_separate_methods;

    @Before
    public void setUp(){

        supplement_manger = new Mms_supplementManager();
        prms_separate_methods = new Prms_separate_methods();
        bmi_Test = new Pms_bmi_calculator();


    }

    @Test
    public void testBmiResult(){
        double res = bmi_Test.calculateBMI(150, 40);
        System.out.println(res);
        assertEquals(17.8 , res , 0.001);

    }

    @Test
    public void testBmiCategory(){
        String res = bmi_Test.findBmiCategory(17.5);
        String expectedResult = "Underweight";
        assertEquals(expectedResult , res);
    }

    @Test
    public void supplementName_isCorrect(){

        String result = supplement_manger.showSupplementName("sp002");
        assertEquals("Vitamin B",result);
    }

    /*prms test method*/
    @Test
    public void testAgeCalculator(){
        String age = prms_separate_methods.calculate_age("02/18/1999" , "2021");
        assertEquals("22" , age);
    }

    @Test
    public void testGiveCorrectErrorsAgeCall(){
        String age = prms_separate_methods.calculate_age("02/18/199a" , "2021");
        assertEquals("-2" , age);
    }

}
