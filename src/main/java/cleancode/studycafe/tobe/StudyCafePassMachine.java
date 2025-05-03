package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe.model.*;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {
  private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
  private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

  public void run() {

    try {
      ioHandler.showWelcomeMessage();
      ioHandler.showAnnouncement();

      StudyCafePass selectedPass = selectPass();
      Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);
      optionalLockerPass.ifPresentOrElse(lockerpass ->  ioHandler.showPassOrderSummary(selectedPass, lockerpass),
        () -> ioHandler.showPassOrderSummary(selectedPass)
        );
    } catch (AppException e) {
      ioHandler.showSimpleMessage(e.getMessage());
    } catch (Exception e) {
      ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
    }
  }

  private StudyCafePass selectPass() {
    StudyCafePassType passType = ioHandler.askPassTypeSelecting();
    List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);
    return ioHandler.askPassSelecting(passCandidates);
  }


  private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
    //일급 컬렉션
    StudyCafePasses allPasses = studyCafeFileHandler.readStudyCafePasses();

    //가공하는 로직들이 안쪽으로 추상화되어 들어갈수잇다
    return allPasses.findPassBy(studyCafePassType);
  }

  private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
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

  private Optional<StudyCafeLockerPass> lockerPassCandidateBy(StudyCafePass pass) {
    StudyCafeLockerPasses allLockerPasses = studyCafeFileHandler.readLockerPasses();
    return  allLockerPasses.findLockerPassBy(pass);

  }

}
