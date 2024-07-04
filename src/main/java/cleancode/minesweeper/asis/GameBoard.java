package cleancode.minesweeper.asis;

import cleancode.minesweeper.asis.cell.Cell;
import cleancode.minesweeper.asis.cell.EmptyCell;
import cleancode.minesweeper.asis.cell.LandMineCell;
import cleancode.minesweeper.asis.cell.NumberCell;
import cleancode.minesweeper.asis.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;

public class GameBoard {

	private final Cell[][] board;
	private int landMineCount;

	public GameBoard(GameLevel gameLevel) {
		int rowSize = gameLevel.getRowSize();
		int colSize = gameLevel.getColSize();
		landMineCount = gameLevel.getLandMineCount();
		board = new Cell[rowSize][colSize];
	}

	public int getRowSize() {
		return board.length;
	}

	public int getColSize() {
		return board[0].length;
	}

	public String getSign(final int rowIndex, final int colIndex) {
		return findCell(rowIndex, colIndex).getSign();
	}

	public void flag(final int rowIndex, final int colIndex) {
		Cell cell = findCell(rowIndex, colIndex);
		cell.flag();
	}

	public void open(final int rowIndex, final int colIndex) {
		Cell cell = findCell(rowIndex, colIndex);
		cell.open();
	}

	public boolean isLandMineCell(int selectedRowIndex, int selectedColumnIndex) {
		Cell cell = findCell(selectedRowIndex, selectedColumnIndex);
		return cell.isLandMine();
	}

	public void initializeGame() {
		int rowSize = getRowSize();
		int colSize = getColSize();

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				board[row][col] = new EmptyCell();
			}
		}

		for (int i = 0; i < landMineCount; i++) {
			int landMineCol = new Random().nextInt(colSize);
			int landMineRow = new Random().nextInt(rowSize);
			LandMineCell landMineCell = new LandMineCell();
			board[landMineRow][landMineCol] = landMineCell;
		}

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (isLandMineCell(row, col)) {
					continue;
				}
				int count = countNearByLandMines(row, col);
				if (count == 0) {
					continue;
				}
				NumberCell numberCell = new NumberCell(count);
				board[row][col] = numberCell;
			}
		}
	}

	public boolean isAllCellChecked() {
		return Arrays.stream(board)
				.flatMap(Arrays::stream)
				.allMatch(Cell::isChecked);
	}

	private int countNearByLandMines(final int row, final int col) {
		int rowSize = getRowSize();
		int colSize = getColSize();

		int count = 0;
		if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
			count++;
		}
		if (row - 1 >= 0 && isLandMineCell(row - 1, col)) {
			count++;
		}
		if (row - 1 >= 0 && col + 1 < colSize && isLandMineCell(row - 1, col + 1)) {
			count++;
		}
		if (col - 1 >= 0 && isLandMineCell(row, col - 1)) {
			count++;
		}
		if (col + 1 < colSize && isLandMineCell(row, col + 1)) {
			count++;
		}
		if (row + 1 < rowSize && col - 1 >= 0 && isLandMineCell(row + 1, col - 1)) {
			count++;
		}
		if (row + 1 < rowSize && isLandMineCell(row + 1, col)) {
			count++;
		}
		if (row + 1 < rowSize && col + 1 < colSize && isLandMineCell(row + 1, col + 1)) {
			count++;
		}
		return count;
	}

	private Cell findCell(final int rowIndex, final int colIndex) {
		return board[rowIndex][colIndex];
	}

	public void openSurroundedCells(int row, int col) {
		if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
			return;
		}

		if (isOpenedCell(row, col)) {
			return;
		}

		if (isLandMineCell(row, col)) {
			return;
		}

		open(row, col);

		if (doesCellHaveLandMineCount(row, col)) {
			return;
		}

		openSurroundedCells(row - 1, col - 1);
		openSurroundedCells(row - 1, col);
		openSurroundedCells(row - 1, col + 1);
		openSurroundedCells(row, col - 1);
		openSurroundedCells(row, col + 1);
		openSurroundedCells(row + 1, col - 1);
		openSurroundedCells(row + 1, col);
		openSurroundedCells(row + 1, col + 1);
	}

	private boolean isOpenedCell(final int row, final int col) {
		return findCell(row, col).isOpened();
	}

	private boolean doesCellHaveLandMineCount(final int row, final int col) {
		return findCell(row, col).hasLandMineCount();
	}
}
