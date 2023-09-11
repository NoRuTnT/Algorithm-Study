import java.util.Scanner;

public class 김태윤_1655 {
	public static void swap(int[] arr, int idx1, int idx2) {
		int tmp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=tmp;
	}
	public static void minInsert(int[] minHeap, int size) {
		int po=size;
		while(po/2>=1) {
			if(minHeap[po]<minHeap[po/2]) {
				swap(minHeap, po, po/2);
				po/=2;
			}
			else break;
		}
	}
	public static void maxInsert(int[] maxHeap, int size) {
		int po=size;
		while(po/2>=1) {
			if(maxHeap[po]>maxHeap[po/2]) {
				swap(maxHeap, po, po/2);
				po/=2;
			}
			else break;
		}
	}
	public static void minHeapify(int[] minHeap, int size) {
		int po=1;
		int l=po*2, r=po*2+1;
		while(l<=size) {
			if(minHeap[po]>minHeap[l] || (r<=size && minHeap[po]>minHeap[r])) {
				if(r<=size && minHeap[r]<minHeap[l]) {
					swap(minHeap, po, r);
					po=r;
					l=po*2;
					r=po*2+1;
				}
				else {
					swap(minHeap, po, l);
					po=l;
					l=po*2;
					r=po*2+1;
				}
			}
			else break;
		}
	}
	public static void maxHeapify(int[] maxHeap, int size) {
		int po=1;
		int l=po*2, r=po*2+1;
		while(l<=size) {
			if(maxHeap[po]<maxHeap[l] || (r<=size && maxHeap[po]<maxHeap[r])) {
				if(r<=size && maxHeap[r]>maxHeap[l]) {
					swap(maxHeap, po, r);
					po=r;
					l=po*2;
					r=po*2+1;
				}
				else {
					swap(maxHeap, po, l);
					po=l;
					l=po*2;
					r=po*2+1;
				}
			}
			else break;
		}
	}

	public static void main(String[] args) {
		//최대힙, 최소힙 두가지를 운용한다
		//최대힙 꼭대기에 있는걸 올린다
		//최대힙은 최소힙와 같거나 1개 더 많도록 한다
		//처음부터 감이 안 와서 인터넷 보고함 ㅠ
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		
		int[] maxHeap=new int[50001];
		int[] minHeap=new int[50001];
		maxHeap[1]=sc.nextInt();
		StringBuilder sb=new StringBuilder();
		sb.append(maxHeap[1]).append("\n");
		for(int i=2;i<=n;i++) {
			int num=sc.nextInt();
			if(i%2==0) {
				minHeap[i/2]=num;
				minInsert(minHeap, i/2);
			}
			else {
				maxHeap[i/2+1]=num;
				maxInsert(maxHeap, i/2+1);
			}
			//일단 박고 정렬 완료
			
			if(maxHeap[1]>minHeap[1]) {
				int tmp=maxHeap[1];
				maxHeap[1]=minHeap[1];
				minHeap[1]=tmp;
				minHeapify(minHeap, i/2);
				maxHeapify(maxHeap, i/2+i%2);
			}
			//서로 바꾸고 더 큰 값 있는지 확인
			sb.append(maxHeap[1]).append("\n");
		}
		System.out.println(sb);
	}
}
