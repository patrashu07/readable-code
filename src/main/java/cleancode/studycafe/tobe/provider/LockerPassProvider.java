package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.asis.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPasses;

public interface LockerPassProvider {

  StudyCafeLockerPasses getSLockerPasses();

}
