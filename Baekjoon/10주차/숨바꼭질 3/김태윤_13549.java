import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 김태윤_13549 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int start=sc.nextInt();
		int end=sc.nextInt();
		int[] arr=new int[100001];
		Arrays.fill(arr, 987654321);
		Queue<Integer> queue=new LinkedList<>();
		queue.offer(start);
		arr[start]=0;
		while(arr[end]==987654321) {
			int idx=queue.poll();
			
			//순간이동
			for(int i=idx*2;i<=100000;i*=2) {
				if(arr[i]>arr[idx]) {
					arr[i]=arr[idx];
					queue.offer(i);
				}
				else break;
			}
			
			//-1, +1
			if(idx>0 && arr[idx-1]>arr[idx]+1) {
				arr[idx-1]=arr[idx]+1;
				queue.offer(idx-1);
			}
			if(idx+1<=100000 && arr[idx+1]>arr[idx]+1) {
				arr[idx+1]=arr[idx]+1;
				queue.offer(idx+1);
			}
		}
		System.out.println(arr[end]);
	}
}
