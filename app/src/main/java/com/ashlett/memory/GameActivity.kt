package com.ashlett.memory

import ItemAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.ashlett.memory.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), GameView {

    private lateinit var presenter: GamePresenter
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemAdapter = ItemAdapter(
            itemList = emptyList(),
            listener = object : ItemAdapter.Listener {
                override fun onClick(position: Int) {
                    presenter.businessLogicHere(position)
                }
            }
        )

        presenter = GamePresenter()
        presenter.start(this)

        with(binding) {
            gameGrid.apply {
                layoutManager = GridLayoutManager(this@GameActivity, 4)
                adapter = itemAdapter
            }
        }
    }

    override fun renderView(list: List<Item>) {
        itemAdapter.itemList = list
        Log.d("GameActivity", "itemList is $list")
        itemAdapter.notifyDataSetChanged()
    }

    override fun gameOver() {
        startActivity(WinActivity.createIntent(ctx = this))
    }

    companion object {
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, GameActivity::class.java)
    }
}
