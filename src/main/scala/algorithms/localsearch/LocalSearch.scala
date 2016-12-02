package algorithms.localsearch

import algorithms.Algorithm
import qap._
import QuadraticAssignmentEvaluator._
import algorithms.RandomAlgorithm._

/**
 * Created by JG on 30/10/16.
 */
abstract class LocalSearch extends Algorithm with Neighbourhood{

  override def calculate(problem: QAProblem): QASolution = {
    val initPermutation=randomPermutation(problem)
    val initResult=problem.calculateResult(initPermutation)

    searchSolutionSpace(problem, initPermutation, initResult).copy(initialValue = initResult)
  }

  def searchSolutionSpace(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int,
                          steps:Int=0, evaluatedSolutions:Int=0): QASolution



}
