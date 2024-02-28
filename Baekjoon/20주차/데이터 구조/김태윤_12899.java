package Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_12899 {
    static int n;
    static int[] tree = new int[2000001*4];
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st=new StringTokenizer(br.readLine());
            int t=Integer.parseInt(st.nextToken());
            int x=Integer.parseInt(st.nextToken());
            if(t==1) add(x, 1, 1, 2000000);
            else delete(x, 1, 1, 2000000);
        }
        System.out.println(ans.toString());
    }
    static void add(int x, int idx, int l, int r){
        if(l==r) {
            tree[idx]++;
            return;
        }
        int mid = (l+r)/2;
        if(x<=mid) add(x, idx*2, l, mid);
        else add(x, idx*2+1, mid+1, r);
        tree[idx]++;
    }
    static void delete(int x, int idx, int l, int r){
        if(l==r){
            ans.append(l).append("\n");
            tree[idx]--;
            return;
        }
        int mid = (l+r)/2;
        if(tree[idx*2] >= x) delete(x, idx*2, l, mid);
        else delete(x - tree[idx*2], idx*2+1, mid+1, r);
        tree[idx]--;
    }
}
