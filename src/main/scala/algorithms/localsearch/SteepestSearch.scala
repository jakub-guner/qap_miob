package algorithms.localsearch

import java.util.NoSuchElementException
import qap._
import QuadraticAssignmentEvaluator._

/**
 * Created by JG on 30/10/16.
 */
class SteepestSearch extends LocalSearch{
  override def searchSolutionSpace(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int, steps:Int, evaluatedSolutions:Int): QuadraticAssignmentSolution = {
    try {
      val nb=neighbourhood(currentPermutation)
      val (newPermutation, newResult) =
        nb
        .map{
          case permutation => (permutation, problem.calculateResult(permutation))
        }
        .filter {
          case (permutation, result:Int) => result < currentResult
        }
        .minBy {
          case (permutation, result:Int) => result
        }
      searchSolutionSpace(problem, newPermutation, newResult, steps=steps+1, evaluatedSolutions=evaluatedSolutions+nb.size)
    }catch{
      case uoe:UnsupportedOperationException => QuadraticAssignmentSolution(currentPermutation).copy(steps=steps, evaluatedSolutions=evaluatedSolutions)
    }

  }

  override def neighName: String = ""

  override def name: String = "steepest"+neighName
}
