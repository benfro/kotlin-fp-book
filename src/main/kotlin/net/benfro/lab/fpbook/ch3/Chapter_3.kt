package net.benfro.lab.fpbook.ch3


sealed class LabList<out A> {
    companion object {
        fun <A> of(vararg aa: A): LabList<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun sum(ints: LabList<Int>): Int =
            when (ints) {
                is Nil -> 0
                is Cons -> ints.head + sum(ints.tail)
            }

        /**
         * Exercise 3.1
         */
        fun <A> tail(xs: LabList<A>): LabList<A> =
            when (xs) {
                is Nil -> Nil
                is Cons -> xs.tail
            }

        /**
         * Exercise 3.2
         */
        fun <A> setHead(xs: LabList<A>, x: A): LabList<A> =
            when (xs) {
                is Nil -> Nil
                is Cons -> Cons(x, xs.tail)
            }

        /**
         * Exercise 3.3
         */
        fun <A> drop(l: LabList<A>, n: Int): LabList<A> {
            tailrec fun loop(i: Int, xs: LabList<A>): LabList<A> =
                when (i) {
                    0 -> xs
                    else -> loop(i - 1, tail(xs))
                }
            return loop(n, l)
        }

        /**
         * Exercise 3.4
         */
        fun <A> dropWhile(l: LabList<A>, f: (A) -> Boolean): LabList<A> =
            when (l) {
                is Cons ->
                    if (f(l.head)) dropWhile(l.tail, f) else l

                is Nil -> l
            }

        fun <A> append(a1: LabList<A>, a2: LabList<A>): LabList<A> =
            when (a1) {
                is Nil -> a2
                is Cons -> Cons(a1.head, append(a1.tail, a2))
            }

        /**
         * Exercise 3.5
         */
        fun <A> init(l: LabList<A>): LabList<A> =
            when (l) {
                is Cons ->
                    if (l.tail == Nil) Nil
                    else Cons(l.head, init(l.tail))

                is Nil ->
                    throw IllegalStateException("Cannot init Nil list")
            }

        fun <A, B> foldRight(xs: LabList<A>, z: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> f(xs.head, foldRight(xs.tail, z, f))
            }

        fun sumFoldRight(ints: LabList<Int>): Int =
            foldRight(ints, 0, { a, b -> a + b })

        fun productFoldRight(dbs: LabList<Double>): Double =
            foldRight(dbs, 1.0, { a, b -> a * b })

        fun <A> empty(): LabList<A> = Nil

        /**
         * Exercise 3.6
         * No, not with current code constraints
         */

        /**
         * Exercise 3.8
         */
        fun <A> lengthFoldRight(xs: LabList<A>): Int =
            foldRight(xs, 0, { _, acc -> acc + 1 })

        /**
         * Exercise 3.9
         */
        tailrec fun <A, B> foldLeft(xs: LabList<A>, z: B, f: (B, A) -> B): B =
            when (xs) {
                is Nil -> z
                is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
            }

        /**
         * Exercise 3.10
         */
        fun sumFoldLeft(ints: LabList<Int>): Int =
            foldLeft(ints, 0, { b, a -> a + b })

        fun productFoldLeft(dbs: LabList<Double>): Double =
            foldLeft(dbs, 1.0, { b, a -> a * b })

        fun <A> lengthFoldLeft(ints: LabList<A>): Int =
            foldLeft(ints, 0, { acc, _ -> acc + 1 })

        /**
         * Exercise 3.11
         */
        fun <A> reverseFoldLeft(xs: LabList<A>): LabList<A> =
            foldLeft(xs, empty(), { t: LabList<A>, h: A -> Cons(h, t) })

        /**
         * Exercise 3.12
         * Hard
         */

        /**
         * Exercise 3.13
         */
        fun <A> appendFold(a1: LabList<A>, a2: LabList<A>): LabList<A> =
            when (a1) {
                is Nil -> a2
                is Cons -> foldRight(a1, a2, { a, b -> Cons(a1.head, append(a1.tail, a2)) })
            }

        /**
         * Exercise 3.14
         * Hard
         */

        /**
         * Exercise 3.15
         */
        //fun <Int> incrByOne(xs: LabList<Int>): LabList<Int> =
        //    foldRight(
        //        xs,
        //        empty()
        //    ) { i: Int, ls ->
        //        Cons(i + 1, ls)
        //    }
    }
}

//private operator fun <Int> Int.plus(i: Int): Int {
//    return this + i;
//}

object Nil : LabList<Nothing>()
data class Cons<out A>(val head: A, val tail: LabList<A>) : LabList<A>()


fun main() {
    val inst = LabList.of(1, 2, 3, 4)
    val inst2: LabList<Double> = LabList.of(1.0, 2.0, 3.0, 4.0, 5.0)
    //println(LabList.sum(inst))
    //println(LabList.tail(inst))
    //println(LabList.drop(inst, 1))
    //println(LabList.init(inst))
    //println(LabList.setHead(inst, 1000))
    //println(LabList.incrByOne(inst))
    println(LabList.appendFold(Cons(-1, Cons(0, Nil)), inst))
    println(LabList.reverseFoldLeft(inst))
    println(LabList.sumFoldLeft(inst))
    println(LabList.productFoldLeft(inst2))
    println(LabList.lengthFoldLeft(inst))
    println(LabList.lengthFoldLeft(inst2))

    //val xs = Cons(1, Cons(2, Cons(3, Nil)))
    //val lenght = LabList.foldRight(
    //    Cons(1, Cons(2, Cons(3, Nil))),
    //    LabList.empty<Int>(),
    //    LabList.length1(Cons(1, Cons(2, Cons(3, Nil))))
    //)


    /**
     * Exercise 3.7
     */
    val foldRight = LabList.foldRight(
        Cons(1, Cons(2, Cons(3, Nil))),
        LabList.empty<Int>(),
        { x, y -> Cons(x, y) }
    )
    println(foldRight)

}