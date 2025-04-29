package cleancode.minesweeper.tobe.position;

import cleancode.minesweeper.tobe.cell.Cell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CellPositions {

    private final List<CellPosition> positions;


    public CellPositions(List<CellPosition> positions) {
        this.positions = positions;
    }

    public static CellPositions of(List<CellPosition> positions) {
        return new CellPositions(positions);
    }

    public static CellPositions from(Cell[][] board) {
        List<CellPosition> cellPositions = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                CellPosition cellPosition = CellPosition.of(row, col);
                cellPositions.add(cellPosition);
            }
        }

        return of(cellPositions);
    }
    //항상 외부에서 참조할수 없게
    public List<CellPosition> getPositions() {
        return new ArrayList<>(positions);
    }

    public List<CellPosition> extractRandomPositions(int count) {
        List<CellPosition> cellPositions = new ArrayList<>(positions);

        Collections.shuffle(positions); // positions을 셔플하면
        return cellPositions.subList(0, count);
    }

    public List<CellPosition> subStract(List<CellPosition> positionListToSubstract) {
        List<CellPosition> cellPositions = new ArrayList<>(positions);
        CellPositions positionsToSubStract = CellPositions.of(positionListToSubstract);


        return cellPositions.stream()
                .filter(positionsToSubStract::doesNotContain)
                .toList();

    }

    private boolean doesNotContain(CellPosition position) {
        return !positions.contains(position);
    }


}
