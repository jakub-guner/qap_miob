package algorithms

import qap.{QuadraticAssignmentSolution, QuadraticAssignmentProblem}
/**
 * Created by JG on 15/10/16.
 */
object RandomAlgorithm extends Algorithm{
  private val MILLION=Math.pow(10,6)

  private var _timeLimit=0

  def timeLimit_= (value:Int):Unit = _timeLimit = value


  val random=new scala.util.Random()

  def calculate(problem: QuadraticAssignmentProblem): QuadraticAssignmentSolution = {
    QuadraticAssignmentSolution(findBestPermutation(problem))
  }

  def findBestPermutation(problem: QuadraticAssignmentProblem): Array[Int]={
    val start = System.nanoTime()
    var bestPermutation=Array.empty[Int]
    var bestResult=Int.MaxValue

    def withinTimeLimit: Boolean = {
      ((System.nanoTime() - start) / MILLION).toInt < _timeLimit
    }

    while (withinTimeLimit){
      val newPerm=randomPermutation(problem)
      val newResult=problem.calculateResult(newPerm)
      if(newResult<bestResult)
        bestPermutation=newPerm
    }
    bestPermutation
  }

  def randomPermutation(problem: QuadraticAssignmentProblem): Array[Int] = {
    random.shuffle(1 to problem.size).toArray[Int]
  }
}
