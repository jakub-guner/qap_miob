package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}

/**
 * Created by JG on 28/10/16.
 */
object Evaluator {

  def calculateResult(permutation: Array[Int], problem: QuadraticAssignmentProblem) : Int = {

    val flows=problem.flows
    val distances=problem.distances

    val products=for{
      facilityA <- 0 until problem.size
      facilityB <- 0 until problem.size
      locationA = permutation(facilityA)-1
      locationB = permutation(facilityB)-1
    } yield flows(facilityA)(facilityB) * distances(locationA)(locationB)

    products.sum
  }

  def evaluateSolution(solution: QuadraticAssignmentSolution, problem: QuadraticAssignmentProblem) = {
    val result=calculateResult(solution.permutation, problem)
    solution.copy(value=result)
  }

  def apply(solution: QuadraticAssignmentSolution, problem: QuadraticAssignmentProblem) = evaluateSolution(solution, problem)
}
