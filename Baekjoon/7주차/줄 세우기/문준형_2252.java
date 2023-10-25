package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;



	public class b2252 {
	    static int n, m;
	    static int[] degree;
	    static ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
	    static Queue<Integer> queue;

	    public static void main(String [] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	        StringTokenizer st = new StringTokenizer(br.readLine(), " ", false);

	        n = Integer.parseInt(st.nextToken()); //정점 학생수
	        m = Integer.parseInt(st.nextToken()); //간선 비교수
	        degree = new int[n+1]; 
	        queue = new LinkedList<>();

	        for (int i=0; i<=n; i++) {
	            edges.add(new ArrayList<Integer>()); //간선정보저장리스트
	        }

	        for (int i=0;i<m;i++) {
	            st = new StringTokenizer(br.readLine(), " ", false);
	            int start = Integer.parseInt(st.nextToken());
	            int end = Integer.parseInt(st.nextToken());
	            degree[end]++; //진입차수
	            edges.get(start).add(end); //간선정보저장
	        }

	        for (int i=1;i<=n;i++)
	            if (degree[i] == 0) queue.add(i); //진입차수가 0이라면 큐에추가

	        while(!queue.isEmpty()) {
	            int num = queue.poll();

	            bw.write(num+" ");

	            for (int i = 0; i < edges.get(num).size(); i++) {
	                int curr = edges.get(num).get(i); //목적지정점
	                degree[curr]--; //간선체크했으니까 -1
	                if(degree[curr] == 0) queue.add(curr); //차수0이면추가
	            }
	        }

	        bw.close();
	    }
	}
