import java.util.Dictionary

object HelloWorld {

  // Function arguments are typed
  // Note the implicit return statement
  def add(firstVal: Int, secondVal: Int): Int = firstVal + secondVal

  def myName(): String = "Luke"

  // This is a "curried" function that allows us to apply it's arguments at different times.
  def multiply(m: Int)(n: Int): Int = m * n

  // The suffixed wildcard indicates that 0..* strings can be passed into this function
  def capitalizeAll(args: String*) = {
    // Map allows us to apply operations to all elements within a collection
    args.map { arg =>
      arg.capitalize
    }
  }

  // A basic class definition
  class Calculator {
    def add(firstVal: Int, secondVal: Int): Int = firstVal + secondVal
    def subtract(firstVal: Int, secondVal: Int): Int = firstVal - secondVal
    def multiply(firstVal: Int, secondVal: Int): Int = firstVal * secondVal
    def divide(firstVal: Int, secondVal: Int): Int = firstVal / secondVal
  }

  // A class with a constructor
  // Unlike other languages, constructors aren't explicit (no "Register" method, no __init__)
  // Instead all passed in values are immediately available as if they were fields in the class
  // Any code outside function definitions is part of the constructor
  // Note again the implicit return statement
  class Register(names: String*) {
    def getAttendees(): String = {
      var attendees = "Today's attendees are: "
      for (n <- names)
        attendees += n + " "

      attendees
    }
  }

  // Demonstrates inheritance of a super class
  class ScientificCalculator(brand: String) extends Calculator() {
    def log(m: Double, base: Double) = math.log(m) / math.log(base)
  }

  // Demonstrates inheritance and method overloading (again it's implicit)
  class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
    def log(m: Int): Double = log(m, math.exp(1))
  }

  // Abstract classes are lightweight.  As with standard OOP these cannot be instantiated
  abstract class Shape {
    def getArea():Int    // subclass should define this
  }

  // Concrete class.  The compiler will not allow it to exist without implementing "getArea"
  class Circle(r: Int) extends Shape {
    def getArea():Int = { r * r * 3 }
  }

  // Traits allow for polymorphism.  Any class inheriting both Car and Shiny will have "band" and "shineRefraction"
  trait Car { val brand: String }
  trait Shiny { val shineRefraction: Int }
  trait Clean { val amountOfDirt: Int }

  // The "with" keyword allows for polymorphism using multiple traits
  class BMW extends Car with Shiny with Clean {
    val brand = "BMW"
    val shineRefraction = 12
    val amountOfDirt = 0
  }

  // Traits can also be used for generics
  trait Cache[K, V] {
    def get(key: K): V
    def put(key: K, value: V)
    def delete(key: K)
  }

  // By extending the trait and defining the expected types, we've got a powerful generics engine
  // Note "???" is actually a thing in Scala, it's like NotImplementedException
  // Also, Unit is an explicit "Void" return type
  class MemoryCache extends Cache[Int, Int] {
    override def get(key: Int): Int = ???
    override def put(key: Int, value: Int): Unit = ???
    override def delete(key: Int): Unit = ???
  }

  def main(args: Array[String]): Unit = {
    // "val" are immutable constants
    val two = 1 + 1
    //two = 3  <- this would error, you cannot reassign a declared val

    // "var" are mutable variables
    var name = "steve"

    name = "steve's mate"  // Totally ok

    val five = add(2,3)

    // Functions without arguments can be called without parenthesis
    println(myName)

    // This is an anonymous inline function
    var addANumber = (x: Int) => x + 1

    // ...which can be reassigned if a var is used
    addANumber = (x: Int) => x + 2
    println(addANumber(1))

    // "Curried" functions can be used as a normal function
    println(multiply(2)(3))

    // Alternatively, "curried" functions can be assigned arguments over time
    // Note the trailing underscore
    val timesTwo = multiply(2) _
    println(timesTwo(3))

    // This will print "ArrayBuffer(Bob, Jane, Dingo)"
    // That is because the map in the function uses deferred execution (lazy evaluation)
    val names = capitalizeAll("Bob", "Jane", "Dingo")
    println(names)

    // For allows us to iterate over the array
    for (n <- names) println(n)

    // Instantiating a basic class
    val calc = new Calculator
    calc.subtract(10, 2)

    val register = new Register("Jim", "Carl", "Frank")
    println(register.getAttendees())
  }
}