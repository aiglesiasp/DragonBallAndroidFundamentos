package com.keepcoding.dragonball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.keepcoding.dragonball.databinding.ActivityHeroesListBinding

class HeroesListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHeroesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}