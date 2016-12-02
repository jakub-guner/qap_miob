package algorithms.localsearch

/**
 * Created by JG on 30/10/16.
 */
trait Neighbourhood {

  def neighName:String

  def neighbourhood(solution: Array[Int]):Stream[Array[Int]] = {Stream.empty}

  def getAllIndexes(solution: Array[Int]): Range = {
    0 until solution.size
  }

  def swapElements(solution: Array[Int], middle: Int, right: Int, left:Int = -1): Array[Array[Int]] = {
    def tripleSwap(leftDest:Int):Array[Int]={
      val other=if(leftDest==middle) right else middle
      val clone = solution.clone()
      val temp=clone(leftDest)
      clone(leftDest)=clone(left)
      clone(left)=clone(other)
      clone(other)=temp
      clone
    }

    if(left == -1){
      val clone = solution.clone()
      val temp = clone(middle)
      clone(middle) = clone(right)
      clone(right) = temp
      Array(clone)
    }else{
      Array(tripleSwap(middle), tripleSwap(right))
  }
}

}
