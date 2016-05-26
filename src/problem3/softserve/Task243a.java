package problem3.softserve;

public class Task243a {

	public void findTwoSqrt(int n) {
		if (n % 4 == 1) {
			int t;
			for (int i = (int) Math.sqrt(n / 2); i * i <= n; i++) {
				t = (int) Math.sqrt(n - i * i);
				if (t <= i && t * t == n - i * i) {
					System.out.println("(" + i + "^2) + (" + t + "^2)");
				}
			}
		} else {
			System.out.println("doesn't work for that number");
		}

	}
}
