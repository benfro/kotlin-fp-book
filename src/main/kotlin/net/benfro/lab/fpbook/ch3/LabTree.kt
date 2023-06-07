package net.benfro.lab.fpbook.ch3

sealed class Tree<out A>
data class Leaf<A>(val value: A) : Tree<A>()
data class Branch<A>(val left: Tree<A>, val right: Tree<A>) : Tree<A>()

/**
 * Exercise 3.24
 */
fun <A> size(t: Tree<A>): Int =
    when (t) {
        is Leaf -> 1
        is Branch -> 1 + size(t.left) + size(t.right)
    }

/**
 * Exercise 3.25
 */
fun maximum(t: Tree<Int>): Int =
    when (t) {
        is Leaf -> maxOf(0, t.value)
        is Branch -> maxOf(maximum(t.left), maximum(t.right))
    }

/**
 * Exercise 3.26
 */
fun depth(t: Tree<Int>): Int =
    when (t) {
        is Leaf -> 1
        is Branch -> 1 + maxOf(depth(t.left), depth(t.right))
    }

/**
 * Exercise 3.27
 */
fun map(t: Tree<Int>, f: (Int) -> Int): Tree<Int> =
    when (t) {
        is Leaf -> Leaf(f(t.value))
        is Branch -> Branch(map(t.left, f), map(t.right, f))
    }