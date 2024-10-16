package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.*;
import cleancode.studycafe.tobe.model.*;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new ConsoleInputHandler();
    private final OutputHandler outputHandler = new ConsoleOutputHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            if (studyCafePassType == StudyCafePassType.HOURLY) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                StudyCafePasses studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                StudyCafePasses hourlyPasses = studyCafePasses.getHourlyPasses();

                outputHandler.showPassListForSelection(hourlyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
                return;
            }
            if (studyCafePassType == StudyCafePassType.WEEKLY) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                StudyCafePasses studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                StudyCafePasses weeklyPasses = studyCafePasses.getWeeklyPasses();
                outputHandler.showPassListForSelection(weeklyPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
                outputHandler.showPassOrderSummary(selectedPass, null);
                return;
            }

            if (studyCafePassType == StudyCafePassType.FIXED) {
                StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
                StudyCafePasses studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
                StudyCafePasses fixedPasses = studyCafePasses.getFixedPasses();
                outputHandler.showPassListForSelection(fixedPasses);
                StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

                StudyCafeLockerPasses lockerPasses = studyCafeFileHandler.readLockerPasses();
                StudyCafeLockerPass lockerPass =lockerPasses.getLockerPass(selectedPass);

                boolean lockerSelection = false;
                if (lockerPass != null) {
                    outputHandler.askLockerPass(lockerPass);
                    lockerSelection = inputHandler.getLockerSelection();
                }

                if (lockerSelection) {
                    outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                } else {
                    outputHandler.showPassOrderSummary(selectedPass, null);
                }
            }
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

}
