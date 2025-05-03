package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.model.*;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPasses;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import cleancode.studycafe.tobe.provider.LockerPassProvider;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {
  private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
  private final SeatPassProvider seatPassProvider;
  private final LockerPassProvider lockerPassProvider;

  public StudyCafePassMachine(SeatPassProvider seatPassProvider, LockerPassProvider lockerPassProvider) {
    this.seatPassProvider = seatPassProvider;
    this.lockerPassProvider = lockerPassProvider;
  }

  //헥사고날 아키택처 = 포트와 어댑터 //포트란 인터페이스 ,규격만 맞으면 가능 , 어댑터 실제 포트와 맞는 구현체
  public void run() {

    try {
      ioHandler.showWelcomeMessage();
      ioHandler.showAnnouncement();

      StudyCafeSeatPass selectedPass = selectPass();
      Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
      StudyCafePassOrder passOrder = StudyCafePassOrder.of(
        selectedPass
        ,optionalLockerPass.orElse(null)
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


  private List<StudyCafeSeatPass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
    //일급 컬렉션
    StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();

    //가공하는 로직들이 안쪽으로 추상화되어 들어갈수잇다
    return allPasses.findPassBy(studyCafePassType);
  }

  private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafeSeatPass selectedPass) {
    //사물함 옵션을 사용할 숭 있어?
    if(selectedPass.cannotUserLocker()) {
      return Optional.empty();
    }

    Optional<StudyCafeLockerPass> lockerPassCandidate = lockerPassCandidateBy(selectedPass);

    if (lockerPassCandidate.isPresent()) {
      StudyCafeLockerPass lockerPass = lockerPassCandidate.get();

      boolean isLockerSelected = ioHandler.askLockerPass(lockerPass);
      if(isLockerSelected) {
        return Optional.of(lockerPass);
      }
    }

    return Optional.empty();
  }

  private Optional<StudyCafeLockerPass> lockerPassCandidateBy(StudyCafeSeatPass pass) {
    StudyCafeLockerPasses allLockerPasses = lockerPassProvider.getSLockerPasses();
    return  allLockerPasses.findLockerPassBy(pass);

  }

}
