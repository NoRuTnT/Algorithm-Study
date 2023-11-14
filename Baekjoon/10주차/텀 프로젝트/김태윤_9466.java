import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_9466 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=t;tc++) {
			int n=Integer.parseInt(br.readLine());
			int[] arr=new int[n+1];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=1;i<=n;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			//cycle이 생기면 그 cycle끼리 팀이 된다.
			boolean[] isVisited = new boolean[n+1];
			int[] order = new int[n+1]; // 카운팅정렬 할 때와 비슷하게 순서 저장하는 배열
			int ans=n; // n명에서 1명씩 빠짐
			int idx=1;
			for(int i=1;i<=n;i++) {
				if(isVisited[i]) continue; // 이미 사이클 여부 판별한 경우 패스
				int curr=i;
				int startIdx=idx;
				for(int j=startIdx; ; j++) { // 조건 만족할 때 까지 계속 order 채우면서 ㄱㄱ
					if(isVisited[curr]) {
						if(order[curr]>=startIdx) ans-=j-order[curr];
						break;
					}
					isVisited[curr]=true;
					order[curr]=j;
					curr=arr[curr]; // 다음으로 이동
					idx++;
				}
			}
			System.out.println(ans);
		}
	}
}
