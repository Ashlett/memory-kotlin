package com.ashlett.memory

import ItemAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.ashlett.memory.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private val itemList : List<Item> = listOf(
        Item("A"), Item("A"),
        Item("B"), Item("B"),
        Item("C"), Item("C"),
        Item("D"), Item("D"),
        Item("E"), Item("E"),
        Item("F"), Item("F"),
        Item("G"), Item("G"),
        Item("H"), Item("H"),
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
                    hideItemsWithoutPairs()
                }
            }
        )

        with(binding) {
            gameGrid.apply {
                layoutManager = GridLayoutManager(this@GameActivity, 4)
                adapter = itemAdapter
            }
        }
    }

    fun checkGameIsWon() {
        if (game.isWon()) {
            startActivity(WinActivity.createIntent(ctx = this))
        }
    }

    private fun hideItemAtPosition(pos: Int) {
        val binding: ActivityGameBinding = ActivityGameBinding.inflate(layoutInflater)
        with(binding) {
            val viewHolder: ItemAdapter.ViewHolder = gameGrid.findViewHolderForAdapterPosition(pos) as ItemAdapter.ViewHolder
            viewHolder.hideContent()
            itemList[pos].isVisible = false
        }
    }

    fun hideItemsWithoutPairs() {
        Log.d("GameActivity", "in hideItemsWithoutPairs")
        val visibleItemCount: Int = game.getVisibleItemCount()
        Log.d("GameActivity", "visibleItemCount is $visibleItemCount")
        if (visibleItemCount % 2 == 0) {
            val positions = game.getPositionsOfItemsWithoutPairs()
            Log.d("GameActivity", "positions of items w/o pairs: $positions")
            for (pos in positions) {
                Log.d("GameActivity", "hiding item $pos")
                hideItemAtPosition(pos)
            }
        }
    }

    companion object {
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, GameActivity::class.java)
    }
}
