package com.ashlett.memory

import android.util.Log

interface Presenter<T> {
    fun start(view: T) {}
    fun stop() {}
}


class GamePresenter(

) : Presenter<GameView> {
    private val itemList: List<Item> = listOf(
        Item("A"), Item("A"),
        Item("B"), Item("B"),
        Item("C"), Item("C"),
        Item("D"), Item("D"),
        Item("E"), Item("E"),
        Item("F"), Item("F"),
        Item("G"), Item("G"),
        Item("H"), Item("H"),
    ).shuffled()
    private val game = GameLogic(itemList)

    private var view: GameView? = null

    override fun start(view: GameView) {
        this.view = view
        view.renderView(itemList)
    }

    override fun stop() {

    }

    fun businessLogicHere(position: Int) {
        itemList[position].isVisible = true

        if (game.isWon()) {
            view?.gameOver()
        }

        val visibleItemCount: Int = game.getVisibleItemCount()
        if (visibleItemCount % 2 == 0) {
            val items = game.getItemsWithoutPairs()
            Log.d("GamePresenter", "items w/o pairs: $items")
            if (items.isNotEmpty()) {
                TODO("freeze game for x seconds so player can memorize")
            }
            for (item in items) {
                item.isVisible = false
            }
        }

        view?.renderView(itemList)
    }
}
