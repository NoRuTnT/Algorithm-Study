import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 알고리즘 : LIS (이분 정렬을 이용해서 시간 복잡도가 nlogn)
 * 참고 블로그 : https://rebro.kr/33
 */


public class 김태윤_12015 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] nums=new int[n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			nums[i]=Integer.parseInt(st.nextToken());
		}
		/*
		 * 1) 앞에서부터 for문을 하나 쭉 돌려서 수열에 추가한다 (시간 복잡도 n)
		 * 2-1) 만약 만들어진 수열의 가장 큰 수보다 수가 크면, 그냥 그 수열 뒤에 붙인다
		 * 2-2) 만약 가장 큰 수보다 수가 작을 경우, 수열에서 자기보다 크고 그 중 가장 작은 수를 이분 탐색으로 찾고
		 * 		수열에서 그 수의 값으로 바꿔준다 (시간 복잡도 logn -> for문과 합하면 nlogn)
		 * 3) 위 과정을 거치면 실제 LIS 내용물은 잘못됐을 수 있지만, 길이는 같아진다
		 * 예제 ) 6 7 1 2 3 5 4
		 */
		ArrayList<Integer> LIS=new ArrayList<>();
		LIS.add(nums[0]); // 시작값 넣어주고 시작
		for(int i=1;i<n;i++) { // 1
			int end = LIS.size()-1; // LIS 배열 마지막 인덱스
			if(LIS.get(end)<nums[i]) { //2-1
				LIS.add(nums[i]);
			}
			else { //2-2
				int start=0;
				while(start<end) { // 이분 탐색 시작
					// 자기보다 큰 수 중 가장 작은 수를 찾아야 함
					// 자기보다 작은 수를 보면, 그 수를 배제시키고 뒷부분을 이분탐색
					// 자기보다 큰 수를 만나면, 그 수를 포함시켜서 앞부분을 이분탐색
					int mid=(start+end)/2;
					if(LIS.get(mid)<nums[i]) {
						start=mid+1;
					}
					else {
						end=mid;
					}
				}
				//while문 탈출 시 start==end. 이 index의 값을 nums[i]로 바꿔준다
				LIS.set(start, nums[i]);
			}
		}
		System.out.println(LIS.size()); // LIS 길이를 출력
	}
}
