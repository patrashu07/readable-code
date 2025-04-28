package cleancode.minesweeper.tobe.cell;

public class CellState {

    private boolean isFlagged;
    private boolean isOpened;

    public CellState(boolean isOpened, boolean isFlagged) {
        this.isOpened = isOpened;
        this.isFlagged = isFlagged;
    }

    public static CellState initialize() {
        return new CellState(false, false);
    }

    public void flag() {
        this.isFlagged = true;
    }

    public void open() {
        this.isOpened = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

}
