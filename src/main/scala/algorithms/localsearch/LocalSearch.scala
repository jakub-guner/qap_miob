package algorithms.localsearch

import algorithms.Algorithm
import algorithms.Evaluator._
import algorithms.RandomAlgorithm._
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 30/10/16.
 */
abstract class LocalSearch extends Algorithm with Neighbourhood{

  override def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    val initPermutation=randomPermutation(problem)
    val initResult=calculateResult(initPermutation, problem)

    val bestPermutation = searchNeighbourhood(problem, initPermutation, initResult)
    QuadraticAssignmentSolution(bestPermutation)
  }

  def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): Array[Int]



}
