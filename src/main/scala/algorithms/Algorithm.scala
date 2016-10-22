package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 15/10/16.
 */
abstract class Algorithm extends Stopwatch{

  def calculate(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution

  def apply(problem: QuadraticAssignmentProblem):QuadraticAssignmentSolution = {
    measureTime(calculate(problem))
  }


}


