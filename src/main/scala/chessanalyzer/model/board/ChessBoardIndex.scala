package chessanalyzer.model.board

/**
 * Describes coordinates of [[ChessBoardPiece]] on [[ChessBoard]]
 * @param i horizontal coordinate
 * @param j vertical coordinate
 */
case class ChessBoardIndex(i: Int, j: Int) {
  require(i > 0 && j > 0, "Index cannot be lower then zero")
}
