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

    fun makeMove(position: Int) {
        if (getVisibleItemCount() % 2 == 0) {
            hideItemsWithoutPairs()
        }
        showItemAt(position)
    }

    private fun hideItemsWithoutPairs() {
        for (item in getItemsWithoutPairs()) {
            item.isVisible = false
        }
    }

    private fun showItemAt(position: Int) {
        itemList[position].isVisible = true
    }

    private fun getItemsWithoutPairs(): List<Item>{
        val soloItems: MutableList<Item> = mutableListOf()
        for (item1 in itemList) {
            if (item1.isVisible) {
                var hasPair = false
                for (item2 in itemList) {
                    if (item2 != item1 && item2.text == item1.text && item2.isVisible) {
                        hasPair = true
                    }
                }
                if (!hasPair) {
                    soloItems.add(item1)
                }
            }
        }
        return soloItems
    }

    private fun getVisibleItemCount(): Int {
        var count = 0
        for (item in itemList) {
            if (item.isVisible) {
                count += 1
            }
        }
        return count
    }
}
