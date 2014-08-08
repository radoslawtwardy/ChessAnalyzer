package chessanalyzer.model.board

/**
 * Describes coordinates of [[ChessBoardPiece]] on [[ChessBoard]]
 * @param i horizontal coordinate, 1 is the smallest index
 * @param j vertical coordinate, 1 is the smallest index
 */
case class ChessBoardIndex(i: Int, j: Int) {
  require(i > 0 && j > 0, "Index cannot be lower then one")
}
