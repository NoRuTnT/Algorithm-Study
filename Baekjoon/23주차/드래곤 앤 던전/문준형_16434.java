import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Room{
		int type;
		int value1;
		int value2;
		
		public Room(int type, int value1, int value2) {
			this.type = type;
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	static int n;
	static long start,mid,end,reshp;
	static Room[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		long rootatk = Long.parseLong(st.nextToken());
		arr = new Room[123456];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int value1 = Integer.parseInt(st.nextToken());
			int value2 = Integer.parseInt(st.nextToken());
			arr[i] = new Room(type,value1,value2);
		}
		
		start=1;
		end = Long.MAX_VALUE/2;		
		reshp = 0;		
		while(start<=end) {	
			mid = (start+end)/2;
			long hp = mid;
			long atk = rootatk;
			if(fight(hp,atk)) {				
				end = mid-1;			
			}else {
				start = mid+1;				
			}
		}
		System.out.println(reshp);
	}


	private static boolean fight(long hp, long atk) {
		boolean life = true;
		long roothp = hp;
		for(int i=0;i<n;i++) {			
			int type = arr[i].type;
			int value1 = arr[i].value1;
			int value2 = arr[i].value2;
			
			if(type==1) {
				int monsteratk = value1;
				int monsterhp = value2;
				
				if(monsterhp%atk!=0) {
					hp -= monsteratk * (monsterhp/atk);
				}else {
					hp -= monsteratk * (monsterhp/atk-1);
				}
				
				if(hp<=0) {
					life = false;
					break;
				}
				
				
			}else {
				int atkup = value1;
				int heal = value2;
				
				atk += atkup;
				hp += heal;
				if(hp>roothp) {
					hp = roothp;
				}				
			}			
			
		}
		if(life) {				
			reshp = roothp;
			return true;
		}else {
			return false;
		}		
		
	}

}
