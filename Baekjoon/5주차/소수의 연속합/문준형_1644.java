package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class b1644 {
	static ArrayList<Integer> prime_num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[] num = new boolean[4000001];
		num[0]=true;
		num[1]=true;
		//곱으로 나타낼수있는수 찾기
		for(int i=2;i<Math.sqrt(4000000)+1;i++) {
			if(!num[i]) {
				int j=2;
				while(i*j<=4000000) {
					num[i*j]=true;
					j++;
				}
			}
		}
		//소수리스트생성
		prime_num = new ArrayList<>();
		for(int i=0;i<4000000;i++) {
			if(!num[i]) {
				prime_num.add(i);
			}
		}
		prime_num.sort(Comparator.naturalOrder());
		System.out.println(prime(n));
	}

	private static int prime(int n) { //모든경우의수 구하기
		int cnt=0;
		for(int i=0;i<prime_num.size();i++) { 
			if(prime_num.get(i)>n) {		//n보다 큰소수는 쳐낸다
				break;
			}
			int total=0;
			for(int j=i;j<prime_num.size();j++) { 
				if(total>=n) {  //n보다 합계가 커지면 쳐낸다
					break;
				}
				total+=prime_num.get(j); //더하기
				if(total==n) { //정확히 숫자가 맞춰지면 cnt+1
					cnt++;					
				}
				
			}
			
		}
		
		return cnt;
	}

}
