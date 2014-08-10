package chessanalyzer.model.board

/**
 * Describes coordinates of [[ChessBoardPiece]] on [[ChessBoard]]
 * @param i horizontal coordinate, 1 is the smallest index
 * @param j vertical coordinate, 1 is the smallest index
 */
case class ChessBoardIndex(i: Int, j: Int) {
  require(i > 0 && j > 0, "Index cannot be lower then one")

  /**
   * Build next index relative to this
   * @param n vertical size of chess board
   * @return next index
   */
  def nextIndex(n: Int): ChessBoardIndex = {
    if(j != n) ChessBoardIndex(i, j + 1)
    else ChessBoardIndex(i + 1, 1)
  }
}
