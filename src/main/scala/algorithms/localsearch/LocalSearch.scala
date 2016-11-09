package algorithms.localsearch

import algorithms.Algorithm
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentSolution, QuadraticAssignmentProblem}
import QuadraticAssignmentEvaluator._
import algorithms.RandomAlgorithm._
import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 30/10/16.
 */
abstract class LocalSearch extends Algorithm with Neighbourhood{

  override def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    val initPermutation=randomPermutation(problem)
    val initResult=problem.calculateResult(initPermutation)

    searchSolutionSpace(problem, initPermutation, initResult).copy(initialValue = initResult)
  }

  def searchSolutionSpace(problem: QuadraticAssignmentProblem, currentPermutation: Array[Int], currentResult: Int, steps:Int=0, evaluatedSolutions:Int=0): QuadraticAssignmentSolution



}
