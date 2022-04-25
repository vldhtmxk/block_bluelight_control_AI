package block_blue_light_AI;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ScreenLight 소속함수
		int[][] slF = { { -1, 1, 20, 40 }, { 20, 40, 60, 80 }, { 60, 80, 100, 120 } };
		// UsingTime 소속함수
		int[][] utF = { { -1, 0, 1, 3 }, { 2, 4, 5, 7 }, { 5, 7, 8, 10 }, { 8, 10, 20, 24 } };
		//블루라이트 차단율 소속함수
		int[][] brF = {{0,0,20,20},{20,20,40,40},{40,40,60,60},{60,60,80,80},{80,80,100,100}};
		
		Scanner scan = new Scanner(System.in);
		//화면 밝기 입력
		System.out.println("화면 밝기 0~100(%)");
		int screenLight = scan.nextInt();
		//사용 시간 입력
		System.out.println("사용 시간 0~24(H)");
		int usingTime = scan.nextInt();
		
	
		Membership sl = new Membership(screenLight, slF);
		Membership ut = new Membership(usingTime, utF);
		
		//1. Fuzzification
		sl.doFuzzification();
		ut.doFuzzification();
		
		BlockingRate controlBlockingRate = new BlockingRate(sl.getDegree(), ut.getDegree());
		
		//2. Fuzzy Logic Operation
		controlBlockingRate.doFuzzyLogicOperation();
		
		//3. Implication
		controlBlockingRate.doImplication(brF);
		
		//4. Aggregation
		controlBlockingRate.doAggregation();
		
		//5. Defuzzification
		controlBlockingRate.doDefuzzification();
		
		//결과 출력
		controlBlockingRate.printResult();
	}

}
