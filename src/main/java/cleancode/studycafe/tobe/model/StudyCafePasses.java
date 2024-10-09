package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafePasses {
    private final List<StudyCafePass> studyCafePasses;

    public StudyCafePasses(List<StudyCafePass> studyCafePasses) {
        this.studyCafePasses = studyCafePasses;
    }

    public static StudyCafePasses of(List<StudyCafePass> studyCafePasses) {
        return new StudyCafePasses(studyCafePasses);
    }

    public StudyCafePasses getHourlyPasses() {
        List<StudyCafePass> hourlyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.HOURLY)
                .toList();
        return of(hourlyPasses);
    }

    public StudyCafePasses getWeeklyPasses() {
        List<StudyCafePass> weeklyPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.WEEKLY)
                .toList();
        return of(weeklyPasses);
    }

    public StudyCafePasses getFixedPasses() {
        List<StudyCafePass> fixedPasses = studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == StudyCafePassType.FIXED)
                .toList();
        return of(fixedPasses);
    }

    public int size() {
        return studyCafePasses.size();
    }

    public StudyCafePass get(int index) {
        return studyCafePasses.get(index);
    }
}
