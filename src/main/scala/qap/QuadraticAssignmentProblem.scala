package qap

import scala.Array._
import scala.collection.SeqView
import scala.io.Source

/**
 * Created by JG on 13/10/16.
 */
object QuadraticAssignmentProblem extends App {
  //Data downloaded from https://www.cs.put.poznan.pl/mkomosinski/lectures/mam/qap.zip
  //Format documentation http://anjos.mgi.polymtl.ca/qaplib/inst.html

  def loadInstance(instanceName:String):QuadraticAssignmentProblem ={
    def makeFullPath = "./qapdatsol/"+instanceName +".dat"

    def readLinesFromFile: Iterator[String] = {
      Source.fromFile(makeFullPath).getLines()
    }

    def getInstanceSizeFromFirstLine(lns:Iterator[String]): Int = {
      lns.next().trim.toInt
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
    val optimalSolution=loadOptimalSolution(instanceName)
    new QuadraticAssignmentProblem(instanceName, instanceSize, flows, distances, optimalSolution)
  }
  
  def loadOptimalSolution(instanceName:String) :Int= {
    def makeFullPath = "./qapdatsol/"+instanceName +".sln"

    def readLinesFromFile: Iterator[String] = {
      Source.fromFile(makeFullPath).getLines()
    }

    readLinesFromFile.next().split(" ").filter(_.size>0)(1).toInt
  }

  def apply(instanceName:String) = {
    println(s"Loading: $instanceName")
    loadInstance(instanceName)
  }

}

class QuadraticAssignmentProblem(val name:String, val size:Int, val flows:Array[Array[Int]], val distances:Array[Array[Int]], val optimalSolution:Int){

  def calculateResult(permutation: Array[Int], currentBest:Int=Int.MaxValue) : Int = {
    val products=for{
      facilityA <- (0 until size).view
      facilityB <- (0 until size).view
      locationA = permutation(facilityA)-1
      locationB = permutation(facilityB)-1
    } yield flows(facilityA)(facilityB) * distances(locationA)(locationB)

    //Optimization - evaluate lazily; return immediately when the result is worse than current best
//    def getSum(sequence:SeqView[Int, Seq[_]]): Int ={
//      sequence.foldLeft(0){
//        (sofar, next)=>if(sofar + next < currentBest) sofar+next
//        else return Int.MaxValue
//      }
//    }
//
//    getSum(products)

    products.sum
  }

  override def toString = s"QuadraticAssignmentProblem(name=$name, size=$size, flows=$flows, distances=$distances, optimalSolution=$optimalSolution)"
}
