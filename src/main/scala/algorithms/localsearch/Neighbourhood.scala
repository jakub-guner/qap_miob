package algorithms.localsearch

/**
 * Created by JG on 30/10/16.
 */
trait Neighbourhood {

  def neighbourhood(solution: Array[Int]):Stream[Array[Int]] = {Stream.empty}

  def getAllIndexes(solution: Array[Int]): Range = {
    0 until solution.size
  }

  def swapElements(solution: Array[Int], left: Int, right: Int): Array[Int] = {
    val clone = solution.clone()
    val temp = clone(left)
    clone(left) = clone(right)
    clone(right) = temp
    clone
  }

}
