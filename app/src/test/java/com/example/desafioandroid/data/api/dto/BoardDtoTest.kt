package com.example.desafioandroid.data.api.dto

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the [BoardDto] class.
 */
class BoardDtoTest {

    @Test
    fun whenBoardDtoIsCreatedPropertiesShouldBeAssignedCorrectly() {

        val id = "board123"
        val name = "Project Phoenix"
        val desc = "A project to refactor the entire codebase."
        val isClosed = false

        val boardDto = BoardDto(
            id = id,
            name = name,
            desc = desc,
            closed = isClosed
        )

        assertEquals("The id should match the value provided at creation", id, boardDto.id)
        assertEquals("The name should match the value provided at creation", name, boardDto.name)
        assertEquals(
            "The description should match the value provided at creation",
            desc,
            boardDto.desc
        )
        assertEquals(
            "The closed status should match the value provided at creation",
            isClosed,
            boardDto.closed
        )
    }
}