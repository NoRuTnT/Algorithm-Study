import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int res;
	static int[][][] dp; //scv3마리 dp배열생성
	static int[][] dmg = {{9,3,1},{9,1,3},{3,9,1},{3,1,9},{1,3,9},{1,9,3}}; //경우의수 배열에 다저장
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[3];
		if(n==1) { //한마리일때
			arr[0]=Integer.parseInt(br.readLine());
		}else if(n==2) { //두마리일때
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<2;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
		}else { //세마리일때
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<3;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
		}		
		dp = new int [61][61][61]; //scv의 최대체력 60
		res=987654321; //최소값생성
		dfs(arr,0);	
		System.out.println(res);
	}
	private static void dfs(int[] arr, int depth) { 
		int a= arr[0]; //파라미터로 입력받은 scv체력 다 풀기
		int b= arr[1];
		int c= arr[2];
		if(dp[a][b][c]<=depth && dp[a][b][c]!=0) { //현재abc들고있는 depth보다 dp에 저장된abc의 depth가 더작다면 이번case는 바로버림
			return;
		}
		if(a==0 && b==0 && c==0) { // scv가 다죽었다면 res계산하고 끝
			res = Math.min(res, depth);
			return;
		}
		dp[a][b][c]=depth; // 이번 depth를 dp배열에 저장
		
		for(int i=0;i<6;i++) {	//데미지줄수있는 6가지경우를 다돌림
			int a1=a-dmg[i][0];			
			int b1=b-dmg[i][1];
			int c1=c-dmg[i][2];
      //음수가되었다면 그냥0으로맞춰줌
			if(a1<0) { 
				a1=0;
			}
			if(b1<0) {
				b1=0;
			}
			if(c1<0) {
				c1=0;
			}
			dfs(new int[] {a1,b1,c1}, depth+1); //depth늘려서 반복
		}
	}

}
