// 2116. 주사위 쌓기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans;
	public static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][6];
		boolean[][][] visited = new boolean[6][N][6];
		// 1<->6 // 2<->4 // 3<->5
		for(int i=0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<6 ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			// 주사위 눈 4~6에 해당하는 원소를 인덱스에 +3 한 값이 서로 마주보는 면이 되도록
			// 배치
			int tmp  = arr[i][5];
			int tmp2 = arr[i][3];
			arr[i][5] = arr[i][4]; // [6]<-[5]
			arr[i][3] = tmp; // [4]<-[6]
			arr[i][4] = tmp2; // [5]<-[4]
			
		}
		for(int idx=0 ; idx<6 ; idx++) {
			int tmp = 0;
			int bottom = idx;
			int top = (bottom+3)%6;
			visited[idx][0][bottom] = visited[idx][0][top] = true;
			for(int i=0 ; i<6 ; i++) {
				if(!visited[idx][0][i])
					tmp = Math.max(tmp, arr[0][i]);
			}
			int sum = tmp;
			int curVal = arr[0][top];
			for(int i=1 ; i<N ; i++) {
				for(int j=0 ; j<6 ; j++) {
					if(arr[i][j]==curVal) {
						bottom = j;
						break;
					}
				}
				top = (bottom+3)%6;
				visited[idx][i][bottom] = visited[idx][i][top] = true;
				tmp = 0;
				for(int j=0 ; j<6 ; j++) {
					if(!visited[idx][i][j])
						tmp = Math.max(tmp, arr[i][j]);
				}
				sum += tmp;
				curVal = arr[i][top];
			}
			ans = Math.max(ans, sum);
		}
		
		System.out.println(ans);
	}
}
