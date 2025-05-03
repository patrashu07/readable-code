package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.StudyCafeSeatPasses;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.locker.StudyCafeLockerPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LockerPassFileReader implements LockerPassProvider {


  public static final String LOCKER_LIST_PATH_CSV = "src/main/resources/cleancode/studycafe/locker.csv";

  @Override
  public StudyCafeLockerPasses getSLockerPasses() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(LOCKER_LIST_PATH_CSV));
      List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
      for (String line : lines) {
        String[] values = line.split(",");
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);

        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
        lockerPasses.add(lockerPass);
      }

      return StudyCafeLockerPasses.of(lockerPasses);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }
}
