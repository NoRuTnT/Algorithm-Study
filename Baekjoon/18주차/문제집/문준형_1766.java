package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class b1766 {
	static int n, m;
	static boolean[] check;
    static int[] degree;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static Queue<Integer> save;
    static PriorityQueue<Integer> pq;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        degree = new int[n+1];
        check = new boolean[n+1];
        pq = new PriorityQueue<Integer>();
        save = new LinkedList<>();
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<Integer>());
        }
        
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            degree[end]++;
            list.get(start).add(end);
        }
        
        for(int i=1;i<=n;i++)
            if(degree[i]==0) {
            	pq.add(i);
            }
        
        while(!pq.isEmpty()) {
        	int now = pq.poll();
        	check[now]=true;
        	save.offer(now);
        	for(int i=0;i<list.get(now).size();i++) {
        		int num = list.get(now).get(i);
        		degree[num]--;
        		if(degree[num]==0) {
        			pq.add(num);
        		}        		
        	}
        }
        for(int i=1;i<=n;i++) {
        	if(!check[i]) {
        		bw.write(i+" ");
        	}else {
        		while(!save.isEmpty()) {
        			bw.write(save.poll()+" ");
        			i++;        			
        		}
        	}        	
        }
        bw.flush();
        bw.close();
        br.close();
        
	}

}
