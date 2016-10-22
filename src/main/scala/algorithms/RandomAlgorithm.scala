package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 15/10/16.
 */
object RandomAlgorithm extends Algorithm{


  val random=new scala.util.Random()

  def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    QuadraticAssignmentSolution(random.shuffle(1 to 10).toArray[Int])
  }
}
