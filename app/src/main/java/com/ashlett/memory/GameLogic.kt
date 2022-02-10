package com.ashlett.memory

class GameLogic(private val itemList: Array<Item>) {
    fun isWon(): Boolean {
        for (item in itemList) {
            if (!item.isVisible) {
                return false
            }
        }
        return true
    }
}
