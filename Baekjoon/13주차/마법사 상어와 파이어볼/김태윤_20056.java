import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 김태윤_20056 {
	static class Fireball{
		int r;
		int c;
		int mass;
		int speed;
		int dir;
		
		Fireball(){}
		Fireball(int r, int c, int mass, int speed, int dir){
			this.r=r;
			this.c=c;
			this.mass=mass;
			this.speed=speed;
			this.dir=dir;
		}
	}
	static int n;
	static int m;
	static int t;
	static Queue<Fireball> queue = new LinkedList<>();
	static int[][] map;
	static int[][] speed;
	static int[][] mass;
	static int[][] dir;
	static boolean[][] dirCheck;
	
	static int[] dr= {-1,-1,0,1,1,1,0,-1};
	static int[] dc= {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			
			queue.offer(new Fireball(r,c,m,s,d));
			
		}
		
		for(int tc=1;tc<=t;tc++) {
			// 1. queue에 저장한 파이어볼 이동시켜 배열로
			move();
			// 2. 배열에 나타낸 파이어볼 다시 queue에 담기
			fusion();
		}
		System.out.println(sol());
	}
	public static void move() {
		map=new int[n][n];
		speed=new int[n][n];
		mass=new int[n][n];
		dir=new int[n][n];
		dirCheck=new boolean[n][n];
		
		while(!queue.isEmpty()) {
			Fireball ball=queue.poll();
			int nextR=ball.r+(ball.speed%n)*dr[ball.dir];
			int nextC=ball.c+(ball.speed%n)*dc[ball.dir];
			nextR=(nextR+n)%n;
			nextC=(nextC+n)%n;
			
			map[nextR][nextC]++;
			speed[nextR][nextC]+=ball.speed;
			mass[nextR][nextC]+=ball.mass;
			if(map[nextR][nextC]==1) { //겹치는 파이어볼이 없는 경우
				dir[nextR][nextC]=ball.dir;
			}
			else { // 파이어볼이 겹치는 경우 방향 결정
				if(dir[nextR][nextC]%2!=ball.dir%2) {
					dirCheck[nextR][nextC]=true; // 1 3 5 7
				}
			}
		}
	}
	public static void fusion() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]>1 && mass[i][j]>=5) { // 여러개 겹칠 경우 소멸 안 되는 경우만
					for(int k=0;k<4;k++) {
						if(dirCheck[i][j]) {
							queue.offer(new Fireball(i,j,mass[i][j]/5,speed[i][j]/map[i][j],k*2+1));
						}
						else {
							queue.offer(new Fireball(i,j,mass[i][j]/5,speed[i][j]/map[i][j],k*2));
						}
					}
				}
				else if(map[i][j]==1) { // 1개인 경우 그냥 그대로 담음
					queue.offer(new Fireball(i,j,mass[i][j], speed[i][j], dir[i][j]));
				}
			}
		}
	}
	
	
	public static int sol() {
		int ans=0;
		while(!queue.isEmpty()) {
			ans+=queue.poll().mass;
		}
		return ans;
	}
}
