package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b16236 {
	static int n,total,stack;
	static int[][] map;
	static boolean smallfish;
	static boolean[][] eatmap,visit;
	static ArrayList<int[]> save;
	static int[] shark;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		map = new int [n][n];
		eatmap = new boolean [n][n];
		visit = new boolean [n][n];
		shark=new int[3];
		save = new ArrayList<>();
		total=0;
		stack=0;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int a =Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if(a==9) {
					shark[0] = i;
					shark[1] = j;
					shark[2] = 2;
				}
			}
		}
		smallfish=true;
		while(smallfish) {
			eat(shark[0],shark[1],shark[2]);	
			System.out.println(Arrays.deepToString(map));
			
		}
		System.out.println(total);
		
		
	}
	//지도상에서 먹을수있는물고기 탐색 
	private static void eat(int i, int j, int k) {
		smallfish = false;		
		for(int a=0;a<n;a++) {
			for(int b=0;b<n;b++) {
				if(map[a][b]!=0 && map[a][b]<k) {
					eatmap[a][b]=true;
					smallfish = true;
				}
			}
		}
		if(!smallfish) {
			return;
		}
		bfs(i,j,k);
	}
	//bfs로 우선순위물고기 탐색
	private static void bfs(int i, int j, int k) {		
		Queue<int[]>queue = new LinkedList<>();
		queue.offer(new int[] {i,j,k});
		
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int a=0;a<len;a++) {
				int[]now = queue.poll();
				int x = now[0];
				int y = now[1];
				int size = now[2];
				for(int d=0;d<4;d++) {
					int r =x+dr[d];
					int c =y+dc[d];
					if(r>=0 && r<n && c>=0 && c<n) {
						if(!visit[r][c] && !(size>k) ) {
							visit[r][c]=true;
							queue.offer(new int[] {r,c,k});
							if(map[r][c]!=0 && eatmap[r][c]) {
								save.add(new int[] {r,c});
							}
						}
					}					
				}					
			}
			move();				
		}
	}
	//이동
	private static void move() {
		if(!save.isEmpty()) {
			if(save.size()==1) {
				total += distance(shark[0],save.get(0)[0],shark[1],save.get(0)[1]);
				map[shark[0]][shark[1]]=0;
				shark[0]=save.get(0)[0];
				shark[1]=save.get(0)[1];
				sizeup();
				map[shark[0]][shark[1]]=9;
				
			}else {
				int[] target=new int [2];
				int min=987654321;
				for(int b=0;b<save.size();b++) {
					int up = save.get(b)[0];
					
					if(up<min) {							
						min = up;
						target[0]=save.get(b)[0];
						target[1]=save.get(b)[1];							
					}else if(up==min) {
						if(target[1]>save.get(b)[1]) {
							target[0]=save.get(b)[0];
							target[1]=save.get(b)[1];
						}
					}
				}
				total += distance(shark[0],target[0],shark[1],target[1]);
				map[shark[0]][shark[1]]=0;
				shark[0]=target[0];
				shark[1]=target[1];
				sizeup();
				map[shark[0]][shark[1]]=9;
				
			}
			
		}
		
	}
	//물고기 사이즈업
	private static void sizeup() {
		stack++;
		if(stack>=shark[2]) {
			stack=0;
			shark[2]++;
		}		
	}
	//거리구하기
	private static int distance(int x1, int x2, int y1, int y2) {
		return Math.abs(x2-x1)+Math.abs(y2-y1);
	}
	
	

}
