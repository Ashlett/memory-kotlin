package com.ashlett.memory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class WinActivity : AppCompatActivity() {

    private val party = Party(
        speed = 0f,
        maxSpeed = 30f,
        damping = 0.9f,
        spread = 360,
        colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
        emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
        position = Position.Relative(0.5, 0.3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val konfetti: KonfettiView = findViewById(R.id.konfetti)
        konfetti.start(party)

        val playButton : Button = findViewById(R.id.btn_play_again)
        playButton.setOnClickListener {
            startActivity(GameActivity.createIntent(ctx = this))
        }
    }

    companion object {
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, WinActivity::class.java)
    }
}
