package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1011 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++) {
			st= new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			int far = end-start;
			if(far==1) {
				System.out.println(1);
			}else if(far==2){
				System.out.println(2);				
			}else {
				for(int j=3;j<=131072;j++) {					
					if((j&1)==0) {
						long n=j/2;
						long high = n*(n+1); //(n(n+1)/2)*2
						if(far<=high) {
							System.out.println(j);
							break;
						}
					}else {
						long n=(j/2)+1;
						long high = n*n; //n(n+1)/2 + (n-1)(n)/2
						if(far<=high) {
							System.out.println(j);
							break;
						}
					}
				}
				
			}
		}
	}

}
