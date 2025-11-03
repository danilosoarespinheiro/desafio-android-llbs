package com.example.desafioandroid.data.api.dto

import com.example.desafioandroid.domain.model.Board
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for the [Board] data class.
 */
class BoardTest {

    @Test
    fun whenBoardIsCreatedAllPropertiesShouldBeAssignedCorrectly() {

        val id = "board-id-1"
        val name = "Project Alpha"
        val desc = "This is the description for Project Alpha."
        val isClosed = false

        val board = Board(id = id, name = name, desc = desc, closed = isClosed)

        assertEquals("ID should match the provided value", id, board.id)
        assertEquals("Name should match the provided value", name, board.name)
        assertEquals("Description should match the provided value", desc, board.desc)
        assertEquals("Closed status should match the provided value", isClosed, board.closed)
    }

    @Test
    fun twoBoardInstancesWithIdenticalPropertiesShouldBeEqual() {

        val board1 =
            Board(id = "board-id-1", name = "Project Alpha", desc = "Description", closed = false)
        val board2 =
            Board(id = "board-id-1", name = "Project Alpha", desc = "Description", closed = false)

        assertEquals(
            "Two instances with the same property values should be considered equal",
            board1,
            board2
        )
        assertEquals(
            "Hash codes of two equal instances should be the same",
            board1.hashCode(),
            board2.hashCode()
        )
    }

    @Test
    fun twoBoardInstancesWithDifferentPropertiesShouldNotBeEqual() {

        val board1 =
            Board(id = "board-id-1", name = "Project Alpha", desc = "Description", closed = false)
        val board2 =
            Board(id = "board-id-2", name = "Project Beta", desc = "Description", closed = false)

        assertNotEquals(
            "Two instances with different property values should not be equal",
            board1,
            board2
        )
    }

    @Test
    fun copyFunctionShouldCreateNewInstanceWithModifiedProperties() {

        val originalBoard =
            Board(id = "board-id-1", name = "Original Name", desc = "Original Desc", closed = false)

        val updatedBoard = originalBoard.copy(name = "Updated Name", closed = true)

        assertEquals("Updated Name", updatedBoard.name)
        assertTrue(updatedBoard.closed)

        assertEquals(originalBoard.id, updatedBoard.id)
        assertEquals(originalBoard.desc, updatedBoard.desc)

        assertTrue(
            "The copied object should be a different instance",
            originalBoard !== updatedBoard
        )
    }
}