import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김태윤_12834 {
    static class Edge{
        int from;
    	int to;
        int value;
        Edge(){}
        Edge(int from, int to, int value){
            this.from=from;
        	this.to=to;
            this.value=value;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int v=Integer.parseInt(st.nextToken());
        int e=Integer.parseInt(st.nextToken());
        int[][] arr= new int[v+1][v+1]; // 이동 거리 저장할 배열
        int max=987654321;
        for(int i=1;i<=v;i++){
            Arrays.fill(arr[i], max);
            arr[i][i]=0; // 요거 안해서 10번이상 틀림 ㅎ\
            //설마 근무지에서 거주할줄은 몰랐음
        }
        int[] ppl=new int[n]; // 팀원 배열
        int[] kist = new int[2]; // 건물 2개
        st=new StringTokenizer(br.readLine());
        kist[0]=Integer.parseInt(st.nextToken());
        kist[1]=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ppl[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<e;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            int l=Integer.parseInt(st.nextToken());
            arr[x][y]=l;
            arr[y][x]=l;
        }
        int ans=0;
        for(int i=0;i<2;i++){
            boolean[] check=new boolean[v+1];
            int curr=kist[i];
            check[curr]=true;
            PriorityQueue<Edge> pq=new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    if(o1.value<o2.value) return -1;
                    else if(o1.value>o2.value) return 1;
                    else return 0;
                }
            });
            for(int j=1;j<=v;j++){
            	if(arr[curr][j]==max) continue;
                pq.offer(new Edge(curr, j, arr[curr][j])); // 직접적으로 연결된 길 다 채우기
            }
            while(!pq.isEmpty()){
                Edge edge=pq.poll();
                int to=edge.to;
                int value=edge.value;
                if(check[to]) continue;
                check[to]=true;
                arr[curr][to]=value;
                for(int j=1;j<=v;j++){
                    if(check[j]) continue;
                    if(arr[to][j]==max) continue;
                    pq.offer(new Edge(curr, j, arr[curr][to]+arr[to][j]));
                }
            }
            for(int j=0;j<n;j++){
                if(arr[curr][ppl[j]]==max) ans--;
                else ans+=arr[curr][ppl[j]];	
            }
        }
        System.out.println(ans);
    }
}
