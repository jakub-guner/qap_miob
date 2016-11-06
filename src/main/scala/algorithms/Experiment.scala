package algorithms

import qap.QuadraticAssignmentProblem

/**
  * Created by tomasz on 06.11.16.
  */
object Experiment{
  def apply(alg : Algorithm, qap : QuadraticAssignmentProblem, numIterations : Int = 30) = {
    val results = for( i <- 1 to numIterations) yield { alg(qap).value }
    (results.min, results.sum / results.length, results.max)
  }
}
