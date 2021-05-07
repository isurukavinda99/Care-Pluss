package com.example.careplus;

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

    @Before
    public void setUp(){

        supplement_manger = new Mms_supplementManager();


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

    @Test
    public void supplementName_isCorrect(){

        String result = supplement_manger.showSupplementName("sp002");
        assertEquals("Vitamin B",result);
    }

}
