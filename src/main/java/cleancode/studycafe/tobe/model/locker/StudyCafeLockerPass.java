package cleancode.studycafe.tobe.model.locker;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

public class StudyCafeLockerPass implements StudyCafePass {

  private final StudyCafePassType passType;
  private final int duration;
  private final int price;

  private StudyCafeLockerPass(StudyCafePassType passType, int duration, int price) {
    this.passType = passType;
    this.duration = duration;
    this.price = price;
  }

  public static StudyCafeLockerPass of(StudyCafePassType passType, int duration, int price) {
    return new StudyCafeLockerPass(passType, duration, price);
  }

  public boolean isSamePassType(StudyCafePassType passType) {
    return this.passType == passType;
  }

  public boolean isSameDuraiton(int duration) {
    return this.duration == duration;
  }

  @Override
  public StudyCafePassType getPassType() {
    return passType;
  }
  @Override
  public int getDuration() {
    return duration;
  }
  @Override
  public int getPrice() {
    return price;
  }


}
