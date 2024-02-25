package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1711 {
	static long[][] point;
	static int n,cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		n=Integer.parseInt(br.readLine());
		point = new long[n][2];
		cnt=0;
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine());
			point[i][0]=Long.parseLong(st.nextToken());
			point[i][1]=Long.parseLong(st.nextToken());			
		}
        for (int i = 0; i < n-2; i++) {
            for (int j = i + 1; j < n-1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (triangle(point[i], point[j], point[k])) {                 
                        cnt++;                        
                    }
                }                
            }            
        }
        
        System.out.println(cnt);
	}
    static boolean triangle(long[] a, long[] b, long[] c) {
        // 각 점으로부터 다른 두 점 사이의 벡터를 계산
    	long[] ab = new long[] { b[0]-a[0], b[1]-a[1] };
    	long[] ac = new long[] { c[0]-a[0], c[1]-a[1] };
    	long[] bc = new long[] { c[0]-b[0], c[1]-b[1] };

        // 벡터의 내적을 계산
        long dotABAC = ab[0]*ac[0] + ab[1]*ac[1];
        long dotABBC = ab[0]*bc[0] + ab[1]*bc[1];
        long dotACBC = ac[0]*bc[0] + ac[1]*bc[1];

        // 내적이 0이면 직각 삼각형
        return (dotABAC == 0) || (dotABBC == 0) || (dotACBC == 0);
    }
	    
}
	

	

