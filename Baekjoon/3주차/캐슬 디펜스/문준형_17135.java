package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b17135 {
	
	static int n,m,range,end_count;
	static int[] check_end;
	static int[][] map,testmap;
	static boolean[][] killmap,visit;
	static boolean[] tf = new boolean[5];
	static int[] dr = {0,1,0,-1}; //우하좌상
	static int[] dc = {1,0,-1,0};
	static ArrayList<boolean[]>list;
	static ArrayList<int[]>realkill;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		range= Integer.parseInt(st.nextToken());
		map = new int[n][m];
		testmap = new int[n][m];
		check_end = new int[m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int res=0;
		list = new ArrayList<>();
		Combination(0,0);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			for (int j=0; j<testmap.length; j++){
	            testmap[j]=Arrays.copyOf(map[j],map[j].length);
	        }
			System.out.println(Arrays.deepToString(testmap));
			int totalkill=0;
			while(true) {				
				int killcount=0;
				killmap = new boolean[n][m];
				realkill = new ArrayList<int[]>();
				System.out.println(Arrays.toString(list.get(i)));
				shoot(list.get(i),range);
				for(int j=0;j<realkill.size();j++) {
//				System.out.println(Arrays.toString(realkill.get(j)));
					int x=realkill.get(j)[0];
					int y=realkill.get(j)[1];
					System.out.print(x+" ");
					System.out.println(y);
					if(testmap[x][y]==1) {
						killcount++;
						testmap[x][y]=0; //카운트올라가면 적을없애서 같은곳을 여러명이때리는경우 카운트안되게처리
					}
				}
				totalkill+=killcount;
				System.out.println(totalkill);
				changemap();
				if(end_count==0) {
					break;
				}				
			}
			res=Math.max(totalkill, res);
		}
		System.out.println(res);
		
	}	

	public static void Combination(int index,int sidx) {
		if(sidx==3) {
			list.add(Arrays.copyOf(tf, tf.length));
			return;
		}
		if(index==5) {
			return;
		}
		tf[index] = true;
		Combination(index+1,sidx+1);
		tf[index] = false;
		Combination(index+1,sidx);
		
	}

	public static void shoot(boolean[]list, int range) {		
		for(int i=0;i<5;i++) {
			killmap = new boolean[n][m];
			visit = new boolean[n][m];
			if(list[i]==true) {
				dfs(n-1,i,range);				
			}
			//killmap탐색해서 죽일적1마리결정
			
			loop:for(int a=0;a<n;a++) {
				for(int b=0;b<m;b++) {
					if(killmap[n-1-a][b]==true) {
						realkill.add(new int[] {n-1-a,b});
						break loop;
					}
				}
			}
		}		
	}
	
	
	public static void dfs(int x,int y,int range) {
		if(testmap[x][y]==1) {
			killmap[x][y]=true;					
		}
		if(range==1) {			
			return;
		}		
		for(int i=0;i<4;i++) {
			int r = x+dr[i];
			int c = y+dc[i];
			if(r>=0 && r<n && c>=0 && c<m && !visit[x][y]) {
				visit[x][y]=true;
				dfs(r,c,range-1);
				visit[x][y]=false;
			}
		}		
	}
	
	public static void changemap() {
		end_count=0;
		for(int i=n-2;i>=0;i--) {
			testmap[i+1]=testmap[i];
			if(testmap[i]!=check_end) {
				end_count++;
			}
		}
		testmap[0]=check_end;
	}
}
