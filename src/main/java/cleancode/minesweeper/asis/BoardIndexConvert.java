package cleancode.minesweeper.asis;

public class BoardIndexConvert {
	private static final char BASE_CHAR_FOR_COL = 'a';

	public int getSelectedColumnIndex(final String cellInput, final int colSize) {
		char cellInputCol = cellInput.charAt(0);
		return convertColFrom(cellInputCol, colSize);
	}

	public int getSelectedRowIndex(final String cellInput, final int rowSize) {
		String cellInputRow = cellInput.substring(1);
		return convertRowFrom(cellInputRow,rowSize);
	}

	private int convertColFrom(char cellInputCol, final int colSize) {
		int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
		if (colIndex < 0 || colIndex >= colSize) {
			throw new GameException("잘못된 입력입니다.");
		}
		return colIndex;
	}

	private int convertRowFrom(String cellInputRow, final int rowSize) {
		int rowIndex = Integer.parseInt(cellInputRow) - 1;
		if (rowIndex >= rowSize || rowIndex < 0) {
			throw new GameException("잘못된 입력입니다.");
		}
		return rowIndex;
	}
}
