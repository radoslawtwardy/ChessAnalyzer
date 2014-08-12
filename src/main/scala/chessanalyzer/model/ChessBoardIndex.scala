package chessanalyzer.model

/**
 * Describes coordinates of [[ChessFigure]] on [[ChessBoard]]
 * @param i horizontal coordinate, 1 is the smallest index
 * @param j vertical coordinate, 1 is the smallest index
 */
case class ChessBoardIndex(i: Int, j: Int) {
  require(i > 0 && j > 0, "Index cannot be lower then one")

  /**
   * Build next index relative to this
   * @param size size of chess board
   * @return next index
   */
  def nextIndex(implicit size: ChessBoardSize): Option[ChessBoardIndex] = {
    if(i * j == size.total) None
    else if(j != size.n) Some(ChessBoardIndex(i, j + 1))
    else Some(ChessBoardIndex(i + 1, 1))
  }

  /**
  * String representation of index
  * @return literal value of index f. ex. A1
  */
  override def toString(): String = {
    // @ + 1 = A
    ('@' + i).toChar.toString + j
  }
}
