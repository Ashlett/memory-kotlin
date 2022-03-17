package com.ashlett.memory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashlett.memory.databinding.ActivityWinBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class WinActivity : AppCompatActivity() {

    private val party = Party(
        speed = SPEED,
        maxSpeed = MAX_SPEED,
        damping = DAMPING,
        spread = SPREAD,
        colors = listOf(YELLOW, RED, PINK, VIOLET),
        emitter = Emitter(duration = DURATION_MS, TimeUnit.MILLISECONDS).max(MAX_AMOUNT),
        position = Position.Relative(POSITION_X, POSITION_Y)
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
        private const val SPEED = 0f
        private const val MAX_SPEED = 30f
        private const val DAMPING = 0.9f
        private const val SPREAD = 360
        private const val YELLOW = 0xfce18a
        private const val RED = 0xff726d
        private const val PINK = 0xf4306d
        private const val VIOLET = 0xb48def
        private const val DURATION_MS = 100L
        private const val MAX_AMOUNT = 100
        private const val POSITION_X = 0.5
        private const val POSITION_Y = 0.3

        fun createIntent(ctx: Context): Intent =
            Intent(ctx, WinActivity::class.java)
    }
}
