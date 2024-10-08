package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapshot {
    private final CellSnapshotStatus status;
    private final int nearByLandMineCount;

    public CellSnapshot(CellSnapshotStatus status, int nearByLandMineCount) {
        this.nearByLandMineCount = nearByLandMineCount;
        this.status = status;
    }

    public static CellSnapshot ofEmpty() {
        return new CellSnapshot(CellSnapshotStatus.EMPTY, 0);
    }

    public static CellSnapshot ofFlag() {
        return new CellSnapshot(CellSnapshotStatus.FLAG, 0);
    }

    public static CellSnapshot ofLandMine() {
        return new CellSnapshot(CellSnapshotStatus.LAND_MINE, 0);
    }

    public static CellSnapshot ofNumber(int nearByLandMineCount) {
        return new CellSnapshot(CellSnapshotStatus.NUMBER, nearByLandMineCount);
    }

    public static CellSnapshot ofUnchecked() {
        return new CellSnapshot(CellSnapshotStatus.UNCHECKED, 0);
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearByLandMineCount() {
        return nearByLandMineCount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CellSnapshot that = (CellSnapshot) object;
        return nearByLandMineCount == that.nearByLandMineCount && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearByLandMineCount);
    }

    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return this.status == cellSnapshotStatus;
    }
}
