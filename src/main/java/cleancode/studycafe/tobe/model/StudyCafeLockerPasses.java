package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafeLockerPasses {
    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    public StudyCafeLockerPasses(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new StudyCafeLockerPasses(studyCafeLockerPasses);
    }

    public StudyCafeLockerPass getLockerPass(StudyCafePass selectedPass) {
        return studyCafeLockerPasses.stream()
                .filter(option -> option.getPassType() == selectedPass.getPassType()
                        && option.getDuration() == selectedPass.getDuration())
                .findFirst()
                .orElse(null);
    }
}
