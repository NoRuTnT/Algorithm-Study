// 18808. 스티커 붙이기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, K, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<int[][]> list = new ArrayList<>(); // 스티커를 담는 리스트
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for(int i=0 ; i<K ; i++) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int[][] arr2 = new int[R][C];
			for(int j=0 ; j<R ; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0 ; k<C ; k++) {
					arr2[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			list.add(arr2);
		}
		// 스티커 붙이기
		label0:
		for(int idx=0 ; idx<K ; idx++) {
			int[][] tmp = list.get(idx);
			int R = tmp.length;
			int C = tmp[0].length;
			if(N<R && M<C) continue; // 가로세로 모두 범위를 초과하면 애초에 불가능
			label1:
			for(int d=0 ; d<4 ; d++) {
				if(R>N || C>M) { // 가로 또는 세로범위 초과
					tmp = rotate90(tmp);
					R = tmp.length;
					C = tmp[0].length;
					continue;
				}
				
				for(int i=0 ; i<=N-R ; i++) {
					label2:
					for(int j=0 ; j<=M-C ; j++) {
						boolean flag = true;
						for(int r=0 ; r<R ; r++) {
							for(int c=0; c<C ; c++) {
								if(arr[i+r][j+c]==1 && tmp[r][c]==1) {
									flag = false;
									continue label2;
								}
									
							}
						}
						if(flag) {
							for(int r=0 ; r<R ; r++) {
								for(int c=0 ; c<C ; c++) {
									if(tmp[r][c]==1)
									arr[i+r][j+c] = tmp[r][c];
								}
							}
							continue label0;
						}
					}
				}
				
				tmp = rotate90(tmp);
				R = tmp.length;
				C = tmp[0].length;
			}
		}
		// ans 계산
		for(int i=0 ; i<N ; i++) {
			for(int j=0 ; j<M ; j++) {
				ans += arr[i][j];
			}
		}
		System.out.println(ans);
	}
	public static int[][] rotate90(int[][] arr){
		int R = arr.length;
		int C = arr[0].length;
		int[][] arr2 = new int[C][R];
		for(int i=0 ; i<R ; i++) {
			for(int j=0 ; j<C ; j++) {
				arr2[j][R-1-i] = arr[i][j];
			}
		}
		return arr2;
	}
}
