import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_2638 {
	static class Pos{
		int r;
		int c;
		Pos(){}
		Pos(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	static int n;
	static int m;
	static boolean[][] arr;
	static int[] dr= {1,-1,0,0};
	static int[] dc= {0,0,1,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		arr=new boolean[n][m];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				int input=Integer.parseInt(st.nextToken());
				if(input==1) arr[i][j]=true;
			}
		}
		int ans=0;
		while(true) {
			Queue<Pos> air=new LinkedList<>();
			//테두리 찾기 => (0,0)과 연결된거 전부 queue에 담음
			findOutside(air);
			//air의 크기가 n*m과 동일하면 다 녹은 상태 => break
			if(air.size()==n*m) break;
			//녹이기 => queue에 담은 부분 인접한 곳 중 true인 거 false로 바꿈
			melt(air);
			//1턴 증가
			ans++;
		}
		System.out.println(ans);
	}
	public static void findOutside(Queue<Pos> air) {
		//전체 arr 순회하며 false인 곳 queue에 집어넣기
		Queue<Pos> bfs=new LinkedList<>();
		boolean[][] isVisited=new boolean[n][m];
		bfs.offer(new Pos(0,0));
		isVisited[0][0]=true;
		while(!bfs.isEmpty()) {
			Pos curr=bfs.poll();
			air.offer(curr); // bfs에 있던건 전부 false였던거니까 다 넣는다
			for(int i=0;i<4;i++) {
				Pos next=new Pos(curr.r+dr[i], curr.c+dc[i]);
				if(next.r<0 || next.r>=n || next.c<0 || next.c>=m) continue;
				if(isVisited[next.r][next.c]) continue;
				if(!arr[next.r][next.c]) {
					bfs.offer(next);
					isVisited[next.r][next.c]=true;
				}
			}
		}
	}
	public static void melt(Queue<Pos> air) {
		int[][] nearAir=new int[n][m]; //  주변 공기부분 개수
		while(!air.isEmpty()) {
			Pos curr=air.poll();
			for(int i=0;i<4;i++) {
				Pos next=new Pos(curr.r+dr[i], curr.c+dc[i]);
				if(next.r<0 || next.r>=n || next.c<0 || next.c>=m) continue;
				if(arr[next.r][next.c]) {
					nearAir[next.r][next.c]++;
					if(nearAir[next.r][next.c]==2) arr[next.r][next.c]=false;
				}
			}
		}
	}
}
