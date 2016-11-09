package report

import algorithms.Algorithm
import qap.QuadraticAssignmentProblem

/**
  * Created by tomasz on 06.11.16.
  */
object Experiment{
  def apply(alg: Algorithm, instances: List[QuadraticAssignmentProblem], numIterations: Int = 10) = {
    instances.flatMap{instance =>
      for( i <- 1 to numIterations) yield alg(instance)
    }
  }
}
