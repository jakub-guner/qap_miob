package algorithms

import qap.QASolution

/**
 * Created by JG on 13/10/16.
 */
trait Stopwatch {
  private val MILLION=Math.pow(10,6)

  def measureTime(calculate: => QASolution): QASolution ={
    val start = System.nanoTime()
    val solution = calculate
    val end = System.nanoTime()
    val miliseconds=((end-start)/MILLION).toInt
    solution.copy(runningTime = miliseconds)
  }

}
