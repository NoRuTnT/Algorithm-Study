package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b2098 {
    static int maxbit,min,n;
    static int[][] dp,map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxbit = (1<<n)-1; // 1이 n개
        dp = new int[n][maxbit];
        for(int i=0;i<n;i++) {
        	Arrays.fill(dp[i],987654321);        	
        }
        System.out.println(makedp(0,1));


    }
    private static int makedp(int city,int visit) {  	
        if(visit == maxbit){
        	if(map[city][0] == 0) {
        		return 987654321;
        	}else {
        		return map[city][0];        		
        	}
        }
        if(dp[city][visit] != 987654321){
            return dp[city][visit];
        }
        for(int i=0;i<n;i++) {
        	if(map[city][i]!=0 && (visit & (1<<i)) == 0 ) {        		
        		dp[city][visit] = Math.min(dp[city][visit], makedp(i,visit | (1 << i)) + map[city][i]);	       		
        	}
        }
        return dp[city][visit];

    }
}
