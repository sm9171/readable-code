package cleancode.minesweeper.asis.io.sign;

import cleancode.minesweeper.asis.cell.CellSnapshot;

public interface CellSignProvidable {

    boolean supports(CellSnapshot cellSnapshot);

    String provide(CellSnapshot cellSnapshot);
}
