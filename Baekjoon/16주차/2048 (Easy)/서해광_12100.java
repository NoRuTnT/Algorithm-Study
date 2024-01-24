// 12100. 2048 (Easy)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans;
	public static int[][] board;
	public static int[] dr = {0, 1, 0, -1}; // 3시부터 시계방향
	public static int[] dc = {1, 0, -1, 0}; 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		while(true) {
//			int n = Integer.parseInt(br.readLine());
//			board = move(n, board);
//			for(int i=0 ; i<N ; i++) {
//				for(int j=0 ; j<N ; j++) {
//					System.out.print(board[i][j]+" ");
//				}
//				System.out.println();
//			}
//		}
		dfs(0, board);
		System.out.println(ans);
	} // main
	public static void dfs(int depth, int[][] arr) {
		if(depth==5) {
			for(int i=0 ; i<N ; i++) {
				for(int j=0 ; j<N ; j++) {
					ans = Math.max(ans, arr[i][j]);
				}
			}
			return;
		}
		for(int d=0 ; d<4 ; d++) {
			int[][] arr2 = arrCpy(arr);
			arr2 = move(d, arr2);
			dfs(depth+1, arr2);
		}
	}
//	0, 1 인덱스 증가하는 방향
//	2, 3 인덱스 감소하는 방향
//	0, 2 row안에서 계산
//	1, 3 col안에서 계산
	public static int[][] move(int d, int[][] arr){
		for(int i=0 ; i<N ; i++) {
			int[] replace = new int[N];
			int idx1 = 0;
			int idx2 = 0;
			int currVal = 0;
			if(d==0) {
				idx1 = 0;
				idx2 = 0;
				replace[idx2] = currVal;
				while(true) {
					if(idx1>=N) break;
					if(arr[i][idx1]!=0) {
						replace[idx2] = arr[i][idx1++];
						currVal = replace[idx2];
						break;
					}
					idx1++;
				}
				while(true) {
					if(idx1>=N) break;
					if(arr[i][idx1]==0) {
						
					}
					else if(arr[i][idx1]==currVal) {
						replace[idx2++]*=2;
						currVal = 0;
					}else { // 그 외의 블록을 만남
						if(currVal==0) {
							currVal = arr[i][idx1];
							replace[idx2]= arr[i][idx1];
						}else {
							replace[++idx2]=arr[i][idx1]; // idx2를 미리 옮겨줌(중요)						
							currVal = arr[i][idx1]; // 11반례 보정 시도
						}
					}
					idx1++;
				}
				for(int j=idx1 ; j<N ; j++) {
					replace[idx2++] = 0;
				}
				for(int j=0 ; j<N ; j++) {
					arr[i][j] = replace[j];
				}
			}
			if(d==1) {
				idx1 = 0;
				idx2 = 0;
				replace[idx2] = currVal;
				while(true) {
					if(idx1>=N) break;
					if(arr[idx1][i]!=0) {
						replace[idx2] = arr[idx1++][i];
						currVal = replace[idx2];
						break;
					}
					idx1++;
				}
				while(true) {
					if(idx1>=N) break;
					if(arr[idx1][i]==0) {}
					else if(arr[idx1][i]==currVal) {
						replace[idx2++]*=2;
						currVal = 0;
					}else {
						if(currVal==0) {
							currVal = arr[idx1][i];
							replace[idx2]= arr[idx1][i];
						}else {
							replace[++idx2]=arr[idx1][i];
							currVal = arr[idx1][i];
						}
					}
					idx1++;
				}
				for(int j=idx1 ; j<N ; j++) {
					replace[idx2++] = 0;
				}
				for(int j=0 ; j<N ; j++) {
					arr[j][i] = replace[j];
				}
			}
			if(d==2) {
				idx1 = N-1;
				idx2 = N-1;
				replace[idx2] = currVal;
				while(true) {
					if(idx1<0) break;
					if(arr[i][idx1]!=0) {
						replace[idx2] = arr[i][idx1--];
						currVal = replace[idx2];
						break;
					}
					idx1--;
				}
				while(true) {
					if(idx1<0) break;
					if(arr[i][idx1]==0) {}
					else if(arr[i][idx1]==currVal) {
						replace[idx2--]*=2;
						currVal = 0;
					}else {
						if(currVal==0) {
							currVal = arr[i][idx1];
							replace[idx2]= arr[i][idx1];
						}else {
							replace[--idx2]=arr[i][idx1];				
							currVal = arr[i][idx1];
						}
					}
					idx1--;
				}
				for(int j=idx1 ; j>=0 ; j--) {
					replace[idx2--] = 0;
				}
				for(int j=0 ; j<N ; j++) {
					arr[i][j] = replace[j];
				}
			}
			if(d==3) {
				idx1 = N-1;
				idx2 = N-1;
				replace[idx2] = currVal;
				while(true) {
					if(idx1<0) break;
					if(arr[idx1][i]!=0) {
						replace[idx2] = arr[idx1--][i];
						currVal = replace[idx2];
						break;
					}
					idx1--;
				}
				while(true) {
					if(idx1<0) break;
					if(arr[idx1][i]==0) {}
					else if(arr[idx1][i]==currVal) {
						replace[idx2--]*=2;
						currVal = 0;
					}else {
						if(currVal==0) {
							currVal = arr[idx1][i];
							replace[idx2]= arr[idx1][i];
						}else {
							replace[--idx2]=arr[idx1][i];				
							currVal = arr[idx1][i];
						}
					}
					idx1--;
				}
				for(int j=idx1 ; j>=0 ; j--) {
					replace[idx2--] = 0;
				}
				for(int j=0 ; j<N ; j++) {
					arr[j][i] = replace[j];
				}
			}
		}
		return arr;
	}
	public static int[][] arrCpy(int[][] arr1){
		int[][] arr2 = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<N ; j++) {
				arr2[i][j] = arr1[i][j];
			}
		}
		return arr2;
	}
}
