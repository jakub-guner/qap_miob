package qap

/**
 * Created by JG on 28/10/16.
 */
object QuadraticAssignmentEvaluator {

  def evaluateSolution(solution: QASolution, problem: QAProblem) = {
    val result=problem.calculateResult(solution.permutation)
    solution.copy(value=result)
  }

  def apply(solution: QASolution, problem: QAProblem) = evaluateSolution(solution, problem)
}
