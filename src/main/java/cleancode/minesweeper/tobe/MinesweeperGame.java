package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell;
import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_COL_SIZE = 10;
    public static final Scanner SCANNER = new Scanner(System.in);
    private static final Cell[][] BOARD = new Cell[BOARD_ROW_SIZE][BOARD_COL_SIZE];
    public static final int LAND_MINE_COUNT = 10;

    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배


    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        MineSweeper minesweeper = new MineSweeper(gameLevel);
        minesweeper.initialize();
        minesweeper.run();
    }

}
