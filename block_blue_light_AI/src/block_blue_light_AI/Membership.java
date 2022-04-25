package block_blue_light_AI;

/*
 * 소속함수를 통해 소속도를 구하는 클래스
 */
public class Membership {
	private int[][] function; //계산될 소속함수
	private double x;//입력값
	private double[] degree; //계산된 소속도 
	
	/*입력값과 그에 따른 소속함수를 파라미터로 받아옴*/
	public Membership(int x, int[][] function) {
		this.x = x;
		this.function = function;
		degree = new double[function.length];
	}
	public Membership(int [][] function) {
		this.function = function;
		degree = new double[function.length];
	}
	
	/*	Step 1 : Fuzzification
	 * 	사다리꼴 형태의 소속함수의 소속도를 구함
	 */
	public void doFuzzification() {
		for (int i = 0,end = function.length; i < end; i++) { // 각 함수에 대해 소속도를 구함
			if (x < function[i][0]) // x < a
				degree[i] = 0;
			else if (x >= function[i][0] && x < function[i][1]) //a <= x < b
				degree[i] = (x - function[i][0]) / (double)(function[i][1] - function[i][0]);
			else if (x >= function[i][1] && x < function[i][2]) // b <= x < c
				degree[i] = 1;
			else if (x >= function[i][2] && x < function[i][3]) // c <= x <d
				degree[i] = (function[i][3] - x) / (double)(function[i][3] - function[i][2]);
			else if (x >= function[i][3]) // d <= x
				degree[i] = 0;
		}
	}
	/*소속도 getter*/
	public double[] getDegree() {
		return degree;
	}
	public void setInput(double x) {
		this.x = x;
	}
}
