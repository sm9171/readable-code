package cleancode.minesweeper.asis.cell;

public enum CellSnapshotStatus {
    EMPTY("빈 셀"),
    LAND_MINE("지뢰"),
    FLAG("깃발"),
    NUMBER("숫자"),
    UNCHECKED("확인 전");

    private final String description;

    CellSnapshotStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
