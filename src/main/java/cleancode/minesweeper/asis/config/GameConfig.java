package cleancode.minesweeper.asis.config;

import cleancode.minesweeper.asis.gamelevel.GameLevel;
import cleancode.minesweeper.asis.io.InputHandler;
import cleancode.minesweeper.asis.io.OutputHandler;

public class GameConfig {
    private final GameLevel gameLevel;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public GameConfig(GameLevel gameLevel, InputHandler inputHandler, OutputHandler outputHandler) {
        this.gameLevel = gameLevel;
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }
}
