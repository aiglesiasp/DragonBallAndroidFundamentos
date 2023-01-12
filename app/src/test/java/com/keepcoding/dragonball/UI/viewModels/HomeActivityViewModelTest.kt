package com.keepcoding.dragonball.UI.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun `WHEN selectedHeroesForBattle EXPECT Succes THEN return Boolean`() {
        //GIVEN
        sut = HomeActivityViewModel()
        //WHEN
        //val actual = sut.selectedHeroesForBattle()
        //THEN
    }
}