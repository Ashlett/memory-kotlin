package com.ashlett.memory

class GameLogic {
    private val itemList: List<Item> = listOf(
        Item("\uD83D\uDE42"), Item("\uD83D\uDE42"),
        Item("\uD83D\uDC25"), Item("\uD83D\uDC25"),
        Item("\uD83D\uDC1F"), Item("\uD83D\uDC1F"),
        Item("⭐️"), Item("⭐️"),
        Item("\uD83C\uDF4E"), Item("\uD83C\uDF4E"),
        Item("⚽️"), Item("⚽️"),
        Item("\uD83D\uDD6F"), Item("\uD83D\uDD6F"),
        Item("❤️"), Item("❤️"),
    ).shuffled()

    fun getItemList() = itemList

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

    private fun getItemsWithoutPairs(): List<Item> {
        val soloItems: MutableList<Item> = mutableListOf()
        for (item1 in itemList) {
            if (item1.isVisible && !hasPair(item1)) {
                soloItems.add(item1)
            }
        }
        return soloItems
    }

    private fun hasPair(item1: Item): Boolean {
        var hasPair = false
        for (item2 in itemList) {
            if (item2 != item1 && item2.text == item1.text && item2.isVisible) {
                hasPair = true
            }
        }
        return hasPair
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
