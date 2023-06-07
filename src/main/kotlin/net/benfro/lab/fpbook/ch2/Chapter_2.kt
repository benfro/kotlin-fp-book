package net.benfro.lab.fpbook.ch2

fun factorial(i: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else go(n - 1, n * acc)
    return go(i, 1)
}

/**
 * Exercise 2.1
 */
fun fib(i: Long): Long {
    tailrec fun go(n: Long, first: Long, second: Long): Long =
        if (n - 1 <= 0) first
        else go(n - 1, second, first + second)
    return go(i, 0, 1)
}

fun main() {
    //println(fib(1_000_000))

    //val strings = arrayOf("A", "AA", "AAA", "AAAAA")
    //println(findFirst(strings) { it.length > 2 })

    val ok: List<Int> = listOf(2, 4, 5, 6, 11, 29)
    val nok: List<Int> = listOf(2, 5, 4)

    //val orderf = { a,b -> a < b}

    val okSorted = isSorted(ok) { a, b -> a < b }
    val nokSorted = isSorted(nok) { a, b -> a < b }
    println("Should be true: " + okSorted)
    println("Should be false: " + nokSorted)
}

fun <A> findFirst(xs: Array<A>, p: (A) -> Boolean): Int {
    tailrec fun loop(n: Int): Int = when {
        n >= xs.size -> -1
        p(xs[n]) -> n
        else -> loop(n + 1)
    }
    return loop(0)
}

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

/**
 * Exercise 2.2
 */
fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun loop(first: A, rest: List<A>): Boolean =
        when {
            rest.size == 1 -> order(first, rest.head)
            !order(first, rest.head) -> false;
            else -> loop(rest.head, rest.tail)
        }
    return loop(aa.head, aa.tail)
}

/**
 * Exercise 2.3
 */
fun <A, B, C> curry(f: (A,B) -> C): (A) -> (B) -> C =
    { a: A -> { b: B -> f(a, b) } }

/**
 * Exercise 2.4
 */
fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C =
    {a: A, b: B -> f(a)(b)}

/**
 * Exercise 2.5
 */
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C =
    { a: A -> f( g(a) ) }