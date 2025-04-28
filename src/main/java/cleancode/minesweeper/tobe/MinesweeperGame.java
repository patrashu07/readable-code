package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;

public class MinesweeperGame {


    public static void main(String[] args) {
        GameLevel gameLevel = new Beginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        MineSweeper minesweeper = new MineSweeper(gameLevel,inputHandler,outputHandler);
        minesweeper.initialize();
        minesweeper.run();
    }


    /**
     * SOLID
     * DIP(Dependency Inversion Principle)
     * 고수준 모듈과 저수준 모듈이 직접적으로 의존하는게 아니라 추상화에 의존해야 한다.
     *
     *
     * Spring 3대요소
     * DI(Dependency Injection) 의존성 주입 -"3"
     * 제 3자가 항상 둘의 의존성을 맺어준다 -> spring 에서 spring context 가 한다 ioc container 가 한다.
     *
     * 필요한 의존성을 내가 직접 생성하는게 아니라 외부에서 주입 받겠다.
     *
     *
     * IOC (Inversion of Control)
     * 제어의 역전
     * IOC는 프로그램의 흐름을 개발자가 아닌 framework가 담당한다.
     * 제어의 순방향 -> 내가 만든 프로그램이 미리 만들어진 공장같은 framework 내에 내 코드가 들어가서 톱니바퀴하나처럼 동장한다.
     * 이미 만들어진 거대한 프레임워크에 네 코드가 들어가서 프로그램의 주도권이 프레임워크한테 있다. 이게 역전
     *
     * Ioc container 가 객체를 직접적으로 생성해주고 셩명주기를 관리해준다.
     * DI로 의존성 주입도 해줄게
     * 내가 다해줄게
     * @BEAN 을 생성해주거나 의존성을 주입해준다
     *
     *
     * AOP
     *
     *
     *
     */
}
