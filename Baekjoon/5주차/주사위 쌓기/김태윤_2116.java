package aps스터디5주차;

import java.util.Scanner;

public class 김태윤_2116 {
	
	public static void main(String[] args) {
		//A,B,C 면 중 하나를 바닥에 선택하면
		//그담엔 옆면들 중에서 큰 값을 그냥 선택하면 된다
		//주사위 총 만개니까 넉넉하게 60만번 비교해서 더하면 된다
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] dice=new int[n+1][6];
		for(int i=0;i<n;i++) {
			for(int j=0;j<6;j++) {
				dice[i][j]=sc.nextInt();
			}
		}
		int[] parallel = {5,3,4,1,2,0};
		int ans=-1;
		for(int start=0;start<=5;start++) {
			int bottom=start; // 처음 바닥번호
			int top=parallel[bottom]; // 바닥과 마주보는 번호
			int sum=0;
			for(int i=0;i<n;i++) {
				//1) 옆면 중 가장 큰 값 구하기
				int max=-1;
				for(int j=0;j<6;j++) {
					if(j==bottom || j==top) continue;
					if(max<dice[i][j]) max=dice[i][j];
				}
				//2) 구한 큰 값을 sum에 더하기
				sum+=max;
				if(i==n-1) break;
				//3) 윗면이 다음 주사위의 아랫면이 된다
				for(int j=0;j<6;j++) {
					if(dice[i+1][j]==dice[i][top]) {
						bottom=j;
						top=parallel[bottom];
						break;
					}
				}
			}
			if(ans<sum) {
				ans=sum;
			}
		}
		System.out.println(ans);
	}
}
