package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_1111 {
	static int n;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	private static void solve(){
		if(isSpecialCase()) return;
		for(int a=-500; a<=500; a++){
			int b=nums[1]-nums[0]*a;
			if(calc(a,b)){
				System.out.println(a*nums[n-1]+b);
				return;
			}
		}
		System.out.println("B");
	}
	private static boolean calc(int a, int b){
		for(int i=2; i<n; i++){
			if(nums[i]!=nums[i-1]*a+b) return false;
		}
		return true;
	}
	private static boolean isSpecialCase(){
		if(n<=2){
			//2개만 있고 서로 같은 경우: 무조건 다음에 같은 값 온다
			if(n==2 && nums[0]==nums[1])
				System.out.println(nums[0]);
			//2개 이하만 있고 서로 다른 경우: 무조건 다음에 여러가지 될 수 있다.
			else
				System.out.println("A");
			return true;
		}
		return false;
	}
	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		StringTokenizer st=new StringTokenizer(br.readLine());
		nums=new int[n];
		for(int i=0; i<n; i++){
			nums[i]=Integer.parseInt(st.nextToken());
		}
		br.close();
	}
}
