package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1711 {
	static int[][] point;
	static int n,cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		n=Integer.parseInt(br.readLine());
		point = new int[n][2];
		cnt=0;
		for(int i=0;i<n;i++) {
			st= new StringTokenizer(br.readLine());
			point[i][0]=Integer.parseInt(st.nextToken());
			point[i][1]=Integer.parseInt(st.nextToken());			
		}
		int len = point.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (triangle(point[i], point[j], point[k])) {                 
                        cnt++;                        
                    }
                }                
            }            
        }
        
        System.out.println(cnt);
	}
    static boolean triangle(int[] a, int[] b, int[] c) {
        // 각 점으로부터 다른 두 점 사이의 벡터를 계산
        int[] ab = new int[] { b[0]-a[0], b[1]-a[1] };
        int[] ac = new int[] { c[0]-a[0], c[1]-a[1] };
        int[] bc = new int[] { c[0]-b[0], c[1]-b[1] };

        // 벡터의 내적을 계산
        int dotABAC = ab[0]*ac[0] + ab[1]*ac[1];
        int dotABBC = ab[0]*bc[0] + ab[1]*bc[1];
        int dotACBC = ac[0]*bc[0] + ac[1]*bc[1];

        // 내적이 0이면 직각 삼각형
        return (dotABAC == 0) || (dotABBC == 0) || (dotACBC == 0);
    }    

}
	

	

