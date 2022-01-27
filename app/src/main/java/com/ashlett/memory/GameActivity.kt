package com.ashlett.memory

import ItemAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GameActivity : AppCompatActivity() {
    private val itemList : Array<Item> = arrayOf(
        Item("A"), Item("A"),
        Item("B"), Item("B"),
        Item("C"), Item("C"),
        Item("D"), Item("D"),
        Item("E"), Item("E"),
        Item("F"), Item("F"),
        Item("G"), Item("G"),
        Item("H"), Item("H"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameGrid : RecyclerView = findViewById(R.id.game_grid)
        gameGrid.layoutManager = GridLayoutManager(applicationContext, 4)
        itemList.shuffle()
        val itemAdapter = ItemAdapter(applicationContext, itemList)
        gameGrid.adapter = itemAdapter
    }

    companion object {
        fun createIntent(ctx: Context): Intent =
            Intent(ctx, GameActivity::class.java)
    }
}