package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;

public class b2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] line = new int[n][2];
		int[] memo = new int[n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			line[i][0]=Integer.parseInt(st.nextToken());
			line[i][1]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(line, new Comparator<int[]>() { //방법찾아봄 [0]기준으로 정렬됨
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for(int i=0;i<n;i++) { //memo 배열은 각 전깃줄을 끝점으로 할 때 연결할 수 있는 최대 전깃줄 개수를 저장   
			memo[i]=1;  //최소개수
			for(int j=0;j<i;j++) {         
				if(line[i][1]>line[j][1]) { // 과거 A의전깃줄과 이어진B번호보다 현재B번호가 더커야이을수있다.(A보다큰)
					memo[i] = Math.max(memo[i], memo[j]+1); // 과거최대전깃줄개수 +1세팅
				}
			}
		}
		int max = 0;
		for(int i=0;i<n;i++) {
			max=Math.max(max, memo[i]);
		}
		
		// 전체개수-설치가능한전깃줄 = 최소철거개수 
		System.out.println(n-max);
		
	}

}
