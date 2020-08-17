package com.ikran.movieinfo

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainTest {

    /**
     * Hilt Rule Order -
     *  HiltAndroidRule runs first before any other rule:
     * @get:Rule(order = 0)
     *
     *  Rule order available from JUnit 4.13 and above
     */

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init(){
        hiltRule.inject()
    }


}