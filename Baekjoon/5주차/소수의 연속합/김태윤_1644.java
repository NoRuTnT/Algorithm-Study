package aps스터디5주차;

import java.util.ArrayList;
import java.util.Scanner;

public class 김태윤_1644 {
	static boolean[] check;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		check=new boolean[n+1];
		//1) n이하의 소수 구하기
		ArrayList<Integer> primeNum=new ArrayList<>();
		for(int i=2;i<=n;i++) {
			if(!check[i]) {
				primeNum.add(i);
				int curr=i;
				while(curr<=n) {
					check[curr]=true;
					curr+=i;
				}
			}
		}
		//2) 구한 소수 배열을 부분합으로 만들기
//		for(int i=1;i<primeNum.size();i++) {
//			primeNum.set(i, primeNum.get(i)+primeNum.get(	i-1));
//		}
		//3) 부분합 활용해서 연속된 소수합 개수 구하기
		int ans=0, sum=0;
		int l=0, r=0;
		while(l<primeNum.size()) {
			while(r<primeNum.size() && sum<n) sum+=primeNum.get(r++);
			
			while(sum>n) sum-=primeNum.get(l++);
			
			if(sum==n) {
				ans++;
				sum-=primeNum.get(l++);
			}
			
			else if(r==primeNum.size()) {
				sum-=primeNum.get(l++);
				if(sum==n) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
