import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김태윤_1725 {
    static int[] histogram;
    static int[] indexTree;
    static int n;
    static int ans=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        histogram=new int[n];
        indexTree =new int[4*n];
        for(int i=0;i<n;i++){
            histogram[i]=Integer.parseInt(br.readLine());
        }
        getTree(0, n-1, 1);
        solve(0, n-1);
        System.out.println(ans);
    }
    public static void getTree(int l, int r, int node){
        if(l==r){
            indexTree[node]=l;
            return;
        }
        int mid=(l+r)/2;
        getTree(l, mid, node*2);
        getTree(mid+1, r, node*2+1);
        indexTree[node]=(histogram[indexTree[node*2]]<histogram[indexTree[node*2+1]])? indexTree[node*2]: indexTree[node*2+1];
    }
    public static void solve(int l, int r){
        if(l>r) return;
        // 구간 내 최솟값에 해당하는 segmentTree index찾기
        int index=getIndex(0, n-1, l, r, 1);
        ans=Math.max(ans, histogram[index]*(r-l+1)); // 정답 갱신

        // 최솟값 기준으로 반 갈라서 또 지엽적인 최솟값을 찾는다
        solve(l, index-1);
        solve(index+1, r);
    }
    public static int getIndex(int start, int end, int l, int r, int node){
        if(end<l || start>r) return -1;
        if(l<=start && end<=r) return indexTree[node];
        int mid = (start+end)/2;
        int leftSide = getIndex(start, mid, l, r, node*2);
        int rightSide = getIndex(mid+1, end, l, r, node*2+1);

        if(leftSide==-1) return rightSide;
        if(rightSide==-1) return leftSide;
        if(histogram[leftSide]<histogram[rightSide]) return leftSide;
        return rightSide;
    }
}
