import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int startR, startC, endR, endC;
    static char[][] map;
    static int[][][] isVisited;
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        input();
        for(int k=0; k<4; k++){
            isVisited[startR][startC][k]=0;
            int r = startR+dr[k];
            int c = startC+dc[k];
            if(r<0 || r>=h || c<0 || c>=w) continue;
            if(map[r][c]=='*') continue;
            dfs(r, c, k, 0);
        }
        System.out.println(ans);
    }
    static void input() throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w=Integer.parseInt(st.nextToken());
        h=Integer.parseInt(st.nextToken());
        map = new char[h][w];
        isVisited = new int[h][w][4];
        boolean flag = false;
        for(int i=0; i<h; i++){
            map[i]=br.readLine().toCharArray();
            for(int j=0; j<w; j++){
                Arrays.fill(isVisited[i][j],Integer.MAX_VALUE);
                if(map[i][j]=='C'){
                    if(!flag){
                        startR = i;
                        startC = j;
                        flag=true;
                    }
                    else{
                        endR = i;
                        endC = j;
                    }
                }
            }
        }
        br.close();
    }

    static void dfs(int r, int c, int dir, int turn){
        if(r==endR && c==endC){
            ans = Math.min(ans, turn);
            return;
        }
        if(turn>=ans) return;
        isVisited[r][c][dir]=turn;
        for(int k=0; k<4; k++){
            int nextR=r+dr[k];
            int nextC=c+dc[k];
            if(nextR<0 || nextR>=h || nextC<0 || nextC>=w) continue;
            if(dir+k == 3) continue; // 왔던 방향인 경우
            if(map[nextR][nextC]=='*') continue;
            if(k==dir && isVisited[nextR][nextC][k]>turn) dfs(nextR, nextC, k, turn);
            else if(k!=dir && isVisited[nextR][nextC][k]>turn+1) dfs(nextR, nextC, k, turn+1);
        }
    }
}
