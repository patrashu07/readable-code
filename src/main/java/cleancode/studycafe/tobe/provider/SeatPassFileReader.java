package cleancode.studycafe.tobe.provider;

import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.model.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.StudyCafeSeatPasses;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SeatPassFileReader implements SeatPassProvider {

  public static final String PATH_LIST_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";

  @Override
  public StudyCafeSeatPasses getSeatPasses() {
    try {
      List<String> lines = Files.readAllLines(Paths.get(PATH_LIST_CSV));
      List<StudyCafeSeatPass> studyCafeSeatPasses = new ArrayList<>();
      for (String line : lines) {
        String[] values = line.split(",");
        StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
        int duration = Integer.parseInt(values[1]);
        int price = Integer.parseInt(values[2]);
        double discountRate = Double.parseDouble(values[3]);

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(studyCafePassType, duration, price, discountRate);
        studyCafeSeatPasses.add(studyCafeSeatPass);
      }

      return StudyCafeSeatPasses.of(studyCafeSeatPasses);
    } catch (IOException e) {
      throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
    }
  }
}
