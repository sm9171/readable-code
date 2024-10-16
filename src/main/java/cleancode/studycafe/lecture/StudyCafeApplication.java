package cleancode.studycafe.lecture;

import cleancode.studycafe.lecture.io.provider.LockerPassFileReader;
import cleancode.studycafe.lecture.io.provider.SeatPassFileReader;
import cleancode.studycafe.lecture.provider.LockerPassProvider;
import cleancode.studycafe.lecture.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
                seatPassProvider,
                lockerPassProvider
        );
        studyCafePassMachine.run();
    }
}
