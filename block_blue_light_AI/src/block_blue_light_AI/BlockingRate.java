package block_blue_light_AI;

/*퍼지의 5단계 구현(1단계는 membership class에서 구현)*/
public class BlockingRate {
	//화면 밝기와 사용시간의 소속도
	private double[] slD;
	private double[] utD;
	//퍼지 논리 연산으로 하나가 된 소속도 값 저장
	private double[] input;
	//무게중심값 저장
	private double center = 0;
	//퍼지 집합을 도출하여 저장
	private double[] union;
	//합집합을 계산
	private double sum = 0;

	/* 두 함수의 소속도를 파라미터로 받아와 저장 */
	public BlockingRate(double[] slD, double[] utD) {
		this.slD = slD;
		this.utD = utD;
		
	}
	/* step2 : Fuzzy Logic Operation
	 * 퍼지 논리 연산을 적용하여 input 배열에 대입  
	 * */
	public void doFuzzyLogicOperation() {
		input = new double[10];
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) { //두 소속도의 min값을 적용  
				input[index] = Math.min(slD[j], utD[i]);
				index++;

			}
		}
		input[9] = utD[3]; // 단일 소속도
	}
	/* Step 3 : Implication
	 * 결과 소속함수와 대응되는 퍼지 집합 도출
	 */
	public void doImplication(int[][] brF) {
		int[] br = {0,1,2,1,2,3,2,3,4,4};
		

		union = new double[5];
		//겹치는 함수는 max를 이용하여 합집합화 한다
		for (int i = 0,end=br.length; i < end; i++) {
			union[br[i]] = Math.max(union[br[i]],
					(brF[br[i]][0] + brF[br[i]][1] + brF[br[i]][2] //소속함수의 중간값 * input
					+ brF[br[i]][3]) / 4 * input[i]);
		}
	}
	/*	Step 4 : Aggregation
	 *  퍼지 집합의 합집합을 계산한다.
	 */
	public void doAggregation() {
		//그래프의 합을 sum에 대입
		for (int i = 0, end = union.length; i < end; i++) {
			sum += union[i];
		}
	}
	/*	Step 5 : Defuzzification
	 *	계산된 퍼지 집합의 무게 중심을 구한다.
	 */
	public void doDefuzzification() {
		double denominator = 0;
		//∑(퍼지함수 결과값)
		for (int i = 0, end = input.length; i < end; i++) {
			denominator += input[i];
		}
		//그래프의 합 / ∑(퍼지함수 결과값)
		center = sum / denominator;
	}
	/*결과 getter*/
	public double getResult() {
		return center;
	}
	/*결과 print*/
	public void printResult() {
		System.out.println("차단율 : " + center);
	}
}
