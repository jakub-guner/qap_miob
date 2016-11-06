import algorithms.localsearch.{FullSwapNeighbourhood, GreedySearch, SteepestSearch}
import algorithms.{Evaluator, MinMaxProductAlgorithm, RandomAlgorithm}
import com.sun.javafx.applet.ExperimentalExtensions
import qap.QuadraticAssignmentProblem
import algorithms.Experiment
/**
 * Created by JG on 15/10/16.
 */
object Example extends App{

  val chosenInstances=List("chr12b")
  val perm=Array(5, 7, 1, 10, 11, 3, 4, 2, 9, 6, 12, 8)

  val qap=QuadraticAssignmentProblem("./qapdatsol/chr12b.dat")

  val greedy=new GreedySearch with FullSwapNeighbourhood
  val sol1  = Experiment(greedy, qap)
  println(sol1)

  val steepest=new SteepestSearch with FullSwapNeighbourhood
  val sol2  = Experiment(steepest, qap)
  println(sol2)



}
