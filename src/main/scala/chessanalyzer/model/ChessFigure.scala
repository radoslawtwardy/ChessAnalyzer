package chessanalyzer.model

import scala.math.abs

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
 */
object ChessFigure {
  case object Bishop extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = {
      abs(actualFigureIndex.i - enemyFigureIndex.i) == abs(actualFigureIndex.j - enemyFigureIndex.j)
    }
  }

  case object King extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = {
      abs(actualFigureIndex.i - enemyFigureIndex.i) < 2 && abs(actualFigureIndex.j - enemyFigureIndex.j) < 2
    }
  }

  case object Knight extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = {
      abs(actualFigureIndex.i - enemyFigureIndex.i) == 1 && abs(actualFigureIndex.j - enemyFigureIndex.j) == 2 ||
      abs(actualFigureIndex.i - enemyFigureIndex.i) == 2 && abs(actualFigureIndex.j - enemyFigureIndex.j) == 1 ||
      actualFigureIndex.i - enemyFigureIndex.i == 0 && actualFigureIndex.j - enemyFigureIndex.j == 0
    }
  }

  case object Queen extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = {
      Bishop.isThreatening(actualFigureIndex, enemyFigureIndex) || Rook.isThreatening(actualFigureIndex, enemyFigureIndex)
    }
  }

  case object Rook extends ChessFigure {
    def isThreatening(actualFigureIndex: ChessBoardIndex, enemyFigureIndex: ChessBoardIndex): Boolean = {
      actualFigureIndex.i == enemyFigureIndex.i || actualFigureIndex.j == enemyFigureIndex.j
    }
  }
}