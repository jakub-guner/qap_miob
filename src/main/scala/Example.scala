import algorithms.localsearch.{SteepestSearch, FullSwapNeighbourhood, GreedySearch}
import algorithms.{RandomAlgorithm, Evaluator, MinMaxProductAlgorithm}
import qap.QuadraticAssignmentProblem

/**
 * Created by JG on 15/10/16.
 */
object Example extends App{

  val chosenInstances=List("chr12b")
  val perm=Array(5, 7, 1, 10, 11, 3, 4, 2, 9, 6, 12, 8)

  val qap=QuadraticAssignmentProblem("./qapdatsol/chr12b.dat")

  val greedy=new GreedySearch with FullSwapNeighbourhood
  val sol1=greedy(qap)
  println(sol1)

  val steepest=new SteepestSearch with FullSwapNeighbourhood
  val sol2=steepest(qap)
  println(sol2)



}
