object HelloWorld {
  def main(args: Array[String]): Unit = {
    // "val" are immutable constants
    val two = 1 + 1
    //two = 3  <- this would error, you cannot reassign a declared val

    // "var" are mutable variables
    var name = "steve"

    name = "steve's mate"  // Totally ok
  }
}