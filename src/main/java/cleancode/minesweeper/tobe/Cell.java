package cleancode.minesweeper.tobe;

public class Cell {

    //불변데이터
    //여러줄 작성 ALT_SHIFT+INSERT
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearByLandMineCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    //CELL이 가진 속성 : 근처 숫자 , 지뢰 여부
    //CELL의 상태 : 깃발,열렸다/닫혔다, 사용자가 확인함
    
    
    
    //생성자 만들기 Crtl + insert
    private Cell(int nearByLandMineCount , boolean isLandMine , boolean isFlagged ,boolean isOpened) {
        this.nearByLandMineCount = nearByLandMineCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    //정적팩토링하시는 이유는 이름을 각각 줄수있다
    public static Cell of( int nearByLandMineCount , boolean isLandMine, boolean isFlagged ,boolean isOpened) {
        return new Cell(nearByLandMineCount , isLandMine ,isFlagged ,isOpened);
    }



    public static Cell create() {
        return of(0,false ,false, false);
    }

    public void flag() {
        this.isFlagged = true;
    }


    public void open() {
        this.isOpened = true;
    }


    //지뢰CELL
    public void turnOnLandMine() {
        this.isLandMine = true;
    }

    public void updateNearByLandMineCount(int count) {
        this.nearByLandMineCount = count;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public boolean isOpened()  {
        return isOpened;
    }

    public boolean hasLandMineCount() {
        return this.nearByLandMineCount != 0;
    }

    public String getSign() {
        if(isOpened()) {
            if(isLandMine()) {
                return LAND_MINE_SIGN;
            }
            if(hasLandMineCount()) {
                return String.valueOf(nearByLandMineCount);
            }
            return EMPTY_SIGN;
        }

        if(isFlagged) {
            return FLAG_SIGN;
        }

        return UNCHECKED_SIGN;
    }
}
