package algorithms

import qap.{QuadraticAssignmentProblem, QuadraticAssignmentSolution}


/**
 * Created by JG on 22/10/16.
 */
object MinMaxProductAlgorithm extends Algorithm{

  case class Entry(value: Int, row: Int, col:Int)
  case class Position(facilityIndex: Int, locationIndex:Int)

  //For performance use mutable.DoubleLinkedList
  override def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {

//    val permutation=Array.fill[Int](problem.size)({-1})
    val flows=toSortedList(problem.flows)
    val distances=toSortedList(problem.distances)
//
//    println("Flows")
//    flows.foreach(println)
//
//    println("Dists")
//    distances.foreach(println)

//    flows.foreach(println)

    val solution=findBestMatches(flows, distances)

    val permutation:Array[Int]=solution
      .sortWith(_.locationIndex < _.locationIndex)
      .map(_.facilityIndex+1)
      .toArray

    QuadraticAssignmentSolution(permutation)
  }

  def findBestMatches(flows: List[Entry], distances: List[Entry]): List[Position] ={

    if(flows.nonEmpty && distances.nonEmpty){
      val biggestFlowSmallestDistance=flows.head.value * distances.last.value
      val biggestDistanceSmallestFlow=distances.head.value * flows.last.value

//      println(biggestFlowSmallestDistance +" vs. "+biggestDistanceSmallestFlow)

      if(biggestFlowSmallestDistance < biggestDistanceSmallestFlow){
        val flow=flows.head
        val distance=distances.last
        val newFlows=flows
          .drop(1)
          .filter(_.row!=flow.row)
          .filter(_.row!=flow.col)
          .filter(_.col!=flow.col)
          .filter(_.col!=flow.row)
        val newDistances=distances
          .dropRight(1)
          .filter(_.row!=distance.row)
          .filter(_.row!=distance.col)
          .filter(_.col!=distance.col)
          .filter(_.col!=distance.row)

//        printDebug(newFlows, newDistances)

        Position(flow.row, distance.row) :: Position(flow.col, distance.col) :: findBestMatches(newFlows, newDistances)
      }else{
        val flow=flows.last
        val distance=distances.head
        val newFlows=flows
          .dropRight(1)
          .filter(_.row!=flow.row)
          .filter(_.row!=flow.col)
          .filter(_.col!=flow.col)
          .filter(_.col!=flow.row)
        val newDistances=distances
          .drop(1)
          .filter(_.row!=distance.row)
          .filter(_.row!=distance.col)
          .filter(_.col!=distance.col)
          .filter(_.col!=distance.row)


//        printDebug(newFlows, newDistances)

        Position(flow.row, distance.row) :: Position(flow.col, distance.col) :: findBestMatches(newFlows, newDistances)
      }

    }else{
      Nil
    }


  }

  def printDebug(flows: List[Entry], distances: List[Entry])={
    println("-------------------------------")

    println("Flows")
    flows.foreach(println)

    println("Dists")
    distances.foreach(println)

  }



  //Full entry
  //Zmienic na tylko pod przekatna - symetryczny flow i distance
  def toSortedList(matrix:Array[Array[Int]]) : List[Entry] = {
    val entryArray=for{

      (row, rowIndex) <- matrix.zipWithIndex
      (value, columnIndex) <- row.zipWithIndex if (columnIndex < rowIndex)

     }yield Entry(value, rowIndex, columnIndex)

    entryArray
      .sortWith(_.value > _.value)
      .toList
  }
}


//object MinMaxProductAlgorithm{
//  def apply(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution = {
//    {new MinMaxProductAlgorithm}.
//    measureTime(calculate(problem))
//  }
//}