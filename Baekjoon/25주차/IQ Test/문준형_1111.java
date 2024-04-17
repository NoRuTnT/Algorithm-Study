package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1111 {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());			
		}
		int pass = 0;
		int ans = 0;
		
		boolean duple = false;
		boolean samenum = false;
		if(n==1) {
			System.out.println("A");	
		}else {
		loop :for(int a=-1000;a<=1000;a++) {
				for(int b=-20001;b<=20001;b++) {
					int allpass = 0;
					int same = 0;
					for(int i=0;i<n-1;i++) {
						if(arr[i+1] == arr[i]) {
							same++;
						}
						if(arr[i+1] != arr[i]*a+b) {
							break;
						}
						allpass++;
					}
					if(same == n-1) {
						samenum = true;
						ans = arr[0];
						break loop;
					}
					if(allpass==n-1) {
//						System.out.print(a+" ");
//						System.out.print(b);
						pass++;
						ans = arr[n-1]*a+b;
					}					
					if(pass>=2) {
						duple = true;						
						break loop;
					}
					
				}
			}
			if(duple) {
				System.out.println("A");
			}else if(samenum){
				System.out.println(ans);
			}else {
				if(pass==0) {
					System.out.println("B");
				}else {
					System.out.println(ans);
				}
			}
		}	
	}
}
