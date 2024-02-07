package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_1766 {
    static int n, m;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static ArrayList<Integer>[] nextNode;
    static int[] inDegree;
    static StringBuilder ans=new StringBuilder();
    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(ans.toString());
    }
    public static void init() throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        nextNode = new ArrayList[n+1];
        inDegree = new int[n+1];

        for(int i=1;i<=n;i++){
            nextNode[i]=new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            nextNode[prev].add(next);
            inDegree[next]++;
        }

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0) pq.offer(i);
        }
    }

    public static void solve(){
        while(!pq.isEmpty()){
            int curr = pq.poll();
            ans.append(curr+" ");
            for(int i=0;i<nextNode[curr].size();i++){
                int next = nextNode[curr].get(i);
                inDegree[next]--;
                if(inDegree[next]==0) pq.offer(next);
            }
        }
    }
}
