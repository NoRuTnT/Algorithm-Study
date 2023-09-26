package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b12015 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] res_arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		//여기까지입력
		res_arr[0]=arr[0]; //첫번쨰꺼는 집어넣는다
		int len = 1; //길이저장변수
		for(int i=1;i<n;i++) { 
			int num = arr[i]; //2번째꺼부터 비교시작
			if(res_arr[len-1]<num) {  //결과배열의 마지막원소보다 크면 
				len++;            //길이증가시키고
				res_arr[len-1]=num; //그냥 마지막에 집어넣음
			}else {              //아니면 이분탐색진행하여 바꿔넣을위치구함
				int left =0;  
				int right = len;
				while(left<right) {
					int mid = (left+right)/2;
					if(res_arr[mid]<num) {
						left = mid+1;
					}else {
						right = mid;
					}
				}
				res_arr[left]=num; //바꿔넣기
			}
			
		}
//		int cnt=0; //개수세기
//		for(int e:res_arr) {
//			if(e!=0) {
//				cnt++;
//			}
//		}
		System.out.println(len);
		
	}

}
