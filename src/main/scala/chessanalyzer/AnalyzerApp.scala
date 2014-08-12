package chessanalyzer

import com.typesafe.scalalogging.LazyLogging
import chessanalyzer.model.{ChessFigure, ChessBoard}
import java.util.Date

/**
 * Provide main method
 */
object AnalyzerApp extends LazyLogging {

  def main (args: Array[String]) {
    val input = new AnalyzerInputs(args.mkString(" "))
    withLogging(input) {
      if(input.parallel) Analyzer.possibleSetups(input.boardSize.m, input.boardSize.m, input.figures)
      else Analyzer.possibleSetupsSequential(input.boardSize.m, input.boardSize.m, input.figures)
    }
  }


  /**
   * Provide simple logging and printing result functionality
   * @param m horizontal size of chess board
   * @param n vertical size of chess board
   * @param figures figures for which app is running
   * @param parallel if set then parallel version of algorithm is running, otherwise sequential
   * @param onlyNumber if set then solution will not printing (only number of solution will be printing)
   * @param job function which return possible solutions
   * @return possible results
   */
  def withLogging(m: Int, n: Int, figures: List[ChessFigure], parallel: Boolean = true, onlyNumber: Boolean = false)(job: => Set[ChessBoard]): Set[ChessBoard] = {
    val startTime = new Date() //to simple time logging java.util.Date is completely sufficient
    logger.info("Start analyze " + m + "x" +  n + " chess board with figures: " + figures.mkString(", "))
    logger.info("Chosen algorithm for analyzing is " + (if(parallel) "parallel" else "sequential"))

    //extra logging "hello i'm still alive - not turn off me" for big solutions
    //ugly solution but acceptable for this purpose
    var stillLogging = true
    val stillAlive = new Thread {
      override def run() {
        while(stillLogging) {
          Thread.sleep(10000)
          logger.info("Calculating solution in progress..............")
        }
      }
    }
    stillAlive.start()

    val result = job
    stillLogging = false

    if(onlyNumber) {
      logger.info("Possible solution will not show, because --onlyNumber parameter was specified ")

    } else {
      result.zipWithIndex.foreach {
        case (solution, index) =>
          logger.info("SOLUTION " + index + "\n" + solution)
          logger.info("\n************************************\n")
      }
    }


    logger.info("Total allowed setups is: " + result.size)
    val endTime = new Date()
    val durationMillis = endTime.getTime - startTime.getTime
    logger.info("Total time: " + durationMillis + " ms (~ " + durationMillis/1000 + " s)")
    result
  }

  private def withLogging(inputs: AnalyzerInputs)(job: => Set[ChessBoard]): Set[ChessBoard] = {
    withLogging(inputs.boardSize.m, inputs.boardSize.n, inputs.figures, inputs.parallel, inputs.onlyNumber)(job)
  }
}
