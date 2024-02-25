package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b15824 {
	public static long half(int num,int a) { 
		if(a==0) {
			return 1;
		}
		long halfnum=half(num,a/2); //분할정복진행
		if(a%2==0) {
			return halfnum * halfnum % 1000000007;
		}else {
			return halfnum * halfnum * num % 1000000007;
		}		
	}
		
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		long sum=0;
		for(int i=0;i<n;i++) { 
			sum+= arr[i]*half(2,i);
			sum-= arr[i]*half(2,n-i-1);
			sum = sum % 1000000007;
		}
		System.out.println(sum);
	}
}
