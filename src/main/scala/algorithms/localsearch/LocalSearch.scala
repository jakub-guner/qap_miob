package algorithms.localsearch

import algorithms.Algorithm
import algorithms.Evaluator._
import algorithms.RandomAlgorithm._
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}
import scala.collection.mutable.ListBuffer
/**
 * Created by JG on 30/10/16.
 */
abstract class LocalSearch extends Algorithm with Neighbourhood{

  override def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    var permutation=randomPermutation(problem)
    var value=calculateResult(permutation, problem)
    var continue = true
    val values = new ListBuffer[Int]()

    while(continue) {
      values += value
      val(newPermutation, newValue, newContinue) = searchNeighbourhood(problem, permutation, value)
      permutation = newPermutation
      value = newValue
      continue = newContinue
    }
    QuadraticAssignmentSolution(permutation, values.toArray)
  }

  def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): (Array[Int], Int, Boolean)

}
