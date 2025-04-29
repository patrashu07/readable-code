package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;

import java.util.Arrays;

public enum CellSignProvider implements CellSignProvidable{

    EMPTY(CellSnapshotStatus.EMPTY){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return EMPTY_SIGN;
        }
    },
    FLAG(CellSnapshotStatus.FLAG){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return FLAG_SIGN;
        }
    },
    LAND_MINE(CellSnapshotStatus.LAND_MINE){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return LAND_MINE_SIGN;
        }
    },
    NUMBER(CellSnapshotStatus.NUMBER){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return String.valueOf(cellSnapshot.getNearByLandMineCount());
        }
    },
    UNCHECKED(CellSnapshotStatus.UNCHECKED){
        @Override
        public String provide(CellSnapshot cellSnapshot) {
            return UNCHECKED_SIGN;
        }
    };

    private final CellSnapshotStatus status;
    private static final String EMPTY_SIGN = "■";
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";

    CellSignProvider(CellSnapshotStatus status) {
        this.status = status;
    }

    @Override
    public boolean supports(CellSnapshot cellSnapshot) {
        return cellSnapshot.isSameStatus(status);
    }

    public static String findCellSignFrom(CellSnapshot snapshot) {
        return Arrays.stream(values())
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider ->  provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("Can't find cell sign from snapshot " + snapshot.toString()));
    }

}
