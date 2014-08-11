package chessanalyzer

import org.scalatest.{Matchers, FlatSpec}
import chessanalyzer.model.{ChessBoardIndex, ChessBoard, ChessFigure}
import ChessFigure._

/**
 * Test suite to [[Analyzer]]
 * Checks correctness of the analyzing methods.
 */
class AnalyzerTest extends FlatSpec with Matchers {

  "Possible chess board" should "be in the number of 1 if chess board size is 1x1, and stands of the chess board one figure" in {
    Analyzer.possibleSetups(1, 1, List(King)) should be (Set(ChessBoard.create(1,1, Map(ChessBoardIndex(1,1) -> King))))
    Analyzer.possibleSetups(1, 1, List(Queen)) should be (Set(ChessBoard.create(1,1, Map(ChessBoardIndex(1,1) -> Queen))))
  }

  it should "be in the number of 0 if chess board size is 1x1, and stands of the chess board two figures" in {
    Analyzer.possibleSetups(1, 1, List(King, Queen)) should be (Set.empty)
    Analyzer.possibleSetups(1, 1, List(King, Knight)) should be (Set.empty)
  }

  it should "be in the number of 4 if chess board size is 3x2, and stands of the chess board following figure: King, Knight" in {
    val possibleBoard1 = buildChessBoard(3, 2) {
      Seq(
        None, Some(King),
        None, None,
        None, Some(Knight)
      )
    }
    val possibleBoard2 = buildChessBoard(3, 2) {
      Seq(
        None, Some(Knight),
        None, None,
        None, Some(King)
      )
    }
    val possibleBoard3 = buildChessBoard(3, 2) {
      Seq(
        Some(King), None,
        None, None,
        Some(Knight), None
      )
    }
    val possibleBoard4 = buildChessBoard(3, 2) {
      Seq(
        Some(Knight), None,
        None, None,
        Some(King), None
      )
    }

    Analyzer.possibleSetups(3, 2, List(King, Knight)) should be (Set(possibleBoard1, possibleBoard2, possibleBoard3, possibleBoard4))
  }

  it should "be in the number of 3 if chess board size is 3x3, and stands of the chess board following figure: King x 2, Rook" in {
    val possibleBoard1 = buildChessBoard(3, 3) {
      Seq(
        Some(King), None, Some(King),
        None, None, None,
        None, Some(Rook), None
      )
    }
    val possibleBoard2 = buildChessBoard(3, 3) {
      Seq(
        Some(King), None, None,
        None, None, Some(Rook),
        Some(King), None, None
      )
    }

    val possibleBoard3 = buildChessBoard(3, 3) {
      Seq(
        None, None, Some(King),
        Some(Rook), None, None,
        None, None, Some(King)
      )
    }

    val possibleBoard4 = buildChessBoard(3, 3) {
      Seq(
        None, Some(Rook), None,
        None, None, None,
        Some(King), None, Some(King)
      )
    }

    Analyzer.possibleSetups(3, 3, List(King, King, Rook)) should be (Set(possibleBoard1, possibleBoard2, possibleBoard3, possibleBoard4))

  }

  it should "be in the number of 8 if chess board size is 4x4, and stands of the chess board following figure: Rook x 2, Knight x 4" in {
    val possibleBoard1 = buildChessBoard(4, 4) {
      Seq(
        None, Some(Knight), None, Some(Knight),
        None, None, Some(Rook), None,
        None, Some(Knight), None, Some(Knight),
        Some(Rook), None, None, None
      )
    }
    val possibleBoard2 = buildChessBoard(4, 4) {
      Seq(
        None, Some(Knight), None, Some(Knight),
        Some(Rook), None, None, None,
        None, Some(Knight), None, Some(Knight),
        None, None, Some(Rook), None
      )
    }
    val possibleBoard3 = buildChessBoard(4, 4) {
      Seq(
        Some(Rook), None, None, None,
        None, Some(Knight), None, Some(Knight),
        None, None, Some(Rook), None,
        None, Some(Knight), None, Some(Knight)
      )
    }
    val possibleBoard4 = buildChessBoard(4, 4) {
      Seq(
        None, None, Some(Rook), None,
        None, Some(Knight), None, Some(Knight),
        Some(Rook), None, None, None,
        None, Some(Knight), None, Some(Knight)
      )
    }
    val possibleBoard5 = buildChessBoard(4, 4) {
      Seq(
        Some(Knight), None, Some(Knight), None,
        None, None, None, Some(Rook),
        Some(Knight), None, Some(Knight), None,
        None, Some(Rook), None, None
      )
    }
    val possibleBoard6 = buildChessBoard(4, 4) {
      Seq(
        Some(Knight), None, Some(Knight), None,
        None, Some(Rook), None, None,
        Some(Knight), None, Some(Knight), None,
        None, None, None, Some(Rook)
      )
    }
    val possibleBoard7 = buildChessBoard(4, 4) {
      Seq(
        None, Some(Rook), None, None,
        Some(Knight), None, Some(Knight), None,
        None, None, None, Some(Rook),
        Some(Knight), None, Some(Knight), None
      )
    }
    val possibleBoard8 = buildChessBoard(4, 4) {
      Seq(
        None, None, None, Some(Rook),
        Some(Knight), None, Some(Knight), None,
        None, Some(Rook), None, None,
        Some(Knight), None, Some(Knight), None
      )
    }
    val possibleBoards = Set(possibleBoard1, possibleBoard2, possibleBoard3, possibleBoard4, possibleBoard5, possibleBoard6, possibleBoard7, possibleBoard8)
    Analyzer.possibleSetups(4, 4, List(Rook, Rook, Knight, Knight, Knight, Knight)) should be (possibleBoards)
  }

  it should "be in the number of 0 for standard layout (chess board size is 8x8, and stands of the chess board following figure: 2xKing, 2xQueen, 4xRook, 4xBishop, 4xKnight)" in {
    Analyzer.possibleSetups(3, 3, List(Rook, Rook, Knight, Knight, Knight, Knight)) should be (Set.empty)
  }

  it should "be in the same number independently of figure order" in {
    Analyzer.possibleSetups(5, 5, List(King, Knight, Rook, Rook)) should be  (Analyzer.possibleSetups(5, 5, List(Rook, Knight, King, Rook)))
  }

  private def buildChessBoard(m: Int, n: Int)(figureInOrder: Seq[Option[ChessFigure]]) = {
    implicit def ordering = new ChessBoard.IndexOrdering(n)
    val tuples = for {
      i <- 1 to m
      j <- 1 to n
    } yield {
      val ind = (i-1)*n + j - 1
      ChessBoardIndex(i, j) -> figureInOrder(ind)
    }

    val boardSetup = tuples.collect {
      case (ind, Some(fig)) => ind -> fig
    }

    ChessBoard.create(m, n, boardSetup.toMap)
  }
}
