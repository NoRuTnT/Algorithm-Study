import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_13164 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		//1) 아이들 키를 전부 입력받는다
		//2) 바로 앞사람과의 키차이를 배열에 저장한다
		//3) 오름차순정렬한다
		//4) 0~n-k-1번째 값을 더해서 제출한다
		int[] gap=new int[n-1]; // 키차이는 총 n-1개
		st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken());
		for(int i=0;i<n-1;i++) {
			int b=Integer.parseInt(st.nextToken());
			gap[i]=b-a;
			a=b;
		}
		br.close();
		quickSort(0, n-2, gap);
		int ans=0;
		for(int i=0;i<n-k;i++) {
			ans+=gap[i];
		}
		System.out.println(ans);
	}
	public static void quickSort(int start, int end, int[] gap) {
		if(start>=end) return;
		mot(start, end, gap); // 3개의 index에서 중앙값 찾아서 그거 pivot으로 두기
		int pivot=gap[start];
		int l=start, r=end+1;
		while(l<r) {
			while(l<end && pivot>gap[++l]);
			while(r>start && pivot<gap[--r]);
			if(l<r) swap(l, r, gap);
		}
		swap(start, r, gap);
		quickSort(start, r-1, gap);
		quickSort(r+1, end, gap);
	}
	public static void mot(int start, int end, int[] gap) {
		int mid=(start+end)/2;
		if(gap[start]<gap[mid]) swap(start, mid, gap);
		if(gap[start]>gap[end]) swap(start, end, gap);
	}
	public static void swap(int a, int b, int[] gap) {
		int tmp=gap[a];
		gap[a]=gap[b];
		gap[b]=tmp;
	}
}
