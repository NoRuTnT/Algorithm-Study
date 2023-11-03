import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_23309 {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] station = new int[1000001][2]; // 배열을 doubly linkedlist처럼 사용
		//0번 방엔 이전 index, 1번 방엔 이후 index
		st=new StringTokenizer(br.readLine());
		int first=Integer.parseInt(st.nextToken());
		int before=first, now=-1;
		for(int i=1;i<n;i++) {
			now=Integer.parseInt(st.nextToken());
			station[now][0]=before;
			if(before>0) station[before][1]=now;
			before=now;
		}
		station[before][1]=first; // 오른쪽 끝에 왼쪽 끝 이어주고
		station[first][0]=before; // 왼쪽 끝에 오른쪽 끝 이어준다
	
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<m;i++) {
			int index=-1, build=-1, near=0;
			
			st=new StringTokenizer(br.readLine());
			String action = st.nextToken();
			index=Integer.parseInt(st.nextToken());
			if(action.charAt(0)=='B') build = Integer.parseInt(st.nextToken());
			if(action.charAt(1)=='N') near=1;
			
			ans.append(station[index][near]).append("\n");
			if(build>0) {
				station[build][1-near]=index; // 새로 지을거 반대방향은 나
				station[build][near]=station[index][near]; // 새로 지을거 원래 방향은 내 옆에꺼
				station[station[index][near]][1-near]=build; // 내 옆에 있던거 반대방향은 새로지을거
				station[index][near]=build; // 내옆에 이제 새로 지은거
			}
			else {
				int next=station[index][near];
				station[index][near]=station[next][near]; // 지금위치에서 다다음꺼 연결
				station[station[next][near]][1-near]=index; // 다다음꺼에서 지금 위치로 연결
				station[next][0]=-1; // 폐쇄
				station[next][1]=-1; // 폐쇄
			}
		}
		System.out.print(ans.toString());
	}
}
