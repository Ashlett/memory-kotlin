package com.ashlett.memory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashlett.memory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnPlay.setOnClickListener {
                startActivity(GameActivity.createIntent(this@MainActivity))
            }
        }
    }
}
