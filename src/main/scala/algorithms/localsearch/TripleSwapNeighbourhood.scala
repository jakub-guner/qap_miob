package algorithms.localsearch

/**
 * Implements triple swap, where each unique triple of indices leads to two neighbouring permutations.
 * Also includes the double swap.
 *
 * Created by JG on 11/11/16.
 */
trait TripleSwapNeighbourhood extends Neighbourhood{

  override def neighName:String = "-3swap"

  override def neighbourhood(solution: Array[Int]):Stream[Array[Int]]={
    val allTriplets=for{
      left <- -1 until solution.length
      middle <- getAllIndexes(solution) if middle > left
      right <-  getAllIndexes(solution) if right > middle
    } yield (left, middle, right)

    allTriplets
      .toStream
      .flatMap{
      case (-1, middle, right) => swapElements(solution, middle, right)
      case (left, middle,right) => swapElements(solution, middle, right, left)

    }
  }

}
