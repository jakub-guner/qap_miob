package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 15/10/16.
 */
object RandomAlgorithm extends Algorithm{


  val random=new scala.util.Random()

  def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    QuadraticAssignmentSolution(randomPermutation(problem))
  }

  def randomPermutation(problem: QuadraticAssignmentProblem): Array[Int] = {
    random.shuffle(1 to problem.size).toArray[Int]
  }
}
