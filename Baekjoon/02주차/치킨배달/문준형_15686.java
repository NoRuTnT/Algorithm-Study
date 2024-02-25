package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b15686 {
	static int[][]map;
	static ArrayList<int[]> home,chicken;
	static int n,m,ans;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());		
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j]=a;
				if(a==1) {
					home.add(new int[]{i,j});
				}else if(a==2) {
					chicken.add(new int[]{i,j});
				}				
			}
		}
		ans=987654321;
		visited = new boolean[chicken.size()];
		dfs(0,0);
		System.out.println(ans);
	}
	
	public static void dfs(int depth,int start) {
		 if (depth == m) {
	            int res = 0;
	            for(int i=0;i<home.size();i++) {
	                int min=987654321;
	                for(int j=0;j<chicken.size();j++) {
	                    if(visited[j]){
	                        int distance= getDistance(home.get(i)[0],chicken.get(j)[0],home.get(i)[1],chicken.get(j)[1]);
	                        min = Math.min(min, distance);
	                    }
	                }
	                res+=min;
	            }
	            ans=Math.min(ans,res);
	            return;
	        }
		for (int i=start;i<chicken.size();i++) {
            visited[i] = true;
            dfs(depth+1,i+1);
            visited[i] = false;
        }
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}


