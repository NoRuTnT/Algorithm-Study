// 2565. 전깃줄

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static int N;
	static class Line implements Comparable<Line>{
		int a;
		int b;
		public Line(int a, int b) {
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Line o) {
			return this.a - o.a;
		}
		
	}
	public static List<Line> list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		list = new ArrayList<>();
		for(int i=0 ; i<N ; i++) {
			list.add(new Line(sc.nextInt(), sc.nextInt()));
		}
		Collections.sort(list); // a에 대해 오름차순 정렬
		int max = 1;
		int[] dp = new int[N]; // 최장길이 수열을 구하기 위한 dp 배열 O(N^2)
		for(int i=0 ; i<N ; i++) {
			dp[i] = 1;
			for(int j=0 ; j<i ; j++) {
				if(list.get(j).b<list.get(i).b)
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
			max = Math.max(dp[i], max);
		}
		System.out.println(N-max);
	}
}
// 8 2 9 1 4 6 7 10
