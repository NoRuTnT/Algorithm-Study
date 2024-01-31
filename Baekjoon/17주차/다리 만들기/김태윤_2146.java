import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_2146 {
    static class Node{
        int r;
        int c;
        Node(int r, int c){
            this.r=r;
            this.c=c;
        }
    }
    static int n;
    static int[][] map;
    static boolean[][] isVisited;
    static int islandCount=0;
    static int[] dr={-1,0,1,0};
    static int[] dc={0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        map=new int[n][n];
        isVisited = new boolean[n][n];
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //1. 서로 다른 섬 구분시키기
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j]&&map[i][j]>0) {
                    islandCount++;
                    findIsland(i,j);
                }
            }
        }
        int min=11111;
        //2. 한 점 잡고 근처 섬 길이 구하기
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(map[i][j]>0) {
                    min=Math.min(min,findNear(i,j));
                }
            }
        }
        System.out.println(min);
    }
    public static void findIsland(int r, int c){
        isVisited[r][c]=true;
        map[r][c]=islandCount;
        for(int k=0;k<4;k++){
            if(r+dr[k]<0 || r+dr[k]>=n || c+dc[k]<0 || c+dc[k]>=n) continue;
            if(map[r+dr[k]][c+dc[k]]>0 && !isVisited[r+dr[k]][c+dc[k]]) findIsland(r+dr[k], c+dc[k]);
        }
    }
    public static int findNear(int r, int c){
        Queue<Node> queue = new LinkedList<>();
        boolean[][] check=new boolean[n][n];
        queue.offer(new Node(r,c));
        check[r][c]=true;
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            for(int k=0;k<4;k++){
                Node next = new Node(curr.r+dr[k], curr.c+dc[k]);
                if(next.r<0 || next.r>=n || next.c<0 || next.c>=n) continue; //경계
                if(check[next.r][next.c]) continue; // 방문처리
                if(map[next.r][next.c]==map[r][c]) continue; // 같은섬이면 더 뻗어갈 필요x
                if(map[next.r][next.c]>0 && map[next.r][next.c]!=map[r][c]){ // 가장 가까운 곳 도달
                    return Math.abs(next.r-r)+Math.abs(next.c-c)-1; // 다리 길이
                }
                check[next.r][next.c]=true;
                queue.offer(next);
            }
        }
        return 11111;
    }
}
