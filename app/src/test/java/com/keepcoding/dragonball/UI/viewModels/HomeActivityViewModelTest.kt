package com.keepcoding.dragonball.UI.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.keepcoding.dragonball.Hero
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.max

internal class HomeActivityViewModelTest {
    //REGLAS
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    //SUT
    private lateinit var sut : HomeActivityViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    //------------------------------------------------------------------------------------//
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    //------------------------------------------------------------------------------------//
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }


    @Test
    fun `WHEN selectedHeroesforBattle EXPECT Succes THEN return Boolean`() {
        //GIVEN
        sut = HomeActivityViewModel()
        val hero = Hero(id = "", name = "", photo = "", maxLive = 100, currentLive = 100)
        //WHEN
        val actual = sut.selectedHeroesForBattle(hero)
        //THEN
        assert(actual)
    }


}