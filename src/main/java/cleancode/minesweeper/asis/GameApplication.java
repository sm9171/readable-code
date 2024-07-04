package cleancode.minesweeper.asis;

import cleancode.minesweeper.asis.gamelevel.GameLevel;
import cleancode.minesweeper.asis.gamelevel.Middle;
import cleancode.minesweeper.asis.io.ConsoleInputHandler;
import cleancode.minesweeper.asis.io.ConsoleOutputHandler;

public class GameApplication {
	public static void main(String[] args) {
		GameLevel gameLevel = new Middle();
		Minesweeper minesweeper = new Minesweeper(gameLevel, new ConsoleInputHandler(), new ConsoleOutputHandler());
		minesweeper.initialize();
		minesweeper.run();
	}
}
