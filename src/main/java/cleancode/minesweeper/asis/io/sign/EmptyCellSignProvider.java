package cleancode.minesweeper.asis.io.sign;

import cleancode.minesweeper.asis.cell.CellSnapshot;
import cleancode.minesweeper.asis.cell.CellSnapshotStatus;

public class EmptyCellSignProvider implements CellSignProvidable {
    private static final String EMPTY_SIGN = "■";

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.EMPTY);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return EMPTY_SIGN;
    }
}
