package cleancode.minesweeper.asis;

import cleancode.minesweeper.asis.config.GameConfig;
import cleancode.minesweeper.asis.gamelevel.Begginer;
import cleancode.minesweeper.asis.gamelevel.GameLevel;
import cleancode.minesweeper.asis.gamelevel.Middle;
import cleancode.minesweeper.asis.io.ConsoleInputHandler;
import cleancode.minesweeper.asis.io.ConsoleOutputHandler;

public class GameApplication {
	public static void main(String[] args) {
		GameConfig gameConfig = new GameConfig(new Begginer(), new ConsoleInputHandler(), new ConsoleOutputHandler());
		Minesweeper minesweeper = new Minesweeper(gameConfig);
		minesweeper.initialize();
		minesweeper.run();
	}
}
