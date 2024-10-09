package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafePasses;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = getUserInput();

        if ("1".equals(userInput)) {
            return StudyCafePassType.HOURLY;
        }
        if ("2".equals(userInput)) {
            return StudyCafePassType.WEEKLY;
        }
        if ("3".equals(userInput)) {
            return StudyCafePassType.FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }

    @Override
    public StudyCafePass getSelectPass(StudyCafePasses passes) {
        String userInput = getUserInput();
        int selectedIndex = getSelectedIndex(userInput);
        return passes.get(selectedIndex);
    }

    @Override
    public boolean getLockerSelection() {
        String userInput = getUserInput();
        return "1".equals(userInput);
    }

    private static String getUserInput() {
        String userInput = SCANNER.nextLine();
        return userInput;
    }

    private static int getSelectedIndex(String userInput) {
        return Integer.parseInt(userInput) - 1;
    }
}
