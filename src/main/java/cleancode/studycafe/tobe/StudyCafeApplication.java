package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.provider.LockerPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
      SeatPassProvider seatPassProvider = new SeatPassFileReader();
      LockerPassFileReader lockerPassFileReader = new LockerPassFileReader();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(seatPassProvider,lockerPassFileReader);
        studyCafePassMachine.run();
    }

  /* 스터디카페 이용권 시스템
   *   시간권 : 2, 4 ,6,8,10,12 시간
   *   주권  : 1,2,3,4, 12 주
   *   고정석 : 4주 , 12주
   *
   *   사물함 : 고정석인 경우만 사용 가능
   *
   *   최종 결제 진행
   *   오픈 이벤트 : 2주권 10 % , 12주권 ,15% 할인
   *
   *
   *  리팩토링
   *   1. 추상화 레벨
   *       - 중복 제거 , 메서드 추출
   *       - 객체에 메시지 보내기
   *   2.  객체 묶어보기
   *   3. 객체 지향 패러다임
   *   4. SRP : 단일 원칙 책임
   *   5. DIP : 의존관계 역전
   *   6. 일급 컬렉션
   *
   * */
}
