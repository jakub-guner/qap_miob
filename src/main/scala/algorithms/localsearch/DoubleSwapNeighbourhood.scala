package algorithms.localsearch

/**
 * Created by JG on 29/10/16.
 */
trait DoubleSwapNeighbourhood extends Neighbourhood{

  override def neighName:String = "-2swap"

  override def neighSize(solution: Array[Int]):Int={
    val n=solution.length
    n*(n-1)/2
  }

  override def neighbourhood(solution: Array[Int]):Stream[Array[Int]]={
    val allPairs=for{
      left <- getAllIndexes(solution)
      right <- getAllIndexes(solution) if left<right
    } yield (left, right)

    allPairs
      .toStream
      .flatMap{
        case (left, right) => swapElements(solution, left, right)
      }
  }
}
