package com.ashlett.memory

import ItemAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.ashlett.memory.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private val itemList: List<Item> = listOf(
        Item("\uD83D\uDE42"), Item("\uD83D\uDE42"),
        Item("\uD83D\uDC25"), Item("\uD83D\uDC25"),
        Item("\uD83D\uDC1F"), Item("\uD83D\uDC1F"),
        Item("⭐️"), Item("⭐️"),
        Item("\uD83C\uDF4E"), Item("\uD83C\uDF4E"),
        Item("⚽️"), Item("⚽️"),
        Item("\uD83D\uDD6F"), Item("\uD83D\uDD6F"),
        Item("❤️"), Item("❤️"),
    ).shuffled()
    private val game = GameLogic(itemList = itemList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemAdapter = ItemAdapter(
            itemList = itemList,
            listener = object : ItemAdapter.Listener {
                override fun onClick() {
                    checkGameIsWon()
                }
            }
        )

        with(binding) {
            gameGrid.apply {
                layoutManager = GridLayoutManager(this@GameActivity, GRID_SIZE)
                adapter = itemAdapter
            }
        }
    }

    fun checkGameIsWon() {
        if (game.isWon()) {
            startActivity(WinActivity.createIntent(ctx = this))
        }
    }

    companion object {
        private const val GRID_SIZE = 4
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, GameActivity::class.java)
    }
}
