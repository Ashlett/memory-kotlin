package com.ashlett.memory

import android.util.Log

interface Presenter<T> {
    fun start(view: T) {}
    fun stop() {}
}


class GamePresenter : Presenter<GameView> {
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
        val visibleItemCount: Int = game.getVisibleItemCount()
        if (visibleItemCount % 2 == 0) {
            val items = game.getItemsWithoutPairs()
            for (item in items) {
                item.isVisible = false
            }
        }

        itemList[position].isVisible = true

        if (game.isWon()) {
            view?.gameOver()
        }

        view?.renderView(itemList)
    }
}
