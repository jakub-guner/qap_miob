package report

import algorithms.Algorithm
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
  * Created by tomasz on 06.11.16.
  */
object Experiment{
  def apply(alg: Algorithm, instance: QuadraticAssignmentProblem, numIterations: Int = 10):IndexedSeq[QuadraticAssignmentSolution] = {
    for( i <- 1 to numIterations) yield alg(instance)
  }
}
