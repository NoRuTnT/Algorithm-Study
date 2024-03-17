package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_16434 {
	static int n;
	static long inputAttack, ans=0L;
	static long[][] rooms;
	static final long MAX = Long.MAX_VALUE; // 백만곱하기백만해도 될거 같은데 틀리다 뜸
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		rooms=new long[n][3];
		inputAttack = Long.parseLong(st.nextToken());
		for(int i=0; i<n; i++){
			st=new StringTokenizer(br.readLine());
			rooms[i][0]=Long.parseLong(st.nextToken());
			rooms[i][1]= Long.parseLong(st.nextToken());
			rooms[i][2]= Long.parseLong(st.nextToken());
		}
		BinarySearch(1, MAX);
		System.out.println(ans);
	}
	public static void BinarySearch(long l, long r){
		if(l==r) {
			ans=l;
			return;
		}
		long maxHealth = (l+r)/2;
		if(fight(maxHealth)) BinarySearch(l, maxHealth);
		else BinarySearch(maxHealth+1, r);
	}
	public static boolean fight(long maxHealth){
		long currHealth = maxHealth;
		long currAttack = inputAttack;
		for(int i=0; i<n; i++){
			long type = rooms[i][0];
			long attack = rooms[i][1];
			long health = rooms[i][2];
			if(type==1){
				long fightNum = health/currAttack;
				if(health%currAttack == 0) fightNum--; // 몬스터를 공격
				currHealth -= attack*fightNum; // 공격당함
				if(currHealth <= 0) return false;
			}
			else{
				currHealth=Math.min(maxHealth, currHealth+health);
				currAttack+=attack;
			}
		}
		return true;
	}
}
