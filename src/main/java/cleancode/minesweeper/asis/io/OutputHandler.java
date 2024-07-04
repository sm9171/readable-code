package cleancode.minesweeper.asis.io;

import cleancode.minesweeper.asis.GameBoard;
import cleancode.minesweeper.asis.GameException;

public interface OutputHandler {
	void showGameStartComments();
	void showBoard(final GameBoard gameBoard);
	void showGameWinningComment();
	void showGameLossingComment();
	void showCommentForUserAction();
	void showCommentForSelectingCell();
	void showExceptionMessage(final GameException e);
	void showSimpleMessage(final String message);
}
