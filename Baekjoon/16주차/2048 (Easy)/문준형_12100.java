package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b12100 {
	private static int n,max;
    private static int[][] map, tmpmap;
    private static int[] stack;
    private static boolean[][] visit;    
    private static int[] dr = {-1,0,1,0}; //상우하좌
    private static int[] dc = {0,1,0,-1};
    
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st;
		 n = Integer.parseInt(br.readLine());
         map = new int[n+1][n+1];
         for(int i=1;1<=n;i++) {
        	 st = new StringTokenizer(br.readLine());
        	 for(int j=1; j<=n;j++) {
        		 map[i][j] = Integer.parseInt(st.nextToken());
        	 }
         }
         stack = new int[5];
         makestack(0);
         System.out.println(max);
		
	}

	private static void makestack(int depth) {
		if(depth==5) {
			start();
		}else {
			for(int i=0;i<4;i++) {
				stack[depth]=i;
				makestack(depth+1);
				
			}
			
		}
		
	}

	private static void start() {
		tmpmap = new int[n+1][n+1];
		for(int i=1; i<=n;i++) {
			tmpmap[i]=map[i].clone();
		}
		
		for(int d=0;d<4;d++) {
			visit = new boolean[n+1][n+1];
			
			if(stack[d]==0) { //상
                for(int i=1;i<=n;i++) {
                    for(int j=1;j<=n;j++) {
                        move(i,j,stack[d]);
                    }
                }
            }else if(stack[d]==2) {  //하
                for(int i=n;i>=1;i--) {
                    for(int j=1;j<=n;j++) {
                        move(i,j,stack[d]);
                    }
                }
            }else if(stack[d]==1) { //우
                for(int i=n;i>=1;i--) {
                    for(int j=1;j<=n;j++) {                        
                        move(j,i,stack[d]); //오른쪽부터
                    }
                }
            }else {   //좌
                for(int i=1;i<=n;i++) {
                    for(int j=1;j<=n;j++) {                       
                        move(j,i,stack[d]); //왼쪽부터
                   }
               }
            }
		}
		for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++) {
                if(tmpmap[i][j]>max) {
                    max = tmpmap[i][j]; //최댓값찾기
                }
            }
        }
		
		
		
		
	}

	private static void move(int x, int y, int dir) {
		if(tmpmap[x][y]==0) {
			return;
		}
		while(true) {
			int r = x+dr[dir];
			int c = x+dc[dir];
			//백트래킹
			if(r<1 || r>n || c<1 || c>n) {
				return;
			}
			if(visit[r][c]) {
				return;
			}
			if(tmpmap[r][c]==tmpmap[x][y]) {
				visit[r][c]=true;
				tmpmap[r][c]*=2; 
				tmpmap[x][y]=0; //같으면 합치고 전칸은 0
				return;
			}else if(tmpmap[r][c]!=0) {
				return; //다른데 빈칸아니면 끝
			}
			tmpmap[r][c]=tmpmap[x][y];
            tmpmap[x][y]=0; //이동
            x = r;
            y = c;
			
		}
		
		
		
		
	}
	
	

}
