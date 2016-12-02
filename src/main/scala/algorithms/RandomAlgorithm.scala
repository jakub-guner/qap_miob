package algorithms

import qap.{QASolution, QAProblem}
/**
 * Created by JG on 15/10/16.
 */
object RandomAlgorithm{
  val random=new scala.util.Random()

  def apply(timeLimit:Int) = new RandomAlgorithm(timeLimit)

  def randomPermutation(problem: QAProblem): Array[Int] = {
    random.shuffle(1 to problem.size).toArray[Int]
  }
}

class RandomAlgorithm(val timeLimit:Int) extends Algorithm{
  import RandomAlgorithm.randomPermutation

  private val MILLION=Math.pow(10,6)

  def calculate(problem: QAProblem): QASolution = {
    QASolution(findBestPermutation(problem))
  }

  def findBestPermutation(problem: QAProblem): Array[Int]={
    val start = System.nanoTime()
    var bestPermutation=Array.empty[Int]
    var bestResult=Int.MaxValue

    def withinTimeLimit: Boolean = {
      ((System.nanoTime() - start) / MILLION).toInt < timeLimit
    }

    do {
      val newPerm=randomPermutation(problem)
      val newResult=problem.calculateResult(newPerm, bestResult)
      if(newResult<bestResult){
        bestPermutation=newPerm
        bestResult=newResult
      }
    }while(withinTimeLimit)
    bestPermutation
  }

  override def name: String = "random"
}
