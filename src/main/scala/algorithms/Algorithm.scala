package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}
import algorithms.Evaluator

/**
 * Created by JG on 15/10/16.
 */
abstract class Algorithm extends Stopwatch{

  def calculate(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution

  def apply(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution = {
    Evaluator(measureTime(calculate(problem)), problem)
  }


}


