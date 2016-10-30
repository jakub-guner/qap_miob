package algorithms.localsearch

import java.util.NoSuchElementException

import algorithms.Evaluator._
import qap.QuadraticAssignmentProblem

/**
 * Created by JG on 30/10/16.
 */
class SteepestSearch extends LocalSearch{
  override def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): Array[Int] = {
    try {
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, calculateResult(permutation, problem))
        }
        .filter {
          case (permutation, result:Int) => result < currentResult
        }
        .minBy {
          case (permutation, result:Int) => result
        }

      searchNeighbourhood(problem, newPermutation, newResult)
    }catch{
      case uoe:UnsupportedOperationException => currentPermutation
    }

  }
}
