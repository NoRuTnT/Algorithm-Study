package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class b1114 {    
    static int l,k,c,tsize;
    static TreeSet<Integer> treeset;  
    static int[] cut,len;  

    static int check(int size) {
        int use = c;
        int tmp = 0;

        // 맨 오른쪽부터 왼쪽으로 확인
        for(int i=tsize;i>=0;i--) {
            if(len[i]>size) {
            	return -1;  
            }
            tmp += len[i];            
            if(tmp>size) {
                use--;            
                tmp = len[i];    
            }
            if(use==0) {      
                if(cut[i]>size) { 
                	return -1;
                }else {
                	return cut[i];
                }
            }
        }
        return cut[0];  
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());        
        l = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());        
        if(k<c) {
            c = k;
        } 
        treeset = new TreeSet<>();        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<k;i++) {
            int tmp = Integer.parseInt(st.nextToken());
            treeset.add(tmp);
        }        
        tsize = treeset.size();
        cut = new int[tsize+1];
        len = new int[tsize+1];
        int cnt=0;
        for(int e:treeset) {
        	cut[cnt]=e;
        	cnt++;
        }
        cut[tsize] = l;    
        
        len[0] = cut[0];
        for(int i=1;i<=tsize;i++) {
            len[i] = cut[i]-cut[i-1];
        }
      
        int s = 1;
        int e = l;
        int ans1 = l;
        int ans2 = l;
        while(s<=e) {
            int m = (s+e)/2;
            int firstcut = check(m);
            if(firstcut>=0){
                ans1 = m;
                ans2 = firstcut;
                e = m-1;
            } else {
                s = m+1;
            }
        }        
        System.out.println(ans1 + " " + ans2);
    }
}
