// 16434. 드래곤 앤 던전
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N,H;
	static class Room{
		long t;
		long a;
		long h;
		public Room(long t,long a,long h) {
			this.t=t;
			this.a=a;
			this.h=h;
		}
	}
	static Room[]rooms;
	static long[]ATK,dp;
	static boolean[]arr2;
	static long maxH, curH, curA;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		rooms=new Room[N];
		ATK=new long[N];
		dp=new long[N];
		arr2=new boolean[N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			long t=Long.parseLong(st.nextToken());
			long a=Long.parseLong(st.nextToken());
			long h=Long.parseLong(st.nextToken());
			rooms[i]=new Room(t, a, h);
		}
		curA=H;
		for(int i=0;i<N;i++) {
			if(rooms[i].t==1) {
				long limitHp = findMinHp(curA, rooms[i].h, rooms[i].a);
				if(curH<limitHp) {
					maxH+=limitHp-curH;
					curH=0;
				}else {
					curH-=limitHp;
				}
			}else {
				curH=Math.min(maxH, curH+rooms[i].h);
				curA+=rooms[i].a;
			}
		}
		System.out.println(maxH+1);
	}
	public static long findMinHp(long wAtk,long eHp,long eAtk) {
//		return (long)(Math.ceil((eHp/wAtk))-1)*eAtk;
		if(eHp%wAtk==0)
			return eAtk*(eHp/wAtk-1);
		else
			return eAtk*(eHp/wAtk);
	}
}
