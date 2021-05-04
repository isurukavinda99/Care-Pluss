package com.example.careplus;

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

    private Prms_separate_methods prms_separate_methods;

    @Before
    public void setUp(){
        prms_separate_methods = new Prms_separate_methods();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
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