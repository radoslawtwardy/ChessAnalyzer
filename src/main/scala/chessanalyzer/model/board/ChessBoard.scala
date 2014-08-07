package chessanalyzer.model.board

/**
 * Describes chess board with M x N dimensions.
 * Chess board consist of MxN pieces [[ChessBoardPiece]] which are indexed by [[ChessBoardIndex]]
 * @param m horizontal size of chess board
 * @param n vertical size of chess board
 * @param pieces all (indexed) pieces of board
 */
case class ChessBoard(m: Int, n: Int, pieces: Map[ChessBoardIndex, ChessBoardPiece]) {

  /**
   * Check if input piece is threatened by any piece (figure).
   * @param otherPiece Index of piece for which we checked
   * @return if input piece of chessboard is threatened by any piece (figure).
   */
  def isThreateningPiece(otherPiece: ChessBoardIndex) = ???

}