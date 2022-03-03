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

    fun getPositionsOfItemsWithoutPairs(): List<Int>{
        val positions: MutableList<Int> = mutableListOf()
        for ((pos1, item1) in itemList.withIndex()) {
            if (item1.isVisible) {
                var hasPair = false
                for ((pos2, item2) in itemList.withIndex()) {
                    if (item2.isVisible && pos2 != pos1) {
                        hasPair = true
                    }
                }
                if (!hasPair) {
                    positions.add(pos1)
                }
            }
        }
        return positions
    }

    fun getVisibleItemCount(): Int {
        var count = 0
        for (item in itemList) {
            if (item.isVisible) {
                count += 1
            }
        }
        return count
    }

}
