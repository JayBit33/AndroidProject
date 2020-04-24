package com.example.mockfragmenttest

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApplicationUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun applicationsDTO_maintainsAppInfo() {
        var app = Applications("facebook", "face@mail.com")
        assertTrue(app.app.equals("facebook"))
        assertTrue(app.userName.equals("face@mail.com"))
    }

    @Test
    fun applicationsDTO_maintainsAppInfo2() {
        var app = Applications("twitter", "twitter@mail.com")
        assertTrue(app.app.equals("twitter"))
        assertTrue(app.userName.equals("twitter@mail.com"))
    }
}
