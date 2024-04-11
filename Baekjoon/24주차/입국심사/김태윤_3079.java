package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_3079 {
	static int n, m;
	static long[] time;
	static long MAX=(long) Math.pow(10,18);
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		time=new long[n];
		long min=1000000001;
		for(int i=0; i<n; i++){
			time[i]=Long.parseLong(br.readLine());
			min=Math.min(time[i], min);
		}
		System.out.println(binarySearch(1L,MAX));
	}
	private static long binarySearch(long left, long right){
		if(left==right) return left;
		long mid = (left+right)/2;
		long num=0; // mid초 동안 받을 수 있는 최대 인구수
		for(int i=0; i<n; i++){
			num+=mid/time[i];
			if(num>m) break;
		}
		if(num>=m) return binarySearch(left, mid);
		else return binarySearch(mid+1, right);
	}
}
