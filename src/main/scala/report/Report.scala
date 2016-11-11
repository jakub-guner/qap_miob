package report

import java.io.PrintWriter

import algorithms.localsearch.{TripleSwapNeighbourhood, DoubleSwapNeighbourhood, GreedySearch, SteepestSearch}
import algorithms.{RandomAlgorithm, MinMaxProductAlgorithm}
import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}
/**
 * Created by JG on 15/10/16.
 */
object Report extends App{

//  "tai25b"
  //Report parameters

  val chosenInstances=List(
    ("chr12b", 30), ("had12", 30), ("esc32f", 175), ("tai80a", 61*1000)//,
//  ("tho150", 30), ("tai256c", 30),
    //  ("wil100", 408*1000),
                          )

  def chosenAlgorithms(timeLimit:Int)=RandomAlgorithm(timeLimit) :: standardAlgorithms

  val standardAlgorithms = MinMaxProductAlgorithm ::
    new GreedySearch with DoubleSwapNeighbourhood ::
    new GreedySearch with TripleSwapNeighbourhood ::
    new SteepestSearch with DoubleSwapNeighbourhood ::
    new SteepestSearch with TripleSwapNeighbourhood :: Nil


  

  //Experiment
  println(header)
  val results=for {
    (qapInstance:String, timeLimit:Int) <- chosenInstances
    qap=QuadraticAssignmentProblem(qapInstance)
    algorithm <-chosenAlgorithms(timeLimit)
    solution <- Experiment(algorithm, qap)
  } yield {
    val line=formatOutputLine(qapInstance, algorithm.name, solution)
    print(line)
    line
  }

  new PrintWriter("results.csv") { write(header); results.foreach(write); close}

  def header="instance, algorithm, value, runningTime, initialValue, steps, evaluatedSolutions\n"

  def formatOutputLine(instance:String, algorithm: String, sol:QuadraticAssignmentSolution):String={
    s"$instance, $algorithm, ${sol.value}, ${sol.runningTime}, ${sol.initialValue}, ${sol.steps}, ${sol.evaluatedSolutions} \n"
  }

  
}
