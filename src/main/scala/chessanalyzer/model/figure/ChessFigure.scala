package chessanalyzer.model.figure

import chessanalyzer.model.board.ChessBoardIndex

/**
 * Describes abstract chess figure
 */
sealed trait ChessFigure {
  /**
   * Check if current figure threatening other figure
   * @param actualFigureIndex Coordination of figure for which we checked
   * @param enemyFigureIndex  Coordination of figure for which is possible threatened by current figure
   * @return threatened indexes of pieces of chess board for current figure
   */
  def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean
}

/**
 * Figure have only some universal properties so we don't create instance of figure.
 * Properties like coordinates etc contains [[chessanalyzer.model.board.ChessBoardPiece]]
 */
object ChessFigure {
  case object Bishop extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = ???
  }

  case object King extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = ???
  }

  case object Knight extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = ???
  }

  case object Queen extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = ???
  }
}