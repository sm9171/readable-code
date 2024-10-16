package cleancode.studycafe.lecture;

import cleancode.studycafe.lecture.exception.AppException;
import cleancode.studycafe.lecture.io.InputHandler;
import cleancode.studycafe.lecture.io.OutputHandler;
import cleancode.studycafe.lecture.io.StudyCafeFileHandler;
import cleancode.studycafe.lecture.model.StudyCafeLockerPass;
import cleancode.studycafe.lecture.model.StudyCafePass;
import cleancode.studycafe.lecture.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            StudyCafePass selectedPass = getSelectedPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                    lockerPass -> outputHandler.showPassOrderSummary(selectedPass, lockerPass),
                    () -> outputHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass getSelectedPass() {
        outputHandler.askPassTypeSelection();
        StudyCafePassType passType = inputHandler.getPassTypeSelectingUserAction();

        List<StudyCafePass> passCandidates = findPathCandidatesBy(passType);

        outputHandler.showPassListForSelection(passCandidates);
        return inputHandler.getSelectPass(passCandidates);
    }

    private List<StudyCafePass> findPathCandidatesBy(final StudyCafePassType studyCafePassType) {
        List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();

        return allPasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
                .toList();
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(final StudyCafePass selectedPass) {
        if (selectedPass.getPassType() != StudyCafePassType.FIXED) {
            return Optional.empty();
        }
        StudyCafeLockerPass lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            outputHandler.askLockerPass(lockerPassCandidate);
            boolean lockerSelected = inputHandler.getLockerSelection();

            if (lockerSelected) {
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private StudyCafeLockerPass findLockerPassCandidateBy(final StudyCafePass pass) {
        List<StudyCafeLockerPass> allLockerPasses = studyCafeFileHandler.readLockerPasses();

        return allLockerPasses.stream()
                .filter(lockerPass ->
                        lockerPass.getPassType() == pass.getPassType()
                                && lockerPass.getDuration() == pass.getDuration()
                )
                .findFirst()
                .orElse(null);
    }
}
