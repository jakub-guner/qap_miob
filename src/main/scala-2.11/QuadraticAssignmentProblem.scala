import scala.Array._
import scala.io.Source

/**
 * Created by JG on 13/10/16.
 */
object QuadraticAssignmentProblem extends App {
  //Data downloaded from https://www.cs.put.poznan.pl/mkomosinski/lectures/mam/qap.zip
  //Format documentation http://anjos.mgi.polymtl.ca/qaplib/inst.html

  val qap:QuadraticAssignmentProblem=loadInstance("./qapdatsol/bur26a.dat")

  def loadInstance(path:String):QuadraticAssignmentProblem ={
    def readLinesFromFile: Iterator[String] = {
      Source.fromFile(path).getLines()
    }

    def getInstanceSizeFromFirstLine(lns:Iterator[String]): Int = {
      lns.next().toInt
    }

    def getMatrix(instanceSize: Int,lines: Iterator[String]): Array[Array[Int]] = {
      1.to(instanceSize).map(Int => {
        lines.next().split(" ").filter(_.length>0).map(_.toInt)
      }).toArray
    }

    def skipLine(lines: Iterator[String]): String = {
      lines.next()
    }

    val lines=readLinesFromFile
    val instanceSize=getInstanceSizeFromFirstLine(lines)
    skipLine(lines)
    val flows= getMatrix(instanceSize, lines)
    skipLine(lines)
    val distances=getMatrix(instanceSize, lines)
    QuadraticAssignmentProblem(flows, distances)
  }

  def apply(flows:Array[Array[Int]], distances:Array[Array[Int]]) = new QuadraticAssignmentProblem(flows, distances)

}

class QuadraticAssignmentProblem(val flows:Array[Array[Int]], val distances:Array[Array[Int]]){



}
