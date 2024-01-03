import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ArrayList<Long> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			list.add(Long.parseLong(st.nextToken()));			
		}
		list.sort(Comparator.naturalOrder());
		Long min = Long.MAX_VALUE;
		int res1=0;
		int res2=0;
		int res3=0;
		for(int i=0;i<n-2;i++) {
			int left = i;
			int mid = i+1;
			int right = n-1;
			while(mid < right) {
				Long sum = list.get(left) + list.get(mid) + list.get(right);
				if(min>Math.abs(sum)) {
					min = Math.abs(sum);
					res1 = left;
					res2 = mid;
					res3 = right;					
				}				
				if(sum>0) {
					right--;
				}else {
					mid++;
				}
				
			}
			
		}		
		System.out.println(list.get(res1)+" "+list.get(res2)+" "+list.get(res3));
		
		
	}
}
