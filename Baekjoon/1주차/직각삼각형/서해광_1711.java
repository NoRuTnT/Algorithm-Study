// 1711. 직각삼각형
import java.util.Scanner;
public class Main {
	static class Point{
		long x;
		long y;
		public Point() {
		}
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Point[] arr = new Point[N];
		for(int i=0 ; i<N ; i++) {
			long x = sc.nextLong();
			long y = sc.nextLong();
			arr[i] = new Point(x, y);
		}
		int cnt = 0;
    // 3개의 점을 선택하는 조합을 전부 확인.
		for(int i=0 ; i<N-2 ; i++) {
			for(int j=i+1 ; j<N-1 ; j++) {
				for(int k=j+1 ; k<N ; k++) {
					if(check(arr[i], arr[j], arr[k]))
						cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	public static boolean check(Point a, Point b, Point c) {
        // 일직선위에 있으면서 피타고라스 공식을 만족하는 점은 수학적으로 있을 수 없다.
		long d1 = (a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y);
		long d2 = (b.x - c.x)*(b.x - c.x) + (b.y - c.y)*(b.y - c.y);
		long d3 = (c.x - a.x)*(c.x - a.x) + (c.y - a.y)*(c.y - a.y);
		if(d1==d2+d3 || d2==d1+d3 || d3==d1+d2) {
			return true;
		}
			
		return false;
	}
}
