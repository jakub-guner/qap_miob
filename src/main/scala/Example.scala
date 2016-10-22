import algorithms.MinMaxProductAlgorithm
import qap.QuadraticAssignmentProblem

/**
 * Created by JG on 15/10/16.
 */
object Example extends App{

  val chosenInstances=List("chr12b")

  val qap=QuadraticAssignmentProblem("./qapdatsol/chr12b.dat")
  val solution=MinMaxProductAlgorithm(qap)

  solution.permutation.foreach(println)

}
