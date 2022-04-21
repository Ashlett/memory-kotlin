package com.ashlett.memory

import ItemAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                    presenter.makeMove(position)
                }
            }
        )

        presenter = GamePresenter()
        presenter.start(this)

        with(binding) {
            gameGrid.apply {
                layoutManager = GridLayoutManager(this@GameActivity, GRID_SIZE)
                adapter = itemAdapter
            }
        }
    }

    override fun renderView(list: List<Item>, changedPositions: List<Int>) {
        itemAdapter.itemList = list
        for (position in changedPositions) {
            itemAdapter.notifyItemChanged(position)
        }
    }

    override fun gameOver() {
        startActivity(WinActivity.createIntent(ctx = this))
    }

    override fun onStop() {
        presenter.stop()
        super.onStop()
    }

    companion object {
        private const val GRID_SIZE = 4
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, GameActivity::class.java)
    }
}
