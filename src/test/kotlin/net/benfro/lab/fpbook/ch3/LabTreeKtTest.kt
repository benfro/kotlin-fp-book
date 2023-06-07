package net.benfro.lab.fpbook.ch3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LabTreeKtTest {

    @Test
    fun testLeaf() {
        val t = Leaf(3)
        assertEquals(1, size(t))
        assertEquals(3, maximum(t))
        assertEquals(1, depth(t))
    }

    @Test
    fun testBranchMax() {
        val t = Branch(Leaf(3), Leaf(7))
        assertEquals(3, size(t))
        assertEquals(7, maximum(t))
        assertEquals(2, depth(t))

        assertEquals(3, depth(Branch(Branch(Leaf(399), Leaf(4711)), Leaf(7))))
    }

    @Test
    fun testMap() {
        val t = Branch(Leaf(3), Leaf(7))
        val res: Branch<Int> = map(t) { a -> a * 2 } as Branch
        assertEquals(6, (res.left as Leaf).value )
        assertEquals(14, (res.right as Leaf).value )
    }
}