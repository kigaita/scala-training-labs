package scalatraining.lab6

import java.io._

/**
 * Lab 6:  Understanding Scala call-by-name by writing a ARM (Automatic Resource Management) method.
 */
object Lab6 {
  val FILE_NAME = "./src/test/resources/lab6.properties"

  /**
   * Read lines from file. Traditional way with resource cleanup etc.
   */
  def readFromFile: List[String] = {
    var stream: BufferedReader = null
    var lines = List[String]()                              
    var line: String = null
    try {
      stream = new BufferedReader(new FileReader(FILE_NAME))
      line = stream.readLine
      while(line ne null) {
        lines ::= line
        line = stream.readLine
      }
    } finally {
      stream.close
    }
    lines
  }

  /**
   * Your task is to write a ARM (Automatic Resource Management) method, that works with code like below.
   * Base it on the code in the 'readFromFile' method above.
   * <pre>
   * // This should be its AP:
   * val lines: List[String] = withInputStream(FILE_NAME) { stream =>
   *   var lines: List[String] = Nil
   *   var line = stream.readLine
   *   while(line ne null) {
   *     lines ::= line
   *     line = stream.readLine
   *   }
   * }
   * </pre>
   */
  def withDataInputStream[T](fileName: String)(body: BufferedReader => T): T = {
    val stream = new BufferedReader(new FileReader(fileName))
    try {
      body(stream)
    } finally {
      stream.close
    }
  }
}