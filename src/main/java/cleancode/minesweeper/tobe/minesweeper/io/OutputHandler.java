package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public interface OutputHandler {

    void showBoard(GameBoard board);

    void showGameStartComments();

    String generateColAlphabets(GameBoard board);

    void showGameLossingComment();

    void showGameWinningComment();

    void showCommentForSelectingCell();

    void showCommentForUserAction();

    void showExceptionMessage(GameException e);

    void showSimpleMessage(String s);
}
