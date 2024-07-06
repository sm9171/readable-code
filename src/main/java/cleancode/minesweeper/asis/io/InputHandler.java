package cleancode.minesweeper.asis.io;

import cleancode.minesweeper.asis.position.CellPosition;
import cleancode.minesweeper.asis.user.UserAction;

public interface InputHandler {
	UserAction getUserActionFromUser();

	CellPosition getCellPositionFromUser();
}
