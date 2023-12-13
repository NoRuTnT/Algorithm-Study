package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class b1994 {
	  static int n;
	    static int[] arr;
	    static Map<Integer, Integer>[] dp;

	    public static void main(String[] args) throws NumberFormatException, IOException {
	    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        n = Integer.parseInt(br.readLine());
	        arr = new int[n];
	        dp = new Map[n];

	        for (int i=0;i<n;i++) {
	            arr[i] = Integer.parseInt(br.readLine());
	            dp[i] = new HashMap<>();
	        }
	        Arrays.sort(arr);  //오름차순정렬
	        System.out.println(makedp());	        
	    }

		private static int makedp() {
			if(n==1) { //배열길이가 1인경우 바로1출력하고끝냄
				return 1;
			}
			int max = 0;			
	        for (int i=0;i<n;i++) { //배열원소하나씩진행
	            for (int j=0;j<i;j++) { //i이전의 원소들 모두비교
	                int d = arr[i]-arr[j]; //공차
	                dp[i].putIfAbsent(d, 2); // dp[i]에 공차d가아직없으면 key:d에 기본값인 2를 넣어준다
	                if (dp[j].containsKey(d)) { //이미 이전 dp[j] key에 등차d가 존재한다면
	                    dp[i].put(d,dp[j].get(d)+1); //dp[j] key:d 값에다 1더한값을 dp[i] key:d에 저장
	                }
	                max = Math.max(dp[i].get(d),max); //최대값비교후변경
	            }
	        }
	        return max;			
		}
	}


