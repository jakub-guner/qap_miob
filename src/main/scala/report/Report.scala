package report

import algorithms.localsearch.{FullSwapNeighbourhood, GreedySearch, SteepestSearch}
import algorithms.{RandomAlgorithm, MinMaxProductAlgorithm}
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}
/**
 * Created by JG on 15/10/16.
 */
object Report extends App{

  //Report parameters
  val chosenInstances=List("chr12b")
  RandomAlgorithm.timeLimit_=(30)
  val chosenAlgorithms=
            RandomAlgorithm ::
            MinMaxProductAlgorithm ::
            new GreedySearch with FullSwapNeighbourhood ::
            new SteepestSearch with FullSwapNeighbourhood :: Nil
  

  //Experiment
  println(header)
  for {
    qapInstance <- chosenInstances
    qap=QuadraticAssignmentProblem(qapInstance)
    algorithm <-chosenAlgorithms
    solution <- Experiment(algorithm, qap)
  } yield println(formatOutputLine(qapInstance, algorithm.name, solution))

  def header="instance, algorithm, value, runningTime, initialValue, steps, evaluatedSolutions"

  def formatOutputLine(instance:String, algorithm: String, sol:QuadraticAssignmentSolution):String={
    s"$instance, $algorithm, ${sol.value}, ${sol.runningTime}, ${sol.initialValue}, ${sol.steps}, ${sol.evaluatedSolutions}, "
  }

  
}
