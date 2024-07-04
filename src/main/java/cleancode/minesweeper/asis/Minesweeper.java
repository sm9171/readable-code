package cleancode.minesweeper.asis;

import cleancode.minesweeper.asis.game.GameInitiallizable;
import cleancode.minesweeper.asis.game.GameRunnable;
import cleancode.minesweeper.asis.gamelevel.GameLevel;
import cleancode.minesweeper.asis.io.InputHandler;
import cleancode.minesweeper.asis.io.OutputHandler;

public class Minesweeper implements GameRunnable, GameInitiallizable {

	private final GameBoard gameBoard;
	private final BoardIndexConvert boardIndexConvert = new BoardIndexConvert();
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

	public Minesweeper(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
		this.gameBoard = new GameBoard(gameLevel);
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
	}

	public void initialize() {
		gameBoard.initializeGame();
	}

	public void run() {
		outputHandler.showGameStartComments();

		while (true) {
			try {
				outputHandler.showBoard(gameBoard);
				if (doesUserWintheGame()) {
					outputHandler.showGameWinningComment();
					break;
				}
				if (doesUserLoseTheGame()) {
					outputHandler.showGameLossingComment();
					break;
				}

				String cellInput = getCellInputFromUser();
				String userActionInput = getUserActionInputFromUser();
				actOnCell(cellInput, userActionInput);

			} catch (GameException e) {
				outputHandler.showExceptionMessage(e);
			} catch (Exception e) {
				outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
				e.printStackTrace();
			}
		}
	}

	private void actOnCell(final String cellInput, final String userActionInput) {
		int selectedColumnIndex = boardIndexConvert.getSelectedColumnIndex(cellInput, gameBoard.getColSize());
		int selectedRowIndex = boardIndexConvert.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

		if (doesUserChooseToPlantFlag(userActionInput)) {
			gameBoard.flag(selectedRowIndex, selectedColumnIndex);
			checkIfGameIsOver();
			return;
		}

		if (doesUserChooseToOpenCell(userActionInput)) {
			if (gameBoard.isLandMineCell(selectedRowIndex, selectedColumnIndex)) {
				gameBoard.open(selectedRowIndex, selectedColumnIndex);
				changeGameStatusToLose();
				return;
			}

			gameBoard.openSurroundedCells(selectedRowIndex, selectedColumnIndex);
			checkIfGameIsOver();
			return;
		}
		throw new GameException("잘못된 번호를 선택하셨습니다.");
	}

	private void changeGameStatusToLose() {
		gameStatus = -1;
	}

	private boolean doesUserChooseToOpenCell(String userActionInput) {
		return userActionInput.equals("1");
	}

	private boolean doesUserChooseToPlantFlag(String userActionInput) {
		return userActionInput.equals("2");
	}

	private String getUserActionInputFromUser() {
		outputHandler.showCommentForUserAction();
		return inputHandler.getUserInput();
	}

	private String getCellInputFromUser() {
		outputHandler.showCommentForSelectingCell();
		return inputHandler.getUserInput();
	}

	private boolean doesUserLoseTheGame() {
		return gameStatus == -1;
	}

	private boolean doesUserWintheGame() {
		return gameStatus == 1;
	}

	private void checkIfGameIsOver() {
		if (gameBoard.isAllCellChecked()) {
			changeGameStatusToWin();
		}
	}

	private void changeGameStatusToWin() {
		gameStatus = 1;
	}
}
