package cleancode.studycafe.tobe.model;

import java.util.List;

public class StudyCafeSeatPasses {
  private final List<StudyCafeSeatPass> passes;

  public StudyCafeSeatPasses(List<StudyCafeSeatPass> passes) {
    this.passes = passes;
  }

  public static StudyCafeSeatPasses of (List<StudyCafeSeatPass> passes) {
    return new StudyCafeSeatPasses(passes);
  }

  public List<StudyCafeSeatPass> findPassBy(StudyCafePassType studyCafePassType) {
      return passes.stream().filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
        .toList();
  }
}
