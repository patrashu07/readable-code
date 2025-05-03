package cleancode.studycafe.tobe.model.locker;


import cleancode.studycafe.tobe.model.StudyCafeSeatPass;

import java.util.List;
import java.util.Optional;

public class StudyCafeLockerPasses {
  private final List<StudyCafeLockerPass> lockerPasses;

  public StudyCafeLockerPasses(List<StudyCafeLockerPass> lockerPasses) {
    this.lockerPasses = lockerPasses;
  }

  public static StudyCafeLockerPasses of (List<StudyCafeLockerPass> passes) {
    return new StudyCafeLockerPasses(passes);
  }


  public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass pass) {
    return lockerPasses.stream().filter(pass::isSameDurationType).findFirst();
  }
}
