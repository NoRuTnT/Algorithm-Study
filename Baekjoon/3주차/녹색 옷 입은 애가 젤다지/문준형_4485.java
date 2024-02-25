package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b4485 {
	public static class Node implements Comparable<Node>{
		private int x;
		private int y;
		
		private int cost;
		
		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}		
		
		// cost를 기준으로한 우선순위큐지정
		@Override
		public int compareTo(Node other) {
			if(other.cost > this.cost)
				return -1;
			else
				return 1;
		}
	}
	
	static int[][] map,checkmap;
	static int n;
	static int[] dr= {1,0,-1,0};
	static int[] dc= {0,1,0,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int tc=0;
		while(true) {
			tc++;
			n = Integer.parseInt(br.readLine());
			map=new int[n][n];
			checkmap= new int[n][n];
			if(n==0) {
				break;
			}
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				Arrays.fill(checkmap[i], 987654321);		//max값으로 채워진 최소값저장맵 생성		
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());				
				}
			}
			
			
			PriorityQueue<Node>queue = new PriorityQueue<>(); 
			queue.add(new Node(0,0,map[0][0]));
			while(!queue.isEmpty()) {
				Node now = queue.poll();
				int x =now.x;
				int y =now.y;
				int cost =now.cost;
				for(int i=0;i<4;i++) {
					int r = x+dr[i];
					int c = y+dc[i];
					if(r>=0 && r<n && c>=0 && c<n) {
						int cost1 = cost + map[r][c];
						if(checkmap[r][c]>cost1) {  //오기전값+ 도착한곳값 더했을때 저장되있던 도착한곳값보다 작으면 최소값갱신
							checkmap[r][c]=cost1;
							queue.offer(new Node(r,c,cost1)); //도착한곳 우선순위큐에추가
						}
					}
				}
				
			}
			bw.write("Problem "+tc+": "+checkmap[n-1][n-1]+"\n");	
			
		}
		bw.flush();
		br.close();
		bw.close();
		
	}

}
