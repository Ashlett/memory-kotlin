package com.ashlett.memory

class ItemListMaker {
    private val repetitions: Int = 2
    private val itemTextStrings: List<String> = listOf(
        "\uD83D\uDE42", // smiley
        "\uD83D\uDC25", // chicken
        "\uD83D\uDC1F", // fish
        "⭐️", // star
        "\uD83C\uDF4E", // apple
        "⚽️", // football
        "\uD83D\uDD6F", // candle
        "❤️", // heart
    )

    fun makeList(): MutableList<Item> {
        val itemList: MutableList<Item> = mutableListOf()
        for (text in itemTextStrings) {
            repeat(repetitions) {
                itemList.add(
                    Item(text, false)
                )
            }
        }
        itemList.shuffle()
        return itemList
    }
}
