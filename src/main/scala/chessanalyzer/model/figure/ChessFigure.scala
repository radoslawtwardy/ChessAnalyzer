package chessanalyzer.model.figure

import chessanalyzer.model.board.ChessBoardIndex

/**
 * Describes abstract chess figure
 */
sealed trait ChessFigure {
  /**
   * Calculate which pieces are threatened if figure will stay on input piece
   * @param figureIndex Coordination of figure for which we checked
   * @param m max horizontal size of threatening possibility (for ex. horizontal size chess board)
   * @param n max vertical size of threatening possibility (for ex. vertical size chess board)
   * @return threatened indexes of pieces of chess board for current figure
   */
  def threatenedPieces(figureIndex: ChessBoardIndex, m: Int, n: Int): Set[ChessBoardIndex]
}

/**
 * Figure have only some universal properties so we don't create instance of figure.
 * Properties like coordinates etc contains [[chessanalyzer.model.board.ChessBoardPiece]]
 */
object ChessFigure {
  case object Bishop extends ChessFigure {
    def threatenedPieces(figureIndex: ChessBoardIndex, m: Int, n: Int): Set[ChessBoardIndex] = ???
  }

  case object King extends ChessFigure {
    def threatenedPieces(figureIndex: ChessBoardIndex, m: Int, n: Int): Set[ChessBoardIndex] = ???
  }

  case object Knight extends ChessFigure {
    def threatenedPieces(figureIndex: ChessBoardIndex, m: Int, n: Int): Set[ChessBoardIndex] = ???
  }

  case object Queen extends ChessFigure {
    def threatenedPieces(figureIndex: ChessBoardIndex, m: Int, n: Int): Set[ChessBoardIndex] = ???
  }
}