import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static class node implements Comparable<node>{
        int from;
        int to;
        int weight;

        public node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }
    static int v,e;
    static int[][] map;
    static int[] parent;


    private static int find(int x){
        if(parent[x]==x){
            return x;
        }else{
            return find(parent[x]);
        }
    }
    private static boolean union(int x,int y) {
        x = find(x);
        y = find(y);

        if(x==y) {
            return false;
        }
        if(x<y) {
            parent[y] =x;
        }else {
            parent[x] =y;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ArrayList<node> list = new ArrayList<>();
        for(int i=0;i<e;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.add(new node(from,to,weight));
        }
        list.sort(Comparator.naturalOrder());
        parent = new int[v+1];
        for(int i=1;i<=v;i++){
            parent[i] =i;
        }
        int total = 0;
        for(node n:list){
            if(union(n.from,n.to)){
                total += n.weight;
            }
        }
        System.out.println(total);

    }
}
