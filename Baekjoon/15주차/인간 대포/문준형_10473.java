package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b10473 {
    static class node {
        float x;
        float y;

        public node(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class edge implements Comparable<edge> {
        int go;
        float time;

        public edge(int go, float time) {
            this.go = go;
            this.time = time;
        }

        @Override
        public int compareTo(edge o) {
            return Float.compare(this.time, o.time);
        }
    }

    public static node[] nodelist;
    public static ArrayList<edge> adjlist[];
    public static boolean[] check;
    public static float time[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        float x = Float.parseFloat(st.nextToken());
        float y = Float.parseFloat(st.nextToken());
        node start = new node(x, y);
       
        st = new StringTokenizer(br.readLine());
        x = Float.parseFloat(st.nextToken());
        y = Float.parseFloat(st.nextToken());
        node end = new node(x, y);

        int n = Integer.parseInt(br.readLine());
        nodelist = new node[n+2];
        check = new boolean[n+2];
        time = new float[n+2];
        adjlist = new ArrayList[n+2];
        for(int i=0;i<n+2;i++){
            adjlist[i] = new ArrayList<>();
        }

        nodelist[0]=start;
        nodelist[n+1]=end;
        for(int i=1;i<n+1;i++){
            st = new StringTokenizer(br.readLine());
            x = Float.parseFloat(st.nextToken());
            y = Float.parseFloat(st.nextToken());
            nodelist[i] = new node(x, y);
        }
        
        for(int i=1;i<n+2;i++){
            float distance = (float) Math.sqrt(Math.pow(nodelist[0].x-nodelist[i].x,2) + Math.pow(nodelist[0].y-nodelist[i].y,2));
            adjlist[0].add(new edge(i,(float)(distance/5.0)));//0에서는 걸어가기
        }
        
        for(int i=1; i<n+2;i++){
            for(int j=0;j<n+2;j++) {
                float distance = (float) Math.sqrt(Math.pow(nodelist[i].x-nodelist[j].x,2) + Math.pow(nodelist[i].y-nodelist[j].y,2));
                adjlist[i].add(new edge(j,(float) Math.min(distance/5.0, Math.abs(distance-50)/5.0+2))); //나머지는 대포타고가거나 걸어가거나
            }
        }
        dijkstra(0);
        System.out.println(time[n+1]);        
    }

    public static void dijkstra(int start) {
        PriorityQueue<edge> pq = new PriorityQueue<>();
        Arrays.fill(time, Integer.MAX_VALUE);
        pq.add(new edge(start,0));
        time[start]=0;
        while(!pq.isEmpty()) {
            edge edge = pq.poll();
            int go = edge.go; //도착점
            if(check[go]){
                continue;
            }else{
            	check[go] = true;
            }
            for(edge next:adjlist[go]){
                if(time[next.go]>=time[go]+next.time){
                    time[next.go]=time[go]+next.time;
                    pq.add(new edge(next.go,time[next.go]));
                }
            }
        }
    }
}
