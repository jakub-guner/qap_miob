package algorithms.localsearch

import java.util.NoSuchElementException

import algorithms.{Evaluator, RandomAlgorithm, Algorithm}
import algorithms.Evaluator._
import algorithms.RandomAlgorithm._
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 30/10/16.
 */
class GreedySearch extends LocalSearch{


  def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): (Array[Int], Int, Boolean) = {
    try {
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, calculateResult(permutation, problem))
        }
        .filter {
          case (permutation, result:Int) => result < currentResult
        }.head

      (newPermutation,newResult,true)
    }catch{
      case nse:NoSuchElementException => (currentPermutation, currentResult, false)
    }
  }
}
