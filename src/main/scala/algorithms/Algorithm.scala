package algorithms

import qap.{QuadraticAssignmentEvaluator, QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 15/10/16.
 */
abstract class Algorithm extends Stopwatch{

  def calculate(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution

  def name:String

  def apply(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution = {
    QuadraticAssignmentEvaluator(measureTime(calculate(problem)), problem)
  }


}


