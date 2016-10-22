package algorithms

import qap.{QuadraticAssignmentProblem, QuadraticAssignmentSolution}


/**
 * Created by JG on 22/10/16.
 */
object MinMaxProductAlgorithm extends Algorithm{

  case class Entry(value: Int, row: Int, col:Int)
  case class Position(facilityIndex: Int, locationIndex:Int)
  
  override def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    
    val flows=toSortedList(problem.flows)
    val distances=toSortedList(problem.distances)


    val solution=findBestMatches(flows, distances)

    val permutation:Array[Int]=solution
      .sortWith(_.locationIndex < _.locationIndex)
      .map(_.facilityIndex+1)
      .toArray

    QuadraticAssignmentSolution(permutation)
  }

  def findBestMatches(flows: List[Entry], distances: List[Entry]): List[Position] ={
    
    def filterEntries(entry:Entry, selected:Entry):Boolean={
      entry.row!=selected.row &&
      entry.row!=selected.col &&
      entry.col!=selected.col &&
      entry.col!=selected.row
    }

    if(flows.nonEmpty && distances.nonEmpty){
      val biggestFlowSmallestDistance=flows.head.value * distances.last.value
      val biggestDistanceSmallestFlow=distances.head.value * flows.last.value
      

      if(biggestFlowSmallestDistance < biggestDistanceSmallestFlow){
        val flow=flows.head
        val distance=distances.last
        val newFlows=flows
          .drop(1)
          .filter(filterEntries(_, flow))

        val newDistances=distances
          .dropRight(1)
          .filter(filterEntries(_, distance))

        Position(flow.row, distance.row) :: Position(flow.col, distance.col) :: findBestMatches(newFlows, newDistances)
      }else{
        val flow=flows.last
        val distance=distances.head
        val newFlows=flows
          .dropRight(1)
          .filter(filterEntries(_, flow))

        val newDistances=distances
          .drop(1)
          .filter(filterEntries(_, distance))

        Position(flow.row, distance.row) :: Position(flow.col, distance.col) :: findBestMatches(newFlows, newDistances)
      }

    }else{
      Nil
    }


  }
  
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