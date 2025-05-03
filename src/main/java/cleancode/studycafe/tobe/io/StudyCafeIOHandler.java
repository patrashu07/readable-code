package cleancode.studycafe.tobe.io;

import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafeIOHandler {
  private final InputHandler inputHandler = new InputHandler();
  private final OutputHandler outputHandler = new OutputHandler();


  public void showWelcomeMessage() {
    outputHandler.showWelcomeMessage();
  }

  public void showAnnouncement() {
    outputHandler.showAnnouncement();
  }

  public void showPassOrderSummary(StudyCafeSeatPass selectedPass, StudyCafeLockerPass lockerpass) {
    outputHandler.showPassOrderSummary(selectedPass,lockerpass);
  }

  public void showPassOrderSummary(StudyCafeSeatPass selectedPass) {
    outputHandler.showPassOrderSummary(selectedPass);
  }


  public void showSimpleMessage(String message) {
    outputHandler.showSimpleMessage(message);
  }

  public StudyCafePassType askPassTypeSelecting() {
    outputHandler.askPassTypeSelection();
    return inputHandler.getPassTypeSelectingUserAction();
  }

  public StudyCafeSeatPass askPassSelecting(List<StudyCafeSeatPass> passCandidates) {
    outputHandler.showPassListForSelection(passCandidates);
    return inputHandler.getSelectPass(passCandidates);
  }

  public boolean askLockerPass(StudyCafeLockerPass lockerPassCandidate) {
    outputHandler.askLockerPass(lockerPassCandidate);
    boolean isLockerSelected = inputHandler.getLockerSelection();
    return isLockerSelected;
  }
}
