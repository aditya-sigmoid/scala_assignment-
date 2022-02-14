import scala.math.BigDecimal.double2bigDecimal

object ques1 extends App{

  val arr=(0.049d to 100.050d by 0.050d).toArray  // making an array with the last values of every range

  val n = arr.size    // calculating the size of array formed above
  print("Enter the no of values you want to check \n")
  var values=scala.io.StdIn.readInt()               // taking input from user
  while(values>0)
  {
    print("Enter the value ")
    val input_value=scala.io.StdIn.readDouble()    // value to be checked

    if(input_value>100.049)                  // edge test case
      print("The no you enter is out of range .. Please enter number less than 100.049 \n")

    else {
      val answer = binary_search(arr, n, BigDecimal(input_value))  // function call
      print("the range is ")
      val range2 = arr(answer)    // calculating range by index of array
      var range1 = range2 - 0.049
      println(s"${range1} - ${range2} contains $input_value ")    // printing the required range
      values = values - 1
    }
  }

  // it gives the index of array that is equal to or just above the value entered by user
  def binary_search(arr:Array[BigDecimal],size:Int,value:BigDecimal):Int={

    var start=0
    var end=size-1
    while(start<end)
    {
      val mid = (start + (end - start) / 2)
      if(arr(mid)>=value)
        end=mid
      else
        start=mid+1
    }
    if(start<size && arr(start)<value)
      start+=1

    return start

  }
}
