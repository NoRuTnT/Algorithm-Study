package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class b11505 {
	static int div=1000000007;
	static int n,m,k;
	static long[] arr,tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		arr = new long[n+1];
		tree = new long[n*4];
		for(int i=1;i<=n;i++) {
			arr[i]=Long.parseLong(br.readLine());
		}
		maketree(1,n,1);
		for(int i=0;i<m+k;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				arr[b]=c;
				change(1,n,1,b,c);
			}else {
				bw.write(multi(1,n,1,b,c)+"\n");
				
			}
			
		}
		bw.flush();
		bw.close();
	}
	



	private static long maketree(long start, long end, int node) {
        if(start == end) {
            return tree[node] = arr[(int) start];
            
        }
        long mid = (start+end)/2;
        long left = maketree(start,mid,node*2);
        long right = maketree(mid+1,end,node*2+1);
        
        return tree[node]=left*right % div;      
    }
	
	private static long change(long start, long end, long node, long b, long c) {
		if(b>end || b<start) { 
			return tree[(int) node];
		}
		
		if(start == end) {
            return tree[(int) node]= c;
            
        }
		
		long mid = (start+end)/2;
		long left = change(start,mid,node*2,b,c);
		long right = change(mid+1,end,node*2+1,b,c);
        
        return tree[(int) node]=left*right % div; 
		
	}
	
	private static long multi(long start, long end, long node, long l, long r) {
		if(l>end || r<start) { 
			return 1;
		}
		
		if(l<=start && end <=r) {
			return tree[(int) node];
		}
		
		long mid = (start+end)/2;
		long left = multi(start,mid,node*2,l,r);
		long right = multi(mid+1,end,node*2+1,l,r);
		
		
		
		
		return left*right %div;
	}
	

}
