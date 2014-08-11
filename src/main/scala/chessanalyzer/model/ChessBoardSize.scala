package chessanalyzer.model

/**
 * Describes size of  [[ChessBoard]]
 * @param m horizontal size of chess board
 * @param n vertical size of chess board
 */
case class ChessBoardSize(m: Int, n: Int) {

  /**
   * Size of flatten structure of [[ChessBoard]]
   * In other words number of all pieces on the chess board
   */
  val total: Int = m * n
}
