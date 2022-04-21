package com.ashlett.memory

class GameLogic(
    private val itemList: MutableList<Item> = ItemListMaker().makeList()
) {

    fun getItemList() = itemList

    fun isWon(): Boolean {
        for (item in itemList) {
            if (!item.isVisible) {
                return false
            }
        }
        return true
    }

    fun makeMove(position: Int): MutableList<Int> {
        val changedPositions: MutableList<Int> = mutableListOf(position)
        if (getVisibleItemCount() % 2 == 0) {
            hideItemsWithoutPairs(changedPositions)
        }
        showItemAt(position)
        return changedPositions
    }

    private fun hideItemsWithoutPairs(changedPositions: MutableList<Int>) {
        for (position in getPositionsOfItemsWithoutPairs()) {
            setVisibility(position, false)
            changedPositions.add(position)
        }
    }

    private fun showItemAt(position: Int) {
        setVisibility(position, true)
    }

    private fun setVisibility(position: Int, isVisible: Boolean) {
        val newItem = itemList[position].copy(isVisible = isVisible)
        itemList[position] = newItem
    }

    private fun getPositionsOfItemsWithoutPairs(): List<Int> {
        val soloItems: MutableList<Int> = mutableListOf()
        for ((index, item) in itemList.withIndex()) {
            if (item.isVisible && !hasPair(index, item)) {
                soloItems.add(index)
            }
        }
        return soloItems
    }

    private fun hasPair(index1: Int, item1: Item): Boolean {
        var hasPair = false
        for ((index2, item2) in itemList.withIndex()) {
            if (index2 != index1 && item2.text == item1.text && item2.isVisible) {
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
