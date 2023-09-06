package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class b14502 {
	static int n,m;
	static int[][] map;
	static boolean[][]visit;
	static boolean[][]visit2, twocheck;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map= new int[n][m];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		visit = new boolean[n][m];		
		twocheck=new boolean[n][m];
		dfs(0);
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0) {
					cnt++;
				}

			}
		}
		System.out.println(Arrays.deepToString(map));
		System.out.println(cnt);

	}
	//기둥3개두기
	public static void dfs(int depth) {
		if(depth==3) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==2&&!twocheck[i][j]) {
						map[i][j]=0;
					}
				}				
			}
			visit2 = new boolean[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==2 && !visit2[i][j]) {
						twocheck[i][j]=true;
						visit2[i][j]=true;
						dfs1(i,j);						
					}
				}
			}
			return;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(!visit[i][j] && map[i][j]==0) {
					map[i][j]=1;
					visit[i][j]=true;
					dfs(depth+1);
					visit[i][j]=false;
					map[i][j]=0;
				}
			}
		}
	}
	//바이러스확산
	private static void dfs1(int i,int j) {
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		visit2[i][j]=true;
		Stack<int[]> stack= new Stack<>();
		stack.add(new int[] {i,j});
		while(!stack.isEmpty()) {
			int[] now = stack.pop();
			for(int a=0;a<4;a++) {
				int r = now[0]+dr[a];
				int c = now[1]+dc[a];
				if(r>=0 && r<n && c>=0 && c<n) {
					if(map[r][c]==0 && !visit2[r][c]) {
						map[r][c]=2;
						stack.add(new int[] {r,c});
						visit2[r][c]=true;
					}
				}
		}

	            }
	        }


		}



