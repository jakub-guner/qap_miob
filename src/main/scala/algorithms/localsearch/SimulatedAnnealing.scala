package algorithms.localsearch



import qap.{QASolution, QAProblem}

import scala.util.Random


/**
 * Created by JG on 23/11/16.
 */
class SimulatedAnnealing extends LocalSearch{

  val ALFA=0.99
  val PROBABILITY=0.3
  val SAMPLE_SIZE=50
  val TEMP_THRESHOLD=0.5

  override def searchSolutionSpace(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps: Int, evaluatedSolutions: Int): QASolution = {

    val temperatureInit = initiliazeTemperature(problem, currentPermutation, currentResult)
    var epochIndexInit=0 //k
    val epochSize=neighSize(currentPermutation)//Stream.continually(20) //var?? L // liczba prob w epoce
    /*
    * W każdej epoce: rozmiar/liczba prób, przejrzanych sąsiedztw
    * W każdej epoce: oblicz temperature
    * */

    simulateAnnealing(problem, currentPermutation, currentResult, steps, evaluatedSolutions, temperatureInit, epochIndexInit)
  }

  def simulateAnnealing(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps: Int, evaluatedSolutions: Int, temperature:Double, epochIndex:Int): QASolution = {
    if(temperature<TEMP_THRESHOLD) {
      QASolution(currentPermutation).copy(steps = steps, evaluatedSolutions = evaluatedSolutions)
    } else{
      val coveredSolutions = 1

      val randomNeighbourIndex=Random.nextInt(neighSize(currentPermutation))
      val (randomNeighbour, randomNeighbourResult) = neighbourhood(currentPermutation)
        .zipWithIndex
        .filter{
          case(neighbour, index)=> randomNeighbourIndex == index
        }
        .map {
          case (neighbour, index) => (neighbour, problem.calculateResult(neighbour, currentResult))
        }
        .head

      def acceptIfBetter: Boolean = {
        randomNeighbourResult < currentResult
      }

      def shouldAcceptIfWorse: Boolean = {
        val acceptanceProbability = Math.exp((currentResult - randomNeighbourResult) / temperature)
        Math.random() <= acceptanceProbability
      }

      val (nextSolution, nextSolutionResult)=if(acceptIfBetter || shouldAcceptIfWorse)
        (randomNeighbour, randomNeighbourResult)
      else
        (currentPermutation, currentResult)

      simulateAnnealing(problem, nextSolution, nextSolutionResult, steps = steps + 1, evaluatedSolutions + coveredSolutions, ALFA*temperature, epochIndex+1)
    }
  }

  def initiliazeTemperature(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int)={
    val indexesOfRandomSample=new scala.util.Random().shuffle((0 until neighSize(currentPermutation)).toList).take(SAMPLE_SIZE)
    val sample=neighbourhood(currentPermutation)
      .zipWithIndex
      .filter{
        case(neighbour, index)=> indexesOfRandomSample.contains(index)
      }
      .map{
        case(neighbour, index) => currentResult - problem.calculateResult(neighbour, currentResult)
      }
      .map(Math.abs)

    val delta=sample.sum/sample.size
    (-1)*delta/Math.log(PROBABILITY)

  }

  override def name: String = "simAnn"+neighName

  override def neighName: String = ""
}
