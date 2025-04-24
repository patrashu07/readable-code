package cleancode.minesweeper.asis;

import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_COL_SIZE = 10;
    public static final String LAND_MINE_SIGN = "☼";
    private static final String[][] BOARD = new String[BOARD_ROW_SIZE][BOARD_COL_SIZE];
    private static final Integer[][] NEARBY_LAND_MINE_COUNT = new Integer[BOARD_ROW_SIZE][BOARD_COL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_COL_SIZE];
    public static final int LAND_MINE_COUNT = 10;
    public static final String FIAG_SIGN = "⚑";
    public static final String CLOSED_CELL_SIGN = "□";
    public static final String OPENER_CELL_SIGN = "■";


    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    public static void main(String[] args) {
        // alt + shift + m  메서드 만들기
        showGameStartComents();
        Scanner scanner = new Scanner(System.in);
        initializeGame();
        while (true) {
            showBoard();
            if (doesUserWinTheGame()) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (doesUserLoseTheGame()) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            String cellInput = getCellInputFromUser(scanner);
            String userActionInput = getUserActionInputFromUser(scanner);

            int selectedColIndex = getSelectedColIndex(cellInput);
            int selectedRowIndex = getSelectedRowIndex(cellInput);

            if (doesUserChosseToPlantPlag(userActionInput)) {
                BOARD[selectedRowIndex][selectedColIndex] = FIAG_SIGN;
                checkIfGameIsOver();
            } else if (doesUserChoseToOpenCell(userActionInput)) {
                if (isLandMindCell(selectedRowIndex, selectedColIndex)) {
                    BOARD[selectedRowIndex][selectedColIndex] = LAND_MINE_SIGN;
                    changeGameStatusToLose();
                    continue;
                } else {
                    open(selectedRowIndex, selectedColIndex);
                }
                checkIfGameIsOver();
            } else {
                System.out.println("잘못된 번호를 선택하셨습니다.");
            }
        }
    }

    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean isLandMindCell(int selectedRowIndex, int selectedColIndex) {
        return LAND_MINES[selectedRowIndex][selectedColIndex];
    }

    private static boolean doesUserChoseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private static boolean doesUserChosseToPlantPlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    private static int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }

    private static int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        return convertCalFrom(cellInputCol);
    }

    private static String getUserActionInputFromUser(Scanner scanner) {
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        return scanner.nextLine();
    }

    private static String getCellInputFromUser(Scanner scanner) {
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        return scanner.nextLine();
    }

    private static boolean doesUserLoseTheGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinTheGame() {
        return gameStatus == 1;
    }

    private static void checkIfGameIsOver() {
        boolean isAllOpened = isAllCellOpened();
        if (isAllOpened) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private static boolean isAllCellOpened() {
        boolean isAllOpened = true;
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                if (BOARD[row][col].equals(CLOSED_CELL_SIGN)) {
                    isAllOpened = false;
                }
            }
        }
        return isAllOpened;
    }

    private static int convertRowFrom(char cellInputRow) {
        return Character.getNumericValue(cellInputRow) - 1;
    }

    private static int convertCalFrom(char cellInputCol) {
        switch (cellInputCol) {
            case 'a':
                return 0;
            case 'b':
                return  1;
            case 'c':
                return  2;
            case 'd':
                return  3;
            case 'e':
                return  4;
            case 'f':
                return  5;
            case 'g':
                return  6;
            case 'h':
                return  7;
            case 'i':
                return  8;
            case 'j':
                return  9;
            default:
                return  -1;
        }
    }

    private static void showBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            System.out.printf("%d  ", row + 1);
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                System.out.print(BOARD[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initializeGame() {
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                BOARD[row][col] = CLOSED_CELL_SIGN;
            }
        }
        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int col = new Random().nextInt(BOARD_COL_SIZE);
            int row = new Random().nextInt(BOARD_ROW_SIZE);
            LAND_MINES[row][col] = true;
        }
        for (int row = 0; row < BOARD_ROW_SIZE; row++) {
            for (int col = 0; col < BOARD_COL_SIZE; col++) {
                int count = 0;
                if (!isLandMindCell(row, col)) {
                    //현재 칸 기준으로 왼쪽위 대각선에 있는지
                    if (row - 1 >= 0 && col - 1 >= 0 && isLandMindCell(row - 1, col - 1)) {
                        count++;
                    }
                    if (row - 1 >= 0 && isLandMindCell(row - 1, col)) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < BOARD_COL_SIZE && isLandMindCell(row - 1, col + 1)) {
                        count++;
                    }
                    if (col - 1 >= 0 && isLandMindCell(row, col - 1)) {
                        count++;
                    }
                    if (col + 1 < BOARD_COL_SIZE && isLandMindCell(row, col + 1)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && col - 1 >= 0 && isLandMindCell(row + 1, col - 1)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && isLandMindCell(row + 1, col)) {
                        count++;
                    }
                    if (row + 1 < BOARD_ROW_SIZE && col + 1 < BOARD_COL_SIZE && isLandMindCell(row + 1, col + 1)) {
                        count++;
                    }
                    NEARBY_LAND_MINE_COUNT[row][col] = count;
                    continue;
                }
                NEARBY_LAND_MINE_COUNT[row][col] = 0;
            }
        }
    }

    private static void showGameStartComents() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작 !!!!!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void open(int row, int col) {
        if (row < 0 || row >= BOARD_ROW_SIZE || col < 0 || col >= BOARD_COL_SIZE) {
            return;
        }
        if (!BOARD[row][col].equals(CLOSED_CELL_SIGN)) {
            return;
        }
        if (isLandMindCell(row, col)) {
            return;
        }
        if (NEARBY_LAND_MINE_COUNT[row][col] != 0) {
            BOARD[row][col] = String.valueOf(NEARBY_LAND_MINE_COUNT[row][col]);
            return;
        } else {
            BOARD[row][col] = OPENER_CELL_SIGN;
        }
        //재귀함수 자기자신을 지속적으로 호출한다.
        //stack overflow 발생 가능성이 존재하기는한다. 종단조건을 잘 설정해줘야한다.
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}
