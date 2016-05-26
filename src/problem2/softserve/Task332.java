package problem2.softserve;

//import java.util.Scanner;

public class Task332 {

//	private static Scanner sc;
//	public static void main(String[] args) {
//		sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		Task332 ts4 = new Task332();
//		ts4.findFourSqrt(num);
//	}
	public void findFourSqrt(int n) {
		int  t1, t2, t;
	
		for (int i = (int) Math.sqrt(n / 4); i * i <= n; i++) {
			t1 = n - i * i;
			for (int j = (int) Math.sqrt(t1 / 3); j <= i && j * j <= t1; j++) {
				t2 = t1 - j * j;
				for (int k = (int) Math.sqrt(t2 / 2); k <= j && k * k <= t2; k++) {
					t = (int) Math.sqrt(t2 - k * k);
					if (t <= k && t * t == t2 - k * k) {
						System.out.println("(" + i + "^2) + (" + j + "^2) + ("
								+ k + "^2) + (" + t + "^2)");
					}
				}
			}
		}
	}
	

}
