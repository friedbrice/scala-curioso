/**
 * Sum.scala
 *
 * Compile as `scalac Sum.scala`
 * Run as `scala -cp . Sum`
 */

object Sum {

  def reduce[T](f: (T, T) => T, z: T, list: List[T]): T = list match {
    case Nil => z
    case t :: ts => reduce(f, f(z, t), ts)
  }

  def sum1(ints: List[Int]): Int = {
    reduce[Int]((x, y) => x + y, 0, ints)
  }

  def sum2(ints: List[Int]): Int = {
    reduce[Int]((x, y) => return x + y, 0, ints)
  }

  def main(args: Array[String]): Unit = {

    println("sum1(List(5)) = " + sum1(List(5)).toString)
    println("sum2(List(5)) = " + sum2(List(5)).toString)
    println("sum1(List(5, 4)) = " + sum1(List(5, 4)).toString)
    println("sum2(List(5, 4)) = " + sum2(List(5, 4)).toString)
    println("sum1(List(5, 4, 3)) = " + sum1(List(5, 4, 3)).toString)
    println("sum2(List(5, 4, 3)) = " + sum2(List(5, 4, 3)).toString)
  }
}

/**
 * Does sum2 return the correct result?
 *
 * Explanation: consider `sum1(List(5, 4))` vs `sum2(List(5,4))`
 *
 *     sum1(List(5, 4))
 *     (let f = (x, y) => x + y)
 *     == reduce(f, 0, List(5, 4))
 *     == reduce(f, f(0, 5), List(4))
 *     == reduce(f, 0 + 5, List(4))
 *     == reduce(f, 5, List(4))
 *     == reduce(f, f(5, 4), List())
 *     == reduce(f, 5 + 4, List())
 *     == reduce(f, 9, List())
 *     == 9
 *
 *     sum2(List(5, 4))
 *     (let g = (x, y) => return(x + y))
 *     == reduce(g, 0, List(5, 4))
 *     == reduce(g, g(0, 5), List(4))
 *     == reduce(g, return 0 + 5, List(4))
 *
 * at which point, the computation is aborted and `0 + 5` is returned
 * to the caller, resulting in `println(0 + 5)` (or perhaps resulting in
 * `println(5)`: I'm not sure if `+` or `return` evaluates first in
 * `return 0 + 5`).
 *
 * Oddly enought, the semantics of `return` have a lot to do with the
 * distinction between _methods_ and _functions_ in Scala
 * (c.f. functions-and-methods/{HelloWorld.scala, HelloWorld2.scala}).
 * See https://tpolecat.github.io/2014/05/09/return.html for a more
 * complete explanation.
 */
