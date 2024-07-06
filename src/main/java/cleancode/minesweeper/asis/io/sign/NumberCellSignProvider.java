package cleancode.minesweeper.asis.io.sign;

import cleancode.minesweeper.asis.cell.CellSnapshot;
import cleancode.minesweeper.asis.cell.CellSnapshotStatus;

public class NumberCellSignProvider implements CellSignProvidable {
    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.NUMBER);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return String.valueOf(cellSnapshot.getNearbyLandMineCount());
    }
}
