package cleancode.studycafe.lecture.model.pass;

public interface StudyCafePass {
    StudyCafePassType getPassType();

    int getDuration();

    int getPrice();
}
