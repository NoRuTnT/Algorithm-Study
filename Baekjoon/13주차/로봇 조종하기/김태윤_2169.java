import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] dp;
    static int[] dr={1,0,0};
    static int[] dc={0,-1,1}; // 하 좌 우
    static boolean[][] check;
    static int n;
    static int m;
    static int ans=-987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        map=new int[n][m];
        dp=new int[n][m][3]; // 칸& 방향
        check=new boolean[n][m];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], -987654321);
            }
        }
        ans=recursion(0,0,0); //시작 행, 시작 열, 이동 방향
        System.out.println(ans);
    }
    public static int recursion(int r, int c, int dir){
        if(r==n-1 && c==m-1) {
            return map[n-1][m-1];
        }
        if(dp[r][c][dir]>-987654321) return dp[r][c][dir]; // 이미 기록이 있는 경우 return
        check[r][c]=true;
        int bigger=-987654321;
        for(int k=0;k<3;k++){
            int nextR=r+dr[k];
            int nextC=c+dc[k];
            if(nextR<0 || nextR>=n || nextC<0 || nextC>=m) continue; // 경계조건
            if(check[nextR][nextC]) continue; // 방문처리
            bigger=Math.max(bigger, recursion(nextR, nextC, k));
        }
        check[r][c]=false;
        dp[r][c][dir]=map[r][c]+bigger;
        return dp[r][c][dir];
    }
}
