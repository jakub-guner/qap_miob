package report

import algorithms.Algorithm
import qap.{QASolution, QAProblem}

/**
  * Created by tomasz on 06.11.16.
  */
object Experiment{
  def apply(alg: Algorithm, instance: QAProblem, numIterations: Int = 10):IndexedSeq[QASolution] = {
    for( i <- 1 to numIterations) yield alg(instance)
  }
}
