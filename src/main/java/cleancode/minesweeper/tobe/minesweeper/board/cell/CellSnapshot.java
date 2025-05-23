package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

public class CellSnapshot {

    private final CellSnapshotStatus status;
    private final int nearByLandMineCount;

    public CellSnapshot(CellSnapshotStatus status, int nearByLandMineCount) {
        this.status = status;
        this.nearByLandMineCount = nearByLandMineCount;
    }


    public static CellSnapshot of(CellSnapshotStatus status, int nearByLandMineCount) {
        return new CellSnapshot(status, nearByLandMineCount);
    }

    public static CellSnapshot ofEmpty() {
        return of(CellSnapshotStatus.EMPTY, 0);
    }

    public static CellSnapshot ofFlag() {
        return of(CellSnapshotStatus.FLAG, 0);
    }

    public static CellSnapshot ofLandMine() {
        return of(CellSnapshotStatus.LAND_MINE, 0);
    }

    public static CellSnapshot ofNumber(int nearByLandMineCount) {
        return of(CellSnapshotStatus.NUMBER, nearByLandMineCount);
    }

    public static CellSnapshot ofUnchecked() {
        return of(CellSnapshotStatus.UNCHECKED, 0);
    }

    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return this.status == cellSnapshotStatus;
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    public int getNearByLandMineCount() {
        return nearByLandMineCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CellSnapshot that = (CellSnapshot) o;
        return nearByLandMineCount == that.nearByLandMineCount && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearByLandMineCount);
    }
}
