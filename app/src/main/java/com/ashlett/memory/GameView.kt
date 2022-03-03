package com.ashlett.memory

interface GameView {
    fun renderView(list: List<Item>)
    fun gameOver()
}