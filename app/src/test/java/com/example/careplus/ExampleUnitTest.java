package com.example.careplus;

import com.example.careplus.mms.Mms_supplementManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

    private Mms_supplementManager supplement_manger;

    @Before
    public void setUp(){

        supplement_manger = new Mms_supplementManager();
    }

    @Test
    public void supplementName_isCorrect(){

        String result = supplement_manger.showSupplementName("sp002");
        assertEquals("Vitamin B",result);
    }



    }