package algorithms.localsearch

/**
 * Created by JG on 29/10/16.
 */
trait FullSwapNeighbourhood extends Neighbourhood{

  override def neighbourhood(solution: Array[Int]):Stream[Array[Int]]={
    val allPairs=for{
      left <- getAllIndexes(solution)
      right <- getAllIndexes(solution) if left<right
    } yield (left, right)

    allPairs
      .toStream
      .map{
        case (left, right) => swapElements(solution, left, right)
      }
  }
}
