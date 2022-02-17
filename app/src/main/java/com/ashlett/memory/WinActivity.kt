package com.ashlett.memory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashlett.memory.databinding.ActivityWinBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
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

        val binding: ActivityWinBinding = ActivityWinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            konfetti.start(party)
            btnPlayAgain.setOnClickListener {
                startActivity(GameActivity.createIntent(this@WinActivity))
            }
        }
    }

    companion object {
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, WinActivity::class.java)
    }
}
