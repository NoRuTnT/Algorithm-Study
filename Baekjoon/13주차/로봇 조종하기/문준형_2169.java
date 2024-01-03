package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b2169 {
    static int[] dr = {1,0,0};
    static int[] dc = {0,-1,1};
    static int n,m;
    static int[][]map;
    static int[][][]dp;
    static boolean[][] visit;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visit = new boolean[n][m];
        dp = new int[3][n][m];
        for(int i=0;i<n;i++) {
            Arrays.fill(dp[0][i], -987654321);
            Arrays.fill(dp[1][i], -987654321);
            Arrays.fill(dp[2][i], -987654321);
        }
        System.out.println(makedp(0,0,0));
        
    }
    private static int makedp(int i, int j, int dir) {
        if(i==n-1 && j==m-1) {
            return map[n-1][m-1];
        }
        
        if(dp[dir][i][j] != -987654321) {
            return dp[dir][i][j];
        }
        visit[i][j]=true;
        int max=-987654321;
        for(int d=0;d<3;d++) {
        	int r = i+dr[d];
        	int c = j+dc[d];
        	if(r>=0 && r<n && c>=0 && c<m) {
        		if(!visit[r][c]) {
        			max = Math.max(max, makedp(r,c,d));
        		}
        	}        	
        }
        visit[i][j]=false;
        dp[dir][i][j] = map[i][j]+max;
        return dp[dir][i][j];
    }

}
