package com.appcent.AnimatedLikeButton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appcent.AnimatedLikeButton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bttnLike.setOnClickListener {
            binding.bttnLike.toggleUIState()
        }

        binding.bttnCelebrate.setOnClickListener {
            binding.bttnCelebrate.toggleUIState()
        }

        binding.bttnChecked.setOnClickListener {
            binding.bttnChecked.toggleUIState()
        }

        binding.bttnWave.setOnClickListener {
            binding.bttnWave.toggleUIState()
        }

        binding.bttnSmile.setOnClickListener {
            binding.bttnSmile.toggleUIState()
        }

    }
}