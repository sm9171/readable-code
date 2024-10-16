package cleancode.studycafe.lecture;

import cleancode.studycafe.lecture.exception.AppException;
import cleancode.studycafe.lecture.io.provider.LockerPassFileReader;
import cleancode.studycafe.lecture.io.provider.SeatPassFileReader;
import cleancode.studycafe.lecture.model.order.StudyCafePassOrder;
import cleancode.studycafe.lecture.model.pass.StudyCafePassType;
import cleancode.studycafe.lecture.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.lecture.model.pass.locker.StudyCafeLockerPasses;
import cleancode.studycafe.lecture.model.pass.seat.StudyCafeSeatPass;
import cleancode.studycafe.lecture.model.pass.seat.StudyCafeSeatPasses;
import cleancode.studycafe.lecture.provider.LockerPassProvider;
import cleancode.studycafe.lecture.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {
    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final SeatPassProvider seatPassProvider;
    private final LockerPassProvider lockerPassProvider;

    public StudyCafePassMachine(final SeatPassProvider seatPassProvider, final LockerPassProvider lockerPassProvider) {
        this.seatPassProvider = seatPassProvider;
        this.lockerPassProvider = lockerPassProvider;
    }

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafeSeatPass selectedPass = selectPass();
            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            StudyCafePassOrder passOrder = StudyCafePassOrder.of(
                    selectedPass,
                    optionalLockerPass.orElse(null)
            );

            ioHandler.showPassOrderSummary(passOrder);
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafeSeatPass selectPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();
        List<StudyCafeSeatPass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafeSeatPass> findPassCandidatesBy(final StudyCafePassType studyCafePassType) {
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();
        return allPasses.findPassBy(studyCafePassType);
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(final StudyCafeSeatPass selectedPass) {
        // 고정 좌석 타입이 아닌가?
        // 사물함 옵션을 사용할 수 있는 타입이 아닌가?
        if (selectedPass.cannotUserLocker()) {
            return Optional.empty();
        }

        Optional<StudyCafeLockerPass> lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate.isPresent()) {
            StudyCafeLockerPass lockerPass = lockerPassCandidate.get();

            boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);
            if (isLockerSelected) {
                return Optional.of(lockerPass);
            }
        }
        return Optional.empty();
    }

    private Optional<StudyCafeLockerPass> findLockerPassCandidateBy(final StudyCafeSeatPass pass) {
        StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getLockerPasses();
        return allLockerPasses.findLockerPassBy(pass);
    }
}
