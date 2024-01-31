package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b1725 {
    static int n,size;
    static int[] arr,tree;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        arr = new int[n+1];
        for(int i=1;i<=n;i++) {
            arr[i]=Integer.parseInt(br.readLine());            
        }
        size=1;
        while(size<n) {
            size = size<<1;
        }
        tree = new int [size*2+1];
        maketree(1,n,1);
        System.out.println(maxarea(1,n));
         
    }
    private static int maketree(int start, int end, int node) {
        if(start == end) {
            return tree[node] = start;
            
        }
        int mid = (start+end)/2;
        int left = maketree(start,mid,node*2);
        int right = maketree(mid+1,end,node*2+1);
        
        if(arr[left]<arr[right]) {
            return tree[node]=left;
        }else {
            return tree[node]=right;
        }        
    }
    
    private static int maxarea(int left, int right) {
        int index = minheight(1,n,left,right,1);
//        System.out.println(index+" "+left+" "+right);
        int area = (right-left+1) * arr[index];
        int tmp=0;
        if(left<index) {
            tmp=maxarea(left,index-1);
            if(area<tmp) {
                area=tmp;
            }
        }
        if(index<right){
            tmp=maxarea(index+1,right);
            if(area<tmp) {
                area=tmp;
            }
        }
        return area;
        
    }
    
    private static int minheight(int start, int end, int left, int right, int node) {
        if(right<start || left>end) {
            return -1;
        }
        if(left<=start && end<=right) {
            return tree[node];
        }
        
        int m = (start+end)/2;
        int l = minheight(start,m,left,right,node*2);
        int r = minheight(m+1,end,left,right,node*2+1);
        
        if(l==-1) {
            return r;
        }else if(r==-1) {
            return l;
        }else {
            if(arr[l]<arr[r]) {
                return l;
            }else {
                return r;
            }
        }
    }

}
