package com.keepcoding.dragonball

data class Hero (
    val id: String,
    val name: String,
    val photo: String,
    val maxLive: Int = 100,
    val currentLive: Int = 100
        )