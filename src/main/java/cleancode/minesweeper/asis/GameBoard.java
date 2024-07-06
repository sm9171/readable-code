package cleancode.minesweeper.asis;

import cleancode.minesweeper.asis.cell.*;
import cleancode.minesweeper.asis.gamelevel.GameLevel;
import cleancode.minesweeper.asis.position.CellPosition;
import cleancode.minesweeper.asis.position.CellPositions;
import cleancode.minesweeper.asis.position.RelativePosition;

import java.util.List;

public class GameBoard {

	private final Cell[][] board;
	private int landMineCount;
	private GameStatus gameStatus;

	public GameBoard(GameLevel gameLevel) {
		int rowSize = gameLevel.getRowSize();
		int colSize = gameLevel.getColSize();
		landMineCount = gameLevel.getLandMineCount();
		board = new Cell[rowSize][colSize];
		initializeGameStatus();
	}

	public int getRowSize() {
		return board.length;
	}

	public int getColSize() {
		return board[0].length;
	}

	public void flagAt(final CellPosition cellPosition) {
		Cell cell = findCell(cellPosition);
		cell.flag();
		checkIfGameIsOver();
	}

	public void openOneAt(final CellPosition cellPosition) {
		Cell cell = findCell(cellPosition);
		cell.open();
	}

	public boolean isLandMineCell(CellPosition cellPosition) {
		Cell cell = findCell(cellPosition);
		return cell.isLandMine();
	}

	public void initializeGame() {
		initializeGameStatus();
		CellPositions cellPositions = CellPositions.from(board);

		initializeEmptyCells(cellPositions);

		List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
		initializeLandMineCells(landMinePositions);

		List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
		initializeNumberCells(numberPositionCandidates);
	}

	private void initializeGameStatus() {
		gameStatus = GameStatus.IN_PROGRESS;
	}

	private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
		for (CellPosition candidatePosition : numberPositionCandidates) {
			int count = countNearByLandMines(candidatePosition);
			if(count == 0) {
				updateCellAt(candidatePosition, new NumberCell(count));
			}
		}
	}

	private void initializeLandMineCells(List<CellPosition> landMinePositions) {
		for (CellPosition position : landMinePositions) {
			updateCellAt(position, new LandMineCell());
		}
	}

	private void initializeEmptyCells(CellPositions cellPositions) {
		int rowSize = getRowSize();
		int colSize = getColSize();

		List<CellPosition> allPositions = cellPositions.getPositions();
		for (CellPosition position : allPositions) {
			updateCellAt(position, new EmptyCell());
		}
	}

	private void updateCellAt(CellPosition position, Cell cell) {
		board[position.getRowIndex()][position.getColIndex()] = cell;
	}

	public boolean isAllCellChecked() {
		Cells cells = Cells.from(board);
		return cells.isAllCellChecked();
	}

	public boolean isWinStatus() {
		return gameStatus == GameStatus.WIN;
	}

	public boolean isLossStatus() {
		return gameStatus == GameStatus.LOSE;
	}

	public boolean isInvalidCellPosition(CellPosition cellPosition) {
		int colSize = getColSize();
		int rowSize = getRowSize();

		return cellPosition.isRowIndexMoreThanOrEqual(rowSize) || cellPosition.isColIndexMoreThanOrEqual(colSize);
	}

	public CellSnapshot getSnapshot(CellPosition cellPosition) {
		return findCell(cellPosition).getSnapshot();
	}

	private int countNearByLandMines(final CellPosition cellPosition) {
		int rowSize = getRowSize();
		int colSize = getColSize();

		return (int) calculateSurroundedPositions(cellPosition, rowSize, colSize).stream()
				.filter(this::isLandMineCell)
				.count();
	}

	private static List<CellPosition> calculateSurroundedPositions(CellPosition cellPosition, int rowSize, int colSize) {
		return RelativePosition.SURROUNDED_POSITION.stream()
				.filter(cellPosition::canCalculatePositionBy)
				.map(cellPosition::calculatePositionBy)
				.filter(position -> position.isRowIndexLessThan(rowSize))
				.filter(position -> position.isColIndexLessThan(colSize))
				.toList();
	}

	public void openSurroundedCells(CellPosition cellPosition) {
		if (isOpenedCell(cellPosition)) {
			return;
		}

		if (isLandMineCell(cellPosition)) {
			return;
		}

		openOneAt(cellPosition);

		if (doesCellHaveLandMineCount(cellPosition)) {
			return;
		}

		List<CellPosition> surroundedPositions = calculateSurroundedPositions(cellPosition, getRowSize(), getColSize());
		surroundedPositions.forEach(this::openSurroundedCells);
	}

	public boolean isInProgress() {
		return gameStatus == GameStatus.IN_PROGRESS;
	}

	public void openAt(CellPosition cellPosition) {
		if (isLandMineCell(cellPosition)) {
			openOneAt(cellPosition);
			changeGameStatusToLose();
		}
		openSurroundedCells(cellPosition);
		checkIfGameIsOver();
	}

	private Cell findCell(final CellPosition cellPosition) {
		return board[cellPosition.getRowIndex()][cellPosition.getColIndex()];
	}

	private boolean isOpenedCell(final CellPosition cellPosition) {
		Cell cell = findCell(cellPosition);
		return cell.isOpened();
	}

	private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
		Cell cell = findCell(cellPosition);
		return cell.hasLandMineCount();
	}

	private void checkIfGameIsOver() {
		if (isAllCellChecked()) {
			changeGameStatusToWin();
		}
	}

	private void changeGameStatusToWin() {
		gameStatus = GameStatus.WIN;
	}

	private void changeGameStatusToLose() {
		gameStatus = GameStatus.LOSE;
	}
}
