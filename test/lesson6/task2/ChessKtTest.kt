package lesson6.task2

import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions

class ChessKtTest {
    @Test
    fun knightMoveNumber() {
        Assertions.assertEquals(0, knightMoveNumber(square("d3"), square("d3")))
        Assertions.assertEquals(1, knightMoveNumber(square("e4"), square("d6")))
        Assertions.assertEquals(2, knightMoveNumber(square("f5"), square("g6")))
        Assertions.assertEquals(3, knightMoveNumber(square("g6"), square("g3")))
        Assertions.assertEquals(3, knightMoveNumber(square("d4"), square("a8")))
        Assertions.assertEquals(4, knightMoveNumber(square("h7"), square("f5")))
        Assertions.assertEquals(4, knightMoveNumber(square("g7"), square("h8")))
        Assertions.assertEquals(6, knightMoveNumber(square("a8"), square("h1")))
    }

}