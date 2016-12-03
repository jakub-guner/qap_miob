package report

import java.io.PrintWriter

import algorithms.localsearch._
import algorithms.{Algorithm, RandomAlgorithm, MinMaxProductAlgorithm}
import qap.{QASolution, QAProblem}
/**
 * Created by JG on 15/10/16.
 */
object Report extends App{

  val chosenInstances=List(
//      ("chr12b", 30),
//      ("had12", 30),
//      ("bur26a", 500),
//      ("esc32f", 175),
//      ("tai40b", 30*1000),
//      ("tai50b", 10*1000),
      ("tai60a", 50*1000)//,
//      ("tai80a", 61*1000)
  )

  def chosenAlgorithms(timeLimit:Int)= //RandomAlgorithm(timeLimit) ::
    standardAlgorithms

  val standardAlgorithms =
//    new GreedySearch with DoubleSwapNeighbourhood ::
//    new SimulatedAnnealing with TripleSwapNeighbourhood ::
//  new TabuSearch with DoubleSwapNeighbourhood::
    new TabuSearch with TripleSwapNeighbourhood::
//    MinMaxProductAlgorithm ::
//    new GreedySearch with DoubleSwapNeighbourhood ::
//    new GreedySearch with TripleSwapNeighbourhood ::
//    new SteepestSearch with DoubleSwapNeighbourhood ::
//    new SteepestSearch with TripleSwapNeighbourhood ::
     Nil


  def zad34algorithms(a:Int)=
    new GreedySearch with TripleSwapNeighbourhood ::
    new SteepestSearch with TripleSwapNeighbourhood :: Nil

  //Zad 2
  normalExperiment(chosenInstances, chosenAlgorithms = chosenAlgorithms,1)

  //Zad 2.3
//  normalExperiment(List(("tai60a", 1000*1000)), chosenAlgorithms = chosenAlgorithms, 5)

  //Zad 3/4
  //  normalExperiment(List(("chr20a", 0), ("rou20",0), ("had12", 30), ("bur26a", 500), ("esc32f", 175)), chosenAlgorithms = zad34algorithms, 350)

  def normalExperiment(instances:List[(String, Int)], chosenAlgorithms:Int=>List[Algorithm], iterations:Int=10)={
    println(header)
    val results=for {
      (qapInstance:String, timeLimit:Int) <- instances
      qapProblem=QAProblem(qapInstance)
      algorithm <-chosenAlgorithms(timeLimit)
      solution <- Experiment(algorithm, qapProblem, iterations)
    } yield {
        val line=formatOutputLine(qapInstance, algorithm.name, solution)
        print(line)
        line
      }

    new PrintWriter("results"+System.currentTimeMillis()+".csv") { write(header); results.foreach(write); close}
  }



  def header="instance, algorithm, value, runningTime, initialValue, steps, evaluatedSolutions\n"
  def header2="instance, algorithm, value, finalPermutation, runningTime, initialValue, steps, evaluatedSolutions\n"

  def formatOutputLine(instance:String, algorithm: String, sol:QASolution):String={
    s"$instance, $algorithm, ${sol.value}, ${sol.runningTime}, ${sol.initialValue}, ${sol.steps}, ${sol.evaluatedSolutions} \n"
  }
  def formatOutputLine2(instance:String, algorithm: String, sol:QASolution):String={
    s"$instance,$algorithm, ${sol.value}, ${sol.permutation.mkString("[", " ", "]")} ,${sol.runningTime}, ${sol.initialValue}, ${sol.steps}, ${sol.evaluatedSolutions} \n"
  }

  
}
