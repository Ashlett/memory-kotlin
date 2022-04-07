package com.ashlett.memory

class GamePresenter(
    private val game: GameLogic = GameLogic()
) : Presenter<GameView> {
    private var view: GameView? = null

    override fun start(view: GameView) {
        this.view = view
        view.renderView(game.getItemList())
    }

    override fun stop() {
        this.view = null
    }

    fun makeMove(position: Int) {
        game.makeMove(position)
        if (game.isWon()) {
            view?.gameOver()
        }
        view?.renderView(game.getItemList())
    }
}
