import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class pipe{
	int r;
	int c;
	int d; //방향
	pipe(){}
	pipe(int r, int c, int d){
		this.r=r;
		this.c=c;
		this.d=d;
	}
}

public class 김태윤_17070 {
	static int n;
	static int[][] map;
	static boolean[][] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		check=new boolean[n][n];
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		if(map[n-1][n-1]==1) {
			System.out.println(0);
			return;
		}
		//입력 완료
		
		LinkedList<pipe> queue=new LinkedList<>();
		queue.add(new pipe(0,1,0));
		int[] dr= {0,1,1};
		int[] dc= {1,1,0}; // 0 우 / 1 우하 / 2 하
		int cnt=0;
		while(!queue.isEmpty()) {
			pipe now=queue.poll();
			for(int k=0;k<3;k++) {
				if(now.r+dr[k]<0 || now.r+dr[k]>=n || now.c+dc[k]<0 || now.c+dc[k]>=n ) continue;
				if(Math.abs(now.d-k)>1) continue; // 방향 전환 못하는 방향으론 탐색 x
				if(map[now.r+dr[k]][now.c+dc[k]]==1) continue; // 가는 방향에 벽있으면 ㄴㄴ
				if(k==1 && (map[now.r+1][now.c]==1 || map[now.r][now.c+1]==1)) continue; // 대각은 조건 더 따져야
				if(now.r+dr[k]==n-1 && now.c+dc[k]==n-1) { // 이번 파이프 두는거로 끝점 도달 가능이면 cnt늘리고 queue엔 안 넣음
					cnt++;
					continue; 
				}
				queue.add(new pipe(now.r+dr[k], now.c+dc[k], k)); // 이 조건 다 통과했으면 queue에 넣고 다음을 본다 
			}
		}
		System.out.println(cnt);
	}
}
