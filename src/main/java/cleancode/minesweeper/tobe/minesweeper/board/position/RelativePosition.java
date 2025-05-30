package cleancode.minesweeper.tobe.minesweeper.board.position;

import java.util.List;
import java.util.Objects;

public class RelativePosition {

    public static final List<RelativePosition> SURROUNDED_POSITIONS = List.of(RelativePosition.of(-1, -1),
            RelativePosition.of(-1, 0),
            RelativePosition.of(-1, 1),
            RelativePosition.of(0, -1),
            RelativePosition.of(0, 1),
            RelativePosition.of(1, -1),
            RelativePosition.of(1, 0),
            RelativePosition.of(1, 1)
    );
    private final int deltaRow; // 변화량
    private final int deltaCol; //

    //유효성 검증은 필요없음 어차피 음수가 나옴
    public RelativePosition(int deltaRow, int deltaCol) {
        this.deltaRow = deltaRow;
        this.deltaCol = deltaCol;
    }

    public static RelativePosition of(int deltaRow, int deltaCol) {
        return new RelativePosition(deltaRow, deltaCol);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RelativePosition that = (RelativePosition) o;
        return deltaRow == that.deltaRow && deltaCol == that.deltaCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaRow, deltaCol);
    }

    public int getDeltaRow() {
        return deltaRow;
    }

    public int getDeltaCol() {
        return deltaCol;
    }
}
