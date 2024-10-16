package cleancode.studycafe.lecture.model.pass.seat;

import cleancode.studycafe.lecture.model.pass.StudyCafePassType;
import cleancode.studycafe.lecture.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.lecture.model.pass.StudyCafePass;

public class StudyCafeSeatPass implements StudyCafePass {


    private final StudyCafePassType passType;
    private final int duration;
    private final int price;
    private final double discountRate;

    private StudyCafeSeatPass(StudyCafePassType passType, int duration, int price, double discountRate) {
        this.passType = passType;
        this.duration = duration;
        this.price = price;
        this.discountRate = discountRate;
    }

    public static StudyCafeSeatPass of(StudyCafePassType passType, int duration, int price, double discountRate) {
        return new StudyCafeSeatPass(passType, duration, price, discountRate);
    }

    public boolean isSamePassType(StudyCafePassType passType) {
        return this.passType == passType;
    }

    public boolean isSameDurationType(StudyCafeLockerPass lockerPass) {
        return lockerPass.isSamePassType(this.passType)
                && lockerPass.isSameDuration(this.duration);
    }

    public boolean cannotUserLocker() {
        return this.passType.isNotLockerType();
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public StudyCafePassType getPassType() {
        return this.passType;
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
