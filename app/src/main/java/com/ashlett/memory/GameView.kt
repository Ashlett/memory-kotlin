package com.ashlett.memory

interface GameView {
    fun renderView(list: List<Item>, changedPositions: List<Int>)
    fun gameOver()
}
