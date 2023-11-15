import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //치즈클래스 생성
    static class cheese {
        int x;
        int y;
        
        public cheese(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }        
    }
    
    static int[][] map,expose_cnt;
    static int m,n,cnt;
    static boolean[][] visit;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+2][m+2]; //맵사방에 공기로둘러쌓인층 한줄생성
        for(int i=1;i<n+1;i++) {
            st= new StringTokenizer(br.readLine());
            for(int j=1;j<m+1;j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }
        int res_cnt =0;
        while(true) { 
        	init(map); //녹일부분체크
        	melt(expose_cnt); //체크한곳치즈녹이기
        	if(cnt==0) { //녹인치즈가없다면 끝내기
        		break;
        	}
        	res_cnt++;
        	
        }
        System.out.println(res_cnt);
        
    }
    
	private static void init(int[][] map) {
        Queue<cheese>queue = new LinkedList<>();
        visit = new boolean[n+2][m+2]; //방문체크배열
        expose_cnt = new int [n+2][m+2]; //공기노출횟수배열
        for(int i=0;i<n+2;i++) {
            for(int j=0;j<m+2;j++) {
                if(i==0 || i==n+1 || j==0 || j==m+1) { //맵바깥 공기층 큐에추가
                    queue.add(new cheese(i,j));
                    visit[i][j]=true; //방문체크
                }else {
                    visit[i][j]=false;
                }
            }
        }
    
        while(!queue.isEmpty()) { 
            cheese now = queue.poll(); //bfs탐색
            int x = now.x;
            int y = now.y;
            for(int d=0;d<4;d++) { 
            	int r = x+dr[d];
            	int c = y+dc[d];            	
            	if(r>=0 && r<n+2 && c>=0 && c<m+2) {
            		if(!visit[r][c]) {
            			if(map[r][c]==1) { //치즈만나면 공기노출횟수+1
            				expose_cnt[r][c]++;            				
            			}else {
            				visit[r][c]=true; //공기면 방문체크하고 큐에추가
            				queue.offer(new cheese(r,c));
            			}            			
            		}
            	}
            }
        }        
        
    }
	
	private static void melt(int[][] expose_cnt) {
		cnt=0;
		for(int i=0;i<n+2;i++) {
			for(int j=0;j<m+2;j++) {
				if(expose_cnt[i][j]>=2) { //공기노출배열받아서 2이상인부분 치즈없앰
					map[i][j]=0;
					cnt++;
				}
			}
		}
		
	}

}
