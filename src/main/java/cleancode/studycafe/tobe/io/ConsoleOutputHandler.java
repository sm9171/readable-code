package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePasses;

public class ConsoleOutputHandler implements OutputHandler {
    @Override
    public void showWelcomeMessage() {
        printLn("*** 프리미엄 스터디카페 ***");
    }

    @Override
    public void showAnnouncement() {
        printLn("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        printLn("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        printLineBreak();
    }

    @Override
    public void askPassTypeSelection() {
        printLn("사용하실 이용권을 선택해 주세요.");
        printLn("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    @Override
    public void showPassListForSelection(StudyCafePasses passes) {
        printLineBreak();
        printLn("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePass pass = passes.get(index);
            printLnWithArgument("%s. " + pass.display(), String.valueOf(index + 1));
        }
    }

    @Override
    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        printLineBreak();
        printLnWithArgument("사물함을 이용하시겠습니까? (%s)", lockerPass.display());
        printLn("1. 예 | 2. 아니오");
    }

    @Override
    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        printLineBreak();
        printLn("이용 내역");
        printLnWithArgument("이용권: %s", selectedPass.display());
        if (lockerPass != null) {
            printLnWithArgument("사물함: %s", lockerPass.display());
        }

        double discountRate = selectedPass.getDiscountRate();
        int discountPrice = (int) (selectedPass.getPrice() * discountRate);
        if (discountPrice > 0) {
            printLnWithArgument("이벤트 할인 금액: %s원", String.valueOf(discountPrice));
        }

        int totalPrice = selectedPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
        printLnWithArgument("총 결제 금액: %s원", String.valueOf(totalPrice));
        printLineBreak();
    }

    @Override
    public void showSimpleMessage(String message) {
        printLn(message);
    }

    private static void printLineBreak() {
        printLn("");
    }

    private static void printLn(String message) {
        System.out.println(message);
    }

    private static void printLnWithArgument(String message, String argument) {
        System.out.println(String.format(message, argument));
    }
}
