import java.util.Scanner;

public class 김태윤_1016 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long min=sc.nextLong();
		long max=sc.nextLong();
		// index 0이 min, 끝자리가 max에 해당하는 에라토스테네스 체
		boolean[] eratos=new boolean[(int)(max-min+1)];
		long ans=max-min+1;
		//min에서 제곱수로 나눈 나머지를 기준으로 체를 거른다, max값 sqrt하면 백만이니까 for문 돌아간다
		for(long num=2; num*num<=max; num++) {
			long remainder=min%(num*num);
			if(remainder==0) remainder=num*num;
			for(long index=(num*num)-remainder; index<eratos.length; index+=num*num) {
				// 체를 거르기 시작하는 index
				if(!eratos[(int)index]) {
					eratos[(int)index]=true;
					ans--;
					//체에 걸러지면 ans 감소
				}
			}
		}
		System.out.println(ans);
	}
}

//오류 사유: 16번째줄 for문을 처음에 int로 돌려봤더니 형변환시켜줘도 뭔가 오류가 나는듯 해서
//그냥 long으로 돌리고 배열에 확인할때 int로 했습니당