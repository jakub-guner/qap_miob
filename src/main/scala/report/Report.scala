package report

import algorithms.localsearch.{FullSwapNeighbourhood, GreedySearch, SteepestSearch}
import qap.QuadraticAssignmentProblem
/**
 * Created by JG on 15/10/16.
 */
object Report extends App{

  val chosenInstances=List("chr12b")
  val qaps=chosenInstances.map(QuadraticAssignmentProblem(_))


  val greedy=new GreedySearch with FullSwapNeighbourhood
  val sol1  = Experiment(greedy, qaps)
  println(sol1)

  val steepest=new SteepestSearch with FullSwapNeighbourhood
  val sol2  = Experiment(steepest, qaps)
  println(sol2)



}
