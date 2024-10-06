package cleancode.minesweeper.tobe.position;

import java.util.List;
import java.util.Objects;

public class RelativePosition {

    public static List<RelativePosition> SURROUNDED_POSITIONS = List.of(
            RelativePosition.of(-1, -1),
            RelativePosition.of(-1, 0),
            RelativePosition.of(-1, 1),
            RelativePosition.of(0, -1),
            RelativePosition.of(0, 1),
            RelativePosition.of(1, -1),
            RelativePosition.of(1, 0),
            RelativePosition.of(1, 1)
    );

    private final int deltaRow;
    private final int deltaCol;

    public RelativePosition(int deltaRow, int deltaCol) {
        this.deltaCol = deltaCol;
        this.deltaRow = deltaRow;
    }

    public static RelativePosition of(int deltaRow, int deltaCol) {
        return new RelativePosition(deltaRow, deltaCol);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RelativePosition that = (RelativePosition) object;
        return deltaRow == that.deltaRow && deltaCol == that.deltaCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaRow, deltaCol);
    }

    public int getDeltaCol() {
        return deltaCol;
    }

    public int getDeltaRow() {
        return deltaRow;
    }
}
