import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_10473 {
    static class Edge{
        int to;
        double value;
        Edge(int to, double value){

            this.to=to;
            this.value=value;
        }
    }
    static double[][] point;
    static double[] start;
    static double[] end;
    static double[][] edge;
    static int n;
    static double ans;
    static boolean[] check;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            if(o1.value<o2.value) return -1;
            else return 1;
        }
    });
    public static void main(String[] args) throws IOException {
        input();
        solve();
        System.out.printf("%.6f", ans);
    }
    public static void solve(){
        Edge curr=new Edge(0,0);
        check[0]=true;
        while(curr.to!=1 && curr.value<ans){
            Edge next=pq.poll();
            if(check[next.to]) continue;
            for(int i=1;i<=n+1;i++){
                if(next.to==i || check[i]) continue;
                pq.offer(new Edge(i, next.value+edge[next.to][i]));
            }
            curr=next;
            check[next.to]=true;
        }
        if(curr.value<ans) ans=curr.value;
    }
    public static void input() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        start=new double[2];
        end=new double[2];
        StringTokenizer st=new StringTokenizer(br.readLine());
        start[0]=Double.parseDouble(st.nextToken());
        start[1]=Double.parseDouble(st.nextToken());
        st=new StringTokenizer(br.readLine());
        end[0]=Double.parseDouble(st.nextToken());
        end[1]=Double.parseDouble(st.nextToken());
        n= Integer.parseInt(br.readLine());
        point=new double[n+2][2];
        check=new boolean[n+2];
        edge=new double[n+2][n+2];
        point[0][0]=start[0];
        point[0][1]=start[1];
        point[1][0]=end[0];
        point[1][1]=end[1];
        for(int i=2;i<=n+1;i++){
            st=new StringTokenizer(br.readLine());
            point[i][0]=Double.parseDouble(st.nextToken());
            point[i][1]=Double.parseDouble(st.nextToken());
        }
        br.close();
        for(int j=1;j<=n+1;j++){
            edge[0][j]=dist(0,j)/5;
            edge[j][0]=edge[0][j];
            pq.offer(new Edge(j,edge[0][j]));
        }
        ans=edge[0][1]; // 그냥 시작 - 끝 직선으로
        for(int i=1;i<n+1;i++){
            for(int j=i+1;j<=n+1;j++){
                edge[i][j]=Math.abs(dist(i,j)-50)/5+2; // 대포거리는 무조건 50m 고정이라서 다음 대포보다 멀리 나갈수도 있다
                // 절댓값 활용해서 시간 계산하기
                edge[j][i]=edge[i][j];
            }
        }
    }
    public static double dist(int from, int to){
        return Math.sqrt((point[from][0]-point[to][0])*(point[from][0]-point[to][0])+(point[from][1]-point[to][1])*(point[from][1]-point[to][1]));
    }
}
