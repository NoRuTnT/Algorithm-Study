package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b2116 {		
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ArrayList<ArrayList> numlist = new ArrayList<>();
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			ArrayList<Integer> list = new ArrayList<>();
			for(int j=0;j<6;j++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			numlist.add(list);
		}//입력
		
		int max=-1; //최댓값저장용
		for(int start=1;start<=6;start++) { //맨밑주사위 윗면이 1~6일때
			int upnum=start; //윗면변수
			int save=0;	//밑면변수
			int a= numlist.get(0).indexOf(upnum); //윗면인덱스를 통해서
			if(a==0) {							  //첫주사위 아랫면의 숫자를 설명해놓음
				save=(int) numlist.get(0).get(5);
			}else if(a==1) {
				save=(int) numlist.get(0).get(3);
			}else if(a==2) {
				save=(int) numlist.get(0).get(4);
			}else if(a==3) {
				save=(int) numlist.get(0).get(1);
			}else if(a==4) {
				save=(int) numlist.get(0).get(2);
			}else if(a==5) {
				save=(int) numlist.get(0).get(0);
			}	
			int sum=0; //경우마다 합계저장용
			//옆면숫자계산
			if(upnum==6) {		//윗면이 6일때
				if(save==5) {   //윗면 6 아랫면 5
					sum+=4;
				}else {	        //윗면 6 아랫면 5제외
					sum+=5;
				}
			}else if(upnum==5) {
				if(save==6) {  //윗면 5 아랫면 6
					sum+=4;
				}else {			//윗면 5 아랫면 6제외
					sum+=6;
				}
			}else {
				if(save==6) { //윗면 5,6제외  아랫면 6
					sum+=5;
				}else {			//윗면 5,6제외  아랫면 6제외
					sum+=6;					
				}
			}
			//다음 위에있는 주사위 모두 계산시작
			for(int i=1;i<n;i++) {				
				save = upnum;		//전주사위의 윗면이 아랫면숫자	
				int idx = numlist.get(i).indexOf(upnum); //윗면숫자계산
				if(idx==0) {
					upnum=(int) numlist.get(i).get(5);
				}else if(idx==1){
					upnum=(int) numlist.get(i).get(3);
				}else if(idx==2){
					upnum=(int) numlist.get(i).get(4);
				}else if(idx==3){
					upnum=(int) numlist.get(i).get(1);
				}else if(idx==4){
					upnum=(int) numlist.get(i).get(2);
				}else{
					upnum=(int) numlist.get(i).get(0);
				}
				if(upnum==6) {		//옆면계산
					if(save==5) {
						sum+=4;
					}else {
						sum+=5;
					}
				}else if(upnum==5) {
					if(save==6) {
						sum+=4;
					}else {
						sum+=6;
					}
				}else {
					if(save==6) {
						sum+=5;
					}else {
						sum+=6;						
					}
				}
			}
			max = Math.max(sum, max); //6가지경우의수중 최댓값구하기
		}
		System.out.println(max);		
	}
}
