package chessanalyzer.model.board

/**
 * Describes chess board with M x N dimensions.
 * Chess board consist of MxN pieces [[ChessBoardPiece]] which are indexed by [[ChessBoardIndex]]
 * @param m horizontal size of chess board
 * @param n vertical size of chess board
 * @param pieces all (indexed) pieces of board
 */
case class ChessBoard(m: Int, n: Int, pieces: Map[ChessBoardIndex, ChessBoardPiece]) {

  require(m > 0 && n > 0, "the smallest size of chessboard is 1 x 1")
  require(pieces.size == m * n, "size of chessboard must be agreed with quantity of pieces")

  /**
   * Check if board is allowed.
   * It means that any chessanalyzer.figure on board not threatening chessanalyzer.figure standing on input index and input chessanalyzer.figure not threatening ant chessanalyzer.figure on board.
   * @param pieceToCheck Piece for which we checked
   * @return if board is allowed.
   */
  def boardAllowed(pieceToCheck: ChessBoardPiece): Boolean = {
    pieces.forall {
      case (index, piece) =>
         piece.actualFigure.isEmpty || pieceToCheck.actualFigure.isEmpty || // if on of compared pieces is empty then is ok
          !piece.isThreateningPiece(pieceToCheck.index) && !pieceToCheck.isThreateningPiece(index) // or if two pieces are not threatening itself then is ok
    }
  }
}

object ChessBoard {

  /**
   * Create empty chess board
   * @param m horizontal size of chess board
   * @param n vertical size of chess board
   * @return empty chess board
   */
  def apply(m: Int, n: Int): ChessBoard = {
    val indexes = for {
      i <- 1 to m
      j <- 1 to n
    } yield ChessBoardIndex(i, j)

    ChessBoard(m, n, indexes.map(ind => ind -> ChessBoardPiece(ind, None))(scala.collection.breakOut))
  }
}
