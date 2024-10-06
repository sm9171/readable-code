package cleancode.minesweeper.tobe;

public class BoardIndexConverter {
    public static final char BASE_CHAR_FOR_COL = 'a';

    private BoardIndexConverter(){}

    public static int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertColFrom(cellInputCol);
    }

    public static int getSelectedRowIndex(String cellInput) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow);
    }

    private static int convertRowFrom(String cellInputRow) {
        int rowindex = Integer.parseInt(cellInputRow);
        if (rowindex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowindex;
    }

    private static int convertColFrom(char cellInputCol) {
        int colIndex = cellInputCol - BASE_CHAR_FOR_COL;
        if (colIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return colIndex;
    }
}
