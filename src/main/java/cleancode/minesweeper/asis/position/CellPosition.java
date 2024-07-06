package cleancode.minesweeper.asis.position;

import java.util.Objects;

public class CellPosition {

    private final int rowIndex;
    private final int colIndex;

    public CellPosition(int row, int column) {
        if (row < 0 || column < 0) {
            throw new IllegalArgumentException("Invalid cell position");
        }
        this.rowIndex = row;
        this.colIndex = column;
    }

    public static CellPosition of(int row, int column) {
        return new CellPosition(row, column);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    public boolean isRowIndexMoreThanOrEqual(int rowSize) {
        return rowIndex >= rowSize;
    }

    public boolean isColIndexMoreThanOrEqual(int colSize) {
        return colIndex >= colSize;
    }

    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
        if (!canCalculatePositionBy(relativePosition)) {
            throw new IllegalArgumentException("잘못된 좌표입니다.");
        }
        return CellPosition.of(
                rowIndex + relativePosition.getDeltaRow(),
                colIndex + relativePosition.getDeltaCol()
        );
    }

    public boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return rowIndex + relativePosition.getDeltaRow() >= 0 &&
                colIndex + relativePosition.getDeltaCol() >= 0;
    }

    public boolean isRowIndexLessThan(int rowSize) {
        return rowIndex < rowSize;
    }

    public boolean isColIndexLessThan(int colSize) {
        return colIndex < colSize;
    }
}
