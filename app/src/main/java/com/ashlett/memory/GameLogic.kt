package com.ashlett.memory

class GameLogic(private val itemList: List<Item>) {
    fun isWon(): Boolean {
        for (item in itemList) {
            if (!item.isVisible) {
                return false
            }
        }
        return true
    }
}
