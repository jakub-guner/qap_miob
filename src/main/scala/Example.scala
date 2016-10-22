/**
 * Created by JG on 15/10/16.
 */
object Example extends App{

  val qap=QuadraticAssignmentProblem("./qapdatsol/bur26a.dat")
  val solution=RandomAlgorithm(qap)
  println(solution.runningTime)


}
