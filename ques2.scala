import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.collection.immutable.ListMap
import scala.collection.immutable.Map
import scala.collection.immutable.Vector


object ques2 extends App {

  val rows = ArrayBuffer[Array[String]]()       // empty data structure

  val bufferedSource = io.Source.fromFile("/Users/adityagupta/IdeaProjects/scala_assignment/src/data.csv")  // accessing data from a csv file
  for (line <- bufferedSource.getLines.drop(1)) {
    rows += line.split(",").map(_.trim)       // store in row separated by comma and remove extra white spaces
  }

  val score = scala.collection.mutable.Map.empty[String,Int]       // empty maps for score wickets and rank
  val wickets = scala.collection.mutable.Map.empty[String,Int]
  val rank = scala.collection.mutable.Map.empty[String,Int]

  var max=0
  var i=0
  // loop to calculate index of max score

  // player with max runs

  for (j <- 0 to rows.length-1){

    score+=(rows(j)(1)->rows(j)(4).toInt)
    wickets+=(rows(j)(1)->rows(j)(5).toInt)
    rank+=(rows(j)(1)->(rows(j)(5).toInt*5 + rows(j)(4).toInt*.05).toInt)

    if (rows(j)(4).toInt > max)
    {
      max=rows(j)(4).toInt
      i=j
    }
  }
  println( "Player with the best highest run scored is \n")
  println(rows(i)(1)+" --->  "+rows(i)(4))



  // 2nd  top 5 players with highest scores

  println("\nTop 5 players by run scored : ")
  var result = ListMap(score.toSeq.sortWith(_._2 > _._2):_*) // sort the map according to values and in descending order

  result.take(5).foreach
  {
    case (key, value) => println(key + " scored " + value)
  }

  // 3rd top 5 players with highest wicket

    println("\nTop 5 players by wicket taken :")

    result= ListMap(wickets.toSeq.sortWith(_._2 > _._2):_*)
    result.take(5).foreach
    {
      case (key,value) => println (key + " " + value)
    }

    // rank each player according to overall performance

    println("\n Ranking players with overall performance")
    result=ListMap(rank.toSeq.sortWith(_._2 > _._2):_*)
    var position=1
    result.foreach
    {
      case (key, value) => println(s"Player : ${key}   Rank: ${position}")
        position+=1
    }

}

