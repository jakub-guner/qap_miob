package algorithms.localsearch

import qap.{QASolution, QAProblem}

/**
 * Created by JG on 01/12/16.
 */
class TabuSearch extends LocalSearch{
  val TABU_SIZE=20
  val STEPS_THRESHOLD=300;


  override def searchSolutionSpace(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, steps:Int, evaluatedSolutions:Int): QASolution = {
    val tabu=List.empty[Array[Int]]
    tabuSearch(problem, currentPermutation, currentResult, currentPermutation, currentResult, steps, evaluatedSolutions, tabu)
  }

  def tabuSearch(problem: QAProblem, currentPermutation: Array[Int], currentResult: Int, bestPermutation: Array[Int], bestResult: Int, steps:Int, evaluatedSolutions:Int, tabu:List[Array[Int]]): QASolution = {
    if(steps>=STEPS_THRESHOLD) {
      QASolution(bestPermutation).copy(steps = steps, evaluatedSolutions = evaluatedSolutions)
    }
    else{
      val nb = neighbourhood(currentPermutation)
      val (localBest, localBestResult) = nb
        .filter(!tabu.contains(_))
        .map {
          case permutation => (permutation, problem.calculateResult(permutation, currentResult))
        }
        .minBy {
          case (permutation, result: Int) => result
        }

      val newBest=if(localBestResult<bestResult) localBest else bestPermutation
      val newBestResult=Math.min(localBestResult, bestResult)

      tabuSearch(problem, localBest, localBestResult, newBest, newBestResult, steps = steps + 1, evaluatedSolutions = evaluatedSolutions + nb.size, (localBest::tabu).take(TABU_SIZE))
    }
  }

  override def neighName: String = ""

  override def name: String = "tabu"+neighName
}
