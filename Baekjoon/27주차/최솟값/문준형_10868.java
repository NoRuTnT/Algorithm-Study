import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int min,max;
	static int[] arr, minTree, maxTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//전체숫자배열
		arr = new int[n+1];
		for(int i=1;i<=n;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		//트리의 높이구하기
		int h = (int) (Math.ceil(Math.log(n)/Math.log(2))+1);
		//만들트리의 전체노드수 구하기
		int size = (int) Math.pow(2, h);
		minTree = new int[size+1];
//		maxTree = new int[size+1];
		//트리제작
		maketree(0,minTree,1,n,1);//(최소트리(0) or 최대트리(1), 트리입력, 시작값, 끝값, 현재노드)
//		maketree(1,maxTree,1,n,1);
		
		//만들어진 트리로 입력값구간 적용
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			min = 1000000001;
			max = -1;
			scantree(0,minTree,1,n,1,a,b);
//			scantree(1,maxTree,1,n,1,a,b);
			bw.write(min+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
		

	private static void maketree(int type, int[] tree, int start, int end, int node) {
		if(start == end) {
			tree[node]=arr[start];
			return;
		}
		
		int mid = (start+end)/2;
		maketree(type,tree,start,mid,node*2); //왼쪽자식노드채우기
		maketree(type,tree,mid+1,end,node*2+1); //오른쪽자식노드채우기
		
		if(type==0) {
			if(tree[node*2] < tree[node*2+1]) { //자식노드비교해서 최소트리는 루트노드에 작은거채워넣기
				tree[node]=tree[node*2];
			}else{
				tree[node]=tree[node*2+1];
			}
		}else {
			if(tree[node*2] < tree[node*2+1]) { //최대트리는 루트노드에 큰거채워넣기
				tree[node]=tree[node*2+1];
			}else{
				tree[node]=tree[node*2];
			}
		}
	}
	private static void scantree(int type, int[] tree, int start, int end, int node, int left, int right) {
		if(left>end || right<start) { //구하려는 범위가 start~end 범위 밖일때
			return;
		}
		if(left<=start && end<=right) { //구하려는 범위가 start~end를 완전히 포함하고있을때
			if(type==0) {
				min = Math.min(min, tree[node]);
			}else {
				max = Math.max(max, tree[node]);
			}
			return;
		}
		int mid = (start+end)/2;
		scantree(type,tree,start,mid,node*2,left,right);   // 왼쪽자식노드탐색
		scantree(type,tree,mid+1,end,node*2+1,left,right); // 오른쪽자식노드탐색			
	}
	
}
