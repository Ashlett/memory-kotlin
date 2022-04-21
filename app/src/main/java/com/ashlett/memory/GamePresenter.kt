package com.ashlett.memory

class GamePresenter(
    private val game: GameLogic = GameLogic()
) : Presenter<GameView> {
    private var view: GameView? = null

    override fun start(view: GameView) {
        this.view = view
        val itemList = game.getItemList()
        view.renderView(itemList, IntRange(0, itemList.size - 1).toList())
    }

    override fun stop() {
        this.view = null
    }

    fun makeMove(position: Int) {
        val changedPositions: List<Int> = game.makeMove(position)
        if (game.isWon()) {
            view?.gameOver()
        }
        view?.renderView(game.getItemList(), changedPositions)
    }
}
