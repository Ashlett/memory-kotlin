package com.ashlett.memory

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameLogicTest {

    @Test
    fun testIsWonFalseIfAllInvisible() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", false), Item("A", false),
                Item("B", false), Item("B", false),
            )
        )
        assertFalse(gameLogic.isWon())
    }

    @Test
    fun testIsWonFalseIfSomeInvisible() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", true), Item("A", false),
                Item("B", false), Item("B", true),
            )
        )
        assertFalse(gameLogic.isWon())
    }

    @Test
    fun testIsWonTrueIfAllVisible() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", true), Item("A", true),
                Item("B", true), Item("B", true),
            )
        )
        assertTrue(gameLogic.isWon())
    }

    @Test
    fun testMakeFirstMove() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", false), Item("A", false),
                Item("B", false), Item("B", false),
            )
        )
        val changedPositions = gameLogic.makeMove(0)
        assertEquals(changedPositions, listOf(0))
        assertTrue(gameLogic.getItemList()[0].isVisible)
    }

    @Test
    fun testMakeThirdMoveWithPair() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", true), Item("A", true),
                Item("B", false), Item("B", false),
            )
        )
        val changedPositions = gameLogic.makeMove(2)
        assertEquals(changedPositions, listOf(2))
        for (index in listOf<Int>(0, 1, 2)) {
            assertTrue(gameLogic.getItemList()[index].isVisible)
        }
        assertFalse(gameLogic.getItemList()[3].isVisible)
    }

    @Test
    fun testMakeThirdMoveWithoutPair() {
        val gameLogic = GameLogic(
            mutableListOf(
                Item("A", true), Item("A", false),
                Item("B", true), Item("B", false),
            )
        )
        val changedPositions = gameLogic.makeMove(1)
        assertEquals(changedPositions, listOf(1, 0, 2))
        for (index in listOf<Int>(0, 2, 3)) {
            assertFalse(gameLogic.getItemList()[index].isVisible)
        }
        assertTrue(gameLogic.getItemList()[1].isVisible)
    }
}
