package algorithms.localsearch

import java.util.NoSuchElementException
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentProblem}
import QuadraticAssignmentEvaluator._
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentProblem}

/**
 * Created by JG on 30/10/16.
 */
class SteepestSearch extends LocalSearch{
  override def searchNeighbourhood(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int): Array[Int] = {
    try {
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, problem.calculateResult(permutation))
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
