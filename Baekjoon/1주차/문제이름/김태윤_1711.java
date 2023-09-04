import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class point{
	long r;
	long c;
	point(){}
	point(long r, long c){
		this.r=r;
		this.c=c;
	}
}
public class Baekjoon1711직각삼각형 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		point[] arr=new point[n]; // 좌표 위치 저장
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long r=Long.parseLong(st.nextToken());
			long c=Long.parseLong(st.nextToken());
			arr[i]=new point(r, c);
		}
		
		br.close();
		int ans=0;
		//피타고라스 법칙 : a^2+b^2=c^2을 만족하면 직각삼각형이다
		//세 개의 좌표를 고르는게 1500C3 (약 5억6천만) 가지수라서 브루트포스로 어려울거 같은데
		//일단 브루트포스라니까 걍 하는걸로
		//변의 길이의 제곱은 long이기 때문에 long으로 생각해야됨
		for(int i=0;i<n-2;i++) {
			for(int j=i+1;j<n-1;j++) {
				long aa=(arr[i].r-arr[j].r)*(arr[i].r-arr[j].r)+(arr[i].c-arr[j].c)*(arr[i].c-arr[j].c);
				for(int k=j+1;k<n;k++) {
					long bb=(arr[j].r-arr[k].r)*(arr[j].r-arr[k].r)+(arr[j].c-arr[k].c)*(arr[j].c-arr[k].c);
					long cc=(arr[k].r-arr[i].r)*(arr[k].r-arr[i].r)+(arr[k].c-arr[i].c)*(arr[k].c-arr[i].c);
					if(aa+bb==cc || bb+cc==aa || cc+aa==bb) ans++;
				}
			}
		}
		System.out.println(ans);
	}
}
