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

  def searchSolutionSpace(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int, steps:Int, evaluatedSolutions:Int): QuadraticAssignmentSolution = {
    try {
      var coveredSolutions=0
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, problem.calculateResult(permutation))
        }
        .filter {
          case (permutation, result:Int) => {coveredSolutions+=1; result < currentResult}
        }.head

      searchSolutionSpace(problem, newPermutation, newResult, steps=steps+1, evaluatedSolutions+coveredSolutions)
    }catch{
      case nse:NoSuchElementException => QuadraticAssignmentSolution(currentPermutation).copy(steps=steps, evaluatedSolutions=evaluatedSolutions)
    }
  }

  override def neighName: String = ""

  override def name: String = "greedy"+neighName
}
