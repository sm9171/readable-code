package cleancode.minesweeper.asis.io.sign;

import cleancode.minesweeper.asis.cell.CellSnapshot;
import cleancode.minesweeper.asis.cell.CellSnapshotStatus;

public class FlagCellSignProvider implements CellSignProvidable {
    private static final String FLAG_SIGN = "⚑";

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(CellSnapshotStatus.FLAG);
    }

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return FLAG_SIGN;
    }
}
