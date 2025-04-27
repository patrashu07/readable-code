package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.GameBoard;
import cleancode.minesweeper.tobe.GameException;

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
