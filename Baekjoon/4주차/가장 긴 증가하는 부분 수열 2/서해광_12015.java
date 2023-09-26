// 12015. 가장 긴 증가하는 부분 수열 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] arr;
	public static ArrayList<Integer> list;
	// 이건 N^2의 시간복잡도로는 안됨
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		list.add(arr[0]);
		for(int i=1 ; i<N ; i++) {
			if(arr[i]>list.get(list.size()-1)) {
				list.add(arr[i]);
			}else {
				int idx = binarySearch(0, list.size()-1, arr[i]);
				list.set(idx, arr[i]);
			}
			
		}
		System.out.println(list.size());
	}
	public static int binarySearch(int st, int ed, int num) {
		int mid = 0;
		while(st<=ed) {
			mid = (st+ed)/2;
			if(num == list.get(mid)) {
				return mid;
			}
			if(num>list.get(mid)) {
				st = mid+1;
			}else {
				ed = mid-1;
			}
		}
		return st;
	}
}
