package chessanalyzer

import chessanalyzer.model.ChessFigure
import chessanalyzer.model.ChessFigure._
import chessanalyzer.model.ChessBoardSize
import scala.Some

/**
 * Tool responsible for parsing and containing input parameters for [[Analyzer]]
 * @param input Input parameters as string
 */
class AnalyzerInputs(input: String) {

  private val VerticalSizeParam = 'h'
  private val HorizontalSizeParam = 'w'
  private val KingParam = 'K'
  private val BishopParam = 'B'
  private val KnightParam = 'N'
  private val QueenParam = 'Q'
  private val RookParam = 'R'
  private val ParallelParam = "-parallel"
  private val SequentialParam = "-sequential"
  private val OnlyNumberParam = "-onlyNumber"
  private val mainParamKey = Set(VerticalSizeParam, HorizontalSizeParam, KingParam, BishopParam, KnightParam, QueenParam, RookParam)
  private val additionalParam = Set(ParallelParam, SequentialParam, OnlyNumberParam)

  private def getQuantity(number: String, id: Char) = {
    val quantity = try {
      number.toInt
    } catch {
      case ex: NumberFormatException => throw new IllegalArgumentException(id + " parameter must contain integer value", ex)
    }
    if(quantity < 0) throw new IllegalArgumentException(id + " parameter must contain positive!! integer value")
    else quantity
  }

  private val args = input.split("^-| -").collect {
    case arg if arg.trim.nonEmpty => arg.trim
  }
  private val (extraArgs, mainArgs) = args.toSet.partition(_.head == '-')

  private val mainArgsMap = mainArgs.map {
    arg =>
      arg.head -> arg.tail.trim
  }.toMap

  private val incorrectArgs = (mainArgsMap.keySet &~ mainParamKey) ++ (extraArgs &~ additionalParam)
  if(incorrectArgs.nonEmpty) throw new IllegalArgumentException("Not recognized input parameters: " + incorrectArgs.mkString(", "))

  val boardSize: ChessBoardSize = {
    val (h, w) = (mainArgsMap.get(VerticalSizeParam), mainArgsMap.get(HorizontalSizeParam)) match {
      case (Some(hStr) , Some(wStr)) => (getQuantity(hStr, VerticalSizeParam), getQuantity(wStr, HorizontalSizeParam))
      case _ => throw new IllegalArgumentException("Parameters h and w are mandatory")
    }
    ChessBoardSize(h, w)
  }

  val figures: List[ChessFigure] = {
    def getFigures(id: Char, figure: ChessFigure) = {
      val number = mainArgsMap.get(id).map(numberStr => getQuantity(numberStr, id)).getOrElse(0)
      (1 to number).map(i => figure)
    }
    getFigures(QueenParam, Queen).toList ++
      getFigures(RookParam, Rook) ++
      getFigures(BishopParam, Bishop) ++
      getFigures(KnightParam, Knight) ++
      getFigures(KingParam, King)
  }

  val parallel: Boolean = extraArgs.contains(ParallelParam) || !extraArgs.contains(SequentialParam)

  val onlyNumber: Boolean = extraArgs.contains(OnlyNumberParam)

}
