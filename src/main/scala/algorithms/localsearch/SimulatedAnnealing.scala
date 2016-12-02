package algorithms.localsearch

import java.util.NoSuchElementException

import qap.{QASolution, QAProblem}

/**
 * Created by JG on 23/11/16.
 */
class SimulatedAnnealing extends LocalSearch{

  val ALFA=0.8

  override def searchSolutionSpace(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps: Int, evaluatedSolutions: Int): QASolution = {

    val temperatureInit = 100
    var epochIndex=0 //k
    val epochSize=Stream.continually(20) //var?? L // liczba prob w epoce
    /*
    * W każdej epoce: rozmiar/liczba prób, przejrzanych sąsiedztw
    * W każdej epoce: oblicz temperature
    * */

    simulateAnnealing(problem, currentPermutation, currentResult, steps, evaluatedSolutions, temperatureInit)
  }

  def simulateAnnealing(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps: Int, evaluatedSolutions: Int, temperature:Double): QASolution = {
    try {
      var coveredSolutions = 0

      val (newPermutation, newResult) =
        neighbourhood(currentPermutation)
          .map {
          case permutation => (permutation, problem.calculateResult(permutation, currentResult))
        }
          .filter {
          case (permutation, result: Int) => {
            def acceptIfBetter: Boolean = {
              result < currentResult
            }

            def shouldAcceptIfWorse: Boolean = {
              val acceptanceProbability = Math.exp((currentResult - result) / temperature)
              acceptanceProbability >= Math.random()
            }

            coveredSolutions += 1;

            acceptIfBetter || shouldAcceptIfWorse
          }
        }.head

      simulateAnnealing(problem, newPermutation, newResult, steps = steps + 1, evaluatedSolutions + coveredSolutions, ALFA*temperature)
    } catch {
      //warunek stopu
      case nse: scala.NoSuchElementException => QASolution(currentPermutation).copy(steps = steps, evaluatedSolutions = evaluatedSolutions)
    }
  }

  override def name: String = ""

  override def neighName: String = "simAnn"+neighName
}
