package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class b1016 {
	static ArrayList<Long> prime_num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		boolean[] num = new boolean[1000001];	
        //gpt형님
//        for (long i = 2; i * i <= max; i++) {
//            long start = (min % (i * i) == 0) ? min : min - (min % (i * i)) + (i * i);
//            for (long j = start; j <= max; j += (i * i)) {
//                isPrimeSquare[(int) (j - min)] = true;
//            }
//        }
//
//        long nono = 0;
//        for (boolean isSquare : isPrimeSquare) {
//            if (!isSquare) {
//                nono++;
//            }
//        }       		
		num[0]=true;
		num[1]=true;
		for(int i=2;i<Math.sqrt(1000000)+1;i++) {
			if(!num[i]) {
				int j=2;
				while(i*j<=1000000) {
					num[i*j]=true;
					j++;
				}
			}
		}		
		prime_num = new ArrayList<>();
		for(long i=0;i<1000000;i++) {
			if(!num[(int) i]) {
				prime_num.add(i*i);
			}
		}
		//여기까지 에라토스테네스의체를이용해 소수의제곱 어레이리스트생성
		prime_num.sort(Comparator.naturalOrder()); //정렬해서 max넘어가면 컷해서 조금시간줄일수있지않을까? 해서넣은거
		boolean[] visit = new boolean[1000001]; // max-min은 최대백만 방문배열생성
		long nono = max-min+1; //전체갯수
		long start=0; //시작변수
		//시작위치를 min부터 정직하게하는것이아니라 e의 이전부분은 스킵하도록 지정해준다
		for(long e:prime_num) {  //어레이리스트에서 소수제곱수 하나씩꺼냄
			if((min/e)*e<min) { // 만약 (min/e)*e 가 min보다 작다면 min은 min*e + a (a<e)의 형태이기때문에 시작지점은 min*(e+1)이된다.
				start=((min/e)*e)+e;
			}else { //아니면 그냥 (min/e)*e 으로진행
				start=(min/e)*e;
			}
			for(long i=start;i<=max;i++) { //시작지점구한곳부터 max까지 탐색
				if(i%e==0 && !visit[(int) (i-min)]) { // e로 나누어떨어지거나 한번도 체크하지않았다면 진행 
					nono--;	
					visit[(int) (i-min)]=true;
														
				}				
			}
		}
		System.out.println(nono);
	}

}
