import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
class virus{
	int r;
	int c;
	virus(){}
	virus(int r, int c){
		this.r=r;
		this.c=c;
	}
}
public class Baekjoon14502연구소 {
	static int n;
	static int m;
	static int[][] map;
	static int safe=0; // 원래 안전한 자리의 개수
	static int max=-1; // 정답으로 사용할 변수
	static ArrayList<virus> start=new ArrayList<>(); // 초기 바이러스 위치
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) safe++;
				else if(map[i][j]==2) start.add(new virus(i,j));
			}
		}
		//1) 벽 세개의 위치 정하기 (dfs 활용)
		//2) 바이러스 퍼뜨리고 안전 영역 구하기 (bfs or dfs 활용)
		wall(0, 0, 0); // r, c, depth
		System.out.println(max);
	}
	public static void wall(int r, int c, int depth) {
		if(depth==3) { // 벽 3개 세움
			int res=virus();
			if(max<res) {
				max=res; // virus()를 통해 벽 세웠을 때 안전 지역 개수 구하고, min 값 갱신
			}
			return;
		}
		if(r==n) return; // 행 범위 벗어난 경우 탐색 종료
		if(c==m) {
			wall(r+1, 0, depth); // 열 방향 범위 벗어난 경우 다음 행으로
			return;
		}
		if(map[r][c]==0) {
			map[r][c]=1; // 빈 공간이었으면 벽을 세운다
			safe--;
			wall(r, c+1, depth+1); // 벽 세우고 다음 탐색
			map[r][c]=0;
			safe++;
		}
		wall(r,c+1, depth); // 벽 안 세우고 그냥 탐색
	}
	public static int virus() { //bfs로 찾을 것
		int left=safe; // 남은 안전한 곳
		int[] dr= {-1,0,1,0};
		int[] dc= {0,1,0,-1}; // 상우하좌
		LinkedList<virus>queue=new LinkedList<>();
		boolean[][] visited=new boolean[n][m]; // 방문한 곳 안 겹치게
		for(int i=0;i<start.size();i++) {
			queue.add(start.get(i));
			visited[start.get(i).r][start.get(i).c]=true;
		}
		while(!queue.isEmpty()) { // 바이러스 완전히 다 퍼질때까지
			virus now=queue.poll();
			for(int i=0;i<4;i++) {
				virus next=new virus(now.r+dr[i], now.c+dc[i]); // 다음으로 탐색할 곳 위치
				if(next.r<0 || next.r>=n || next.c<0 || next.c>=m) continue; // 경계조건
				if(map[next.r][next.c]==1 || visited[next.r][next.c]) continue; // 벽 있거나 이미 전파됐으면 continue
				// 조건문 통과하면 map에서 0이고 방문한적 없는 곳만 남음
				visited[next.r][next.c]=true;
				queue.add(next);
				left--; //남은 안전한 장소 개수
			}
		}
		return left;
	}
}
