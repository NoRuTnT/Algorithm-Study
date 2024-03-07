import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int start;
        int end;
        Pair(int start, int end){
            this.start = (start<=end)?start:end;
            this.end = (start<=end)?end:start;
        }
        @Override
        public int compareTo(Pair o) {
            if(this.end == o.end){
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }
    static int n, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        Pair[] pairList = new Pair[n];
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st=new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            pairList[i]=new Pair(num1, num2);
        }
        Arrays.sort(pairList);
        d = Integer.parseInt(br.readLine());
        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Pair pair : pairList){
            while(!pq.isEmpty() && pq.peek() < pair.end - d){
                pq.poll();
            }
            if(pair.start >= pair.end - d){
                pq.offer(pair.start);
            }
            ans = Math.max(ans, pq.size());
        }
        System.out.println(ans);
    }
}
