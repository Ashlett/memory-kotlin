package com.ashlett.memory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashlett.memory.databinding.ActivityWinBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class WinActivity : AppCompatActivity() {

    private val party = Party(
        speed = SPEED,
        maxSpeed = MAX_SPEED,
        emitter = Emitter(duration = DURATION_MS, TimeUnit.MILLISECONDS).max(MAX_AMOUNT),
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
        private const val DURATION_MS = 100L
        private const val MAX_AMOUNT = 100

        fun createIntent(ctx: Context): Intent =
            Intent(ctx, WinActivity::class.java)
    }
}
