package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b2342 {
	static int len;
	static int[][][]dp;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new ArrayList<>();
		len=0;
		while(true) {
			int a = Integer.parseInt(st.nextToken());
			if(a==0) {
				break;
			}
			list.add(a);
			len++;
		}
		dp = new int[len][5][5];
		System.out.println(makedp(0,0,0));
		
		
	}
	
	private static int makedp(int depth, int l, int r) {
		if(depth==len) {
			return 0;
		}
		if(dp[depth][l][r]!=0) {
			return dp[depth][l][r];
		}
		int next = list.get(depth);
		int lcost = movecost(l,next) + makedp(depth+1,next,r); //왼발로 다음발판밟은경우 왼발위치바꿔주고 코스트더해준다
		int rcost = movecost(r,next) + makedp(depth+1,l,next); //오른발로 다음발판밟은경우 오른발위치바꿔주고 코스트더해준다
		dp[depth][l][r] = Math.min(lcost, rcost); //가장코스트적은거 dp배열에저장
		return dp[depth][l][r];
	}

	private static int movecost(int now, int next) {
		if(now==0) { //가운데에서 움직이는경우 cost는 2
			return 2;
		}
		if(now==next) { //제자리에서 밟는경우 cost는 1
			return 1; 
		}
		if((now+2)%4 == next%4) { // 반대편 건너가는경우 처리 (1-3)/(2-4) cost 4
			return 4; 
		}
		return 3; //나머지는 cost 3
	}	

}
