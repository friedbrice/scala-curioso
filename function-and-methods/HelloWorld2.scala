/**
 * HelloWorld.scala
 *
 * Compile as `scalac HelloWorld2.scala`
 * Run as `scala -cp . HelloWorld2`
 */

object HelloWorld2 {

  def main(args: Array[String]): Unit = {

    println("Hello, World!")
  }
}

/**
 * Did it run?
 *
 * Explanation: Here, main is a _method_ that takes an Array[String] and
 * produces a Unit. In HelloWorld.scala, main is a _field_ that happens
 * to be a function, but this is not the same thing as a method (which
 * is, to say the least, unfortunate).
 */
