import algorithms.localsearch.{FullSwapNeighbourhood, GreedySearch, SteepestSearch}
import algorithms.{Evaluator, MinMaxProductAlgorithm, RandomAlgorithm}
import com.sun.javafx.applet.ExperimentalExtensions
import qap.QuadraticAssignmentProblem
import algorithms.Experiment
import algorithms.RandomAlgorithm
/**
 * Created by JG on 15/10/16.
 */
object Example extends App{

  val chosenInstances=List("chr12b")
  val perm=Array(5, 7, 1, 10, 11, 3, 4, 2, 9, 6, 12, 8)

  val qap=QuadraticAssignmentProblem("./qapdatsol/chr12b.dat")

  val greedy=new GreedySearch with FullSwapNeighbourhood
  val steepest=new SteepestSearch with FullSwapNeighbourhood

  val numIterations = 300

  val sol1  = Experiment(greedy, qap, numIterations)
  println("Sol1:" + sol1)
  val sol2  = Experiment(steepest, qap, numIterations)
  println("Sol2:" + sol2)
  val sol3 = Experiment(RandomAlgorithm, qap, numIterations)
  println("Sol3:" + sol3)
  val sol4 = Experiment(MinMaxProductAlgorithm, qap, numIterations)
  println("Sol4:" + sol4)


}
