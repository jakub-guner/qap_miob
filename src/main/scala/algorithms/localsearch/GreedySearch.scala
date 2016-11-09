package algorithms.localsearch

import java.util.NoSuchElementException

import algorithms.{RandomAlgorithm, Algorithm}
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentSolution, QuadraticAssignmentProblem}
import QuadraticAssignmentEvaluator._
import algorithms.RandomAlgorithm._
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 30/10/16.
 */
class GreedySearch extends LocalSearch{

  def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): Array[Int] = {
    try {
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, problem.calculateResult(permutation))
        }
        .filter {
          case (permutation, result:Int) => result < currentResult
        }.head

      searchNeighbourhood(problem, newPermutation, newResult)
    }catch{
      case nse:NoSuchElementException => currentPermutation
    }
  }
}
