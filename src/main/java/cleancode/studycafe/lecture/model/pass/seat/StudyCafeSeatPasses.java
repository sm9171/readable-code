package cleancode.studycafe.lecture.model.pass.seat;

import cleancode.studycafe.lecture.model.pass.StudyCafePassType;

import java.util.List;

public class StudyCafeSeatPasses {

    private final List<StudyCafeSeatPass> passes;

    public StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
        this.passes = passes;
    }

    public static StudyCafeSeatPasses of(List<StudyCafeSeatPass> passes) {
        return new StudyCafeSeatPasses(passes);
    }

    public List<StudyCafeSeatPass> findPassBy(StudyCafePassType passType) {
        return passes.stream()
                .filter(studyCafePass -> studyCafePass.isSamePassType(passType))
                .toList();
    }
}
