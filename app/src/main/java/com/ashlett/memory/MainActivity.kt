package com.ashlett.memory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton : Button = findViewById(R.id.btn_play)
        playButton.setOnClickListener {
            startActivity(GameActivity.createIntent(ctx = this))
        }
    }
}