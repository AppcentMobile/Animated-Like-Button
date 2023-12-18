package com.appcent.AnimatedLikeButton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appcent.AnimatedLikeButton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            bttnLike.setOnClickListener {
                bttnLike.toggleUIState()
            }

            bttnCelebrate.setOnClickListener {
                bttnCelebrate.toggleUIState()
            }

            bttnChecked.setOnClickListener {
                bttnChecked.toggleUIState()
            }

            bttnWave.setOnClickListener {
                bttnWave.toggleUIState()
            }

            bttnSmile.setOnClickListener {
                bttnSmile.toggleUIState()
            }
        }
    }
}