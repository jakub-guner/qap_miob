package algorithms

import qap.{QAProblem, QuadraticAssignmentEvaluator, QASolution}

/**
 * Created by JG on 15/10/16.
 */
abstract class Algorithm extends Stopwatch{

  def calculate(problem: QAProblem):QASolution

  def name:String

  def apply(problem: QAProblem):QASolution = {
    QuadraticAssignmentEvaluator(measureTime(calculate(problem)), problem)
  }


}


