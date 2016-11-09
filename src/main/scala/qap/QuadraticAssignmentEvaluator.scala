package qap

/**
 * Created by JG on 28/10/16.
 */
object QuadraticAssignmentEvaluator {

  def evaluateSolution(solution: QuadraticAssignmentSolution, problem: QuadraticAssignmentProblem) = {
    val result=problem.calculateResult(solution.permutation)
    solution.copy(value=result)
  }

  def apply(solution: QuadraticAssignmentSolution, problem: QuadraticAssignmentProblem) = evaluateSolution(solution, problem)
}
