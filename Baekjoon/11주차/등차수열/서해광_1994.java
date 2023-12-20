// 1994. 등차수열
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int N, ans=2;
	public static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		for(int i=0 ; i<N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if(N<=2) {
			System.out.println(N);
			return;
		}
		Arrays.sort(arr);
		int idx = 0; // 현재 탐색하고 있는 인덱스 번호
		int dif = 0; // 현재 계산중인 등차
		int tmp = 0; // 현재까지 발견한 등차수열의 최대 길이
		int cur = 0; // 현재 등차수열이 성립하는 지 비교할 숫자
		for(int i=0 ; i<N ; i++) {
			for(int j=i+1 ; j<N ; j++) {
				tmp = 2;
				dif = arr[j]-arr[i];
				cur = arr[j];
				idx = j;
				while(true) {
					idx++;
					if(idx==N) {
						break;
					}
					if(arr[idx]-cur==dif) {
						cur = arr[idx]; // 현재 숫자 올림
						tmp++; // 1 plus count
					}
				}
				ans = Math.max(ans, tmp);
			}
		}
		System.out.println(ans);
	}
}
