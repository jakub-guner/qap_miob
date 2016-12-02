package algorithms.localsearch

import java.util.NoSuchElementException

import algorithms.{RandomAlgorithm, Algorithm}
import qap.{QuadraticAssignmentEvaluator, QASolution, QAProblem}
import QuadraticAssignmentEvaluator._
import algorithms.RandomAlgorithm._
//import qap.{QuadraticAssignmentEvaluator, QASolution, QAProblem}

/**
 * Created by JG on 30/10/16.
 */
class GreedySearch extends LocalSearch{

  def searchSolutionSpace(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps:Int, evaluatedSolutions:Int): QASolution = {
    try {
      var coveredSolutions=0
      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
        .map{
          case permutation => (permutation, problem.calculateResult(permutation, currentResult))
        }
        .filter {
          case (permutation, result:Int) => {coveredSolutions+=1; result < currentResult}
        }.head

      searchSolutionSpace(problem, newPermutation, newResult, steps=steps+1, evaluatedSolutions+coveredSolutions)
    }catch{
      case nse:NoSuchElementException => QASolution(currentPermutation).copy(steps=steps, evaluatedSolutions=evaluatedSolutions)
    }
  }

  override def neighName: String = ""

  override def name: String = "greedy"+neighName
}
