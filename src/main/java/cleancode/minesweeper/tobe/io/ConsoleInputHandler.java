package cleancode.minesweeper.tobe.io;

import cleancode.minesweeper.tobe.BoardIndexConverter;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler{
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public UserAction getUserActionFromUser() {
        String userInput = SCANNER.nextLine();

        if ("1".equals(userInput)) {
            return UserAction.OPEN;
        }

        if ("2".equals(userInput)) {
            return UserAction.FLAG;
        }

        return UserAction.UNKNOWN;
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();

        int rowIndex = BoardIndexConverter.getSelectedRowIndex(userInput);
        int colIndex = BoardIndexConverter.getSelectedColIndex(userInput);
        return CellPosition.of(rowIndex, colIndex);
    }
}
