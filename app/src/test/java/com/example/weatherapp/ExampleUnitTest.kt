package com.example.weatherapp

import com.example.weatherapp.ui.utils.toFormatDateSting
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun long_to_strDate(){

        val milSeg : Long =1625540400
        val str = milSeg.toFormatDateSting()
        assertEquals("Monday 19 Jan 1970, 02:32 PM",str)
    }
}