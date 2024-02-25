import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_1976 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		int[] tree=new int[n+1];
		int[][] adjArr=new int[n+1][n+1];
		int[] tripPlan=new int[m];
		StringTokenizer st;
		for(int i=1;i<=n;i++) {
			if(tree[i]==0) tree[i]=i; // maketree
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				adjArr[i][j]=Integer.parseInt(st.nextToken());
				if(adjArr[i][j]==0) continue;
				if(tree[i]<tree[j] || tree[j]==0) tree[j]=find(tree[i], tree);
				else tree[find(i, tree)]=find(tree[j], tree);
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			tripPlan[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0;i<m-1;i++) {
			if(find(tripPlan[i], tree)!=find(tripPlan[i+1], tree)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	public static int find(int i, int[] tree) {
		if(i!=tree[i]) {
			tree[i]=find(tree[i], tree);
		}
		return tree[i];
	}
}

