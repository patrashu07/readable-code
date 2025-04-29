package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;

import java.util.List;

public class CellSignFinder {

    //힌번만 만들어놓고 재사용
    public static final List<CellSignProvidable> CELL_SIGN_PROVIDABLES = List.of(
            new EmptyCellSignProvider(),
            new LandMineCellSignProvider(),
            new NumberCellSignProvider(),
            new FlagCellSignProvider(),
            new UnCheckedCellSignProvider()
    );

    public String findCellSignFrom(CellSnapshot snapshot) {

        return CELL_SIGN_PROVIDABLES.stream()
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider -> provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("확인 할 수 없는 값입니다."));


    }
}
