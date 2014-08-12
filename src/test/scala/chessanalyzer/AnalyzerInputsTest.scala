package chessanalyzer

import org.scalatest.{Matchers, FlatSpec}
import chessanalyzer.model.ChessFigure._
import chessanalyzer.model.ChessBoardSize

/**
 * Test suite to [[AnalyzerInputs]]
 * Checks correctness of parsing input parameters
 */
class AnalyzerInputsTest extends FlatSpec with Matchers {

  "Input" should "be board size 7x7 with figures: 2 Queens, 2 Bishops, 2 King, 1 Knight, 0 Rooks - sequential method - full output" in {
    val inputString = "-h 7 -w 7 -Q 2 -B 2 -K 2 -N 1 --sequential"
    val input = new AnalyzerInputs(inputString)

    input.boardSize should be (ChessBoardSize(7, 7))
    input.figures.filter(_ == King).size should be (2)
    input.figures.filter(_ == Queen).size should be (2)
    input.figures.filter(_ == Bishop).size should be (2)
    input.figures.filter(_ == Knight).size should be (1)
    input.figures.filter(_ == Rook).size should be (0)
    input.parallel should be (false)
    input.onlyNumber should be (false)
  }

  it should "be board size 5x4 with figures: 1 Queens, 2 Bishops, 1 King, 0 Knights, 1 Rook - sequential method - full output" in {
    val inputString = "-h 5 -w 4  -B 2 -Q 1  -K 1 -R 1"
    val input = new AnalyzerInputs(inputString)

    input.boardSize should be (ChessBoardSize(5, 4))
    input.figures.filter(_ == King).size should be (1)
    input.figures.filter(_ == Queen).size should be (1)
    input.figures.filter(_ == Bishop).size should be (2)
    input.figures.filter(_ == Knight).size should be (0)
    input.figures.filter(_ == Rook).size should be (1)
    input.parallel should be (false)
    input.onlyNumber should be (false)
  }

  it should "be board size 3x3 with figures: 0 Queens, 0 Bishops, 0 King, 0 Knights, 0 Rook - sequential method - full output" in {
    val inputString = "-h 3 -w 3  -B 0 "
    val input = new AnalyzerInputs(inputString)

    input.boardSize should be (ChessBoardSize(3, 3))
    input.figures.filter(_ == King).size should be (0)
    input.figures.filter(_ == Queen).size should be (0)
    input.figures.filter(_ == Bishop).size should be (0)
    input.figures.filter(_ == Knight).size should be (0)
    input.figures.filter(_ == Rook).size should be (0)
    input.parallel should be (false)
    input.onlyNumber should be (false)
  }

  it should "be board size 5x4 with figures: 2 Queens, 4 Bishops, 0 Kings, 0 Knights, 0 Rooks - parallel method - full output" in {
    val inputString = "-h5 -w4  -B4 -Q2 --parallel"
    val input = new AnalyzerInputs(inputString)

    input.boardSize should be (ChessBoardSize(5, 4))
    input.figures.filter(_ == King).size should be (0)
    input.figures.filter(_ == Queen).size should be (2)
    input.figures.filter(_ == Bishop).size should be (4)
    input.figures.filter(_ == Knight).size should be (0)
    input.figures.filter(_ == Rook).size should be (0)
    input.parallel should be (true)
    input.onlyNumber should be (false)
  }

  it should "be board size 5x4 with figures: 2 Queens, 4 Bishops, 0 Kings, 0 Knights, 0 Rooks - sequential method - only number output" in {
    val inputString = "-h5 -w4  -B4 -Q2 --onlyNumber"
    val input = new AnalyzerInputs(inputString)

    input.boardSize should be (ChessBoardSize(5, 4))
    input.figures.filter(_ == King).size should be (0)
    input.figures.filter(_ == Queen).size should be (2)
    input.figures.filter(_ == Bishop).size should be (4)
    input.figures.filter(_ == Knight).size should be (0)
    input.figures.filter(_ == Rook).size should be (0)
    input.parallel should be (false)
    input.onlyNumber should be (true)
  }

  it should "be invalid when unexpected param founded" in {
    val inputString = "-h5 -w4  -suprise -B4 -Q2 --onlyNumber"

    a [IllegalArgumentException] should be thrownBy {
      new AnalyzerInputs(inputString)
    }
  }

  it should "be invalid board size not founed" in {
    val inputString = "-h5 -B4 -Q2 --onlyNumber"
    val inputString2 = "-w 5 -B 4 -Q 2 --onlyNumber"

    a [IllegalArgumentException] should be thrownBy {
      new AnalyzerInputs(inputString)
    }

    a [IllegalArgumentException] should be thrownBy {
      new AnalyzerInputs(inputString2)
    }
  }

  it should "be invalid when number of figure is not a number" in {
    val inputString = "-h5 -w4 -BV -Q2 --onlyNumber"

    a [IllegalArgumentException] should be thrownBy {
      new AnalyzerInputs(inputString)
    }
  }

  it should "be invalid when number of figure is lowe then zero" in {
    val inputString = "-h5 -w4 -B3 -Q-4 --onlyNumber"

    a [IllegalArgumentException] should be thrownBy {
      new AnalyzerInputs(inputString)
    }
  }
  
}
