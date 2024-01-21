import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.*;

public class Main {
  static int n;
  static int m;
  static int[] arr;
  static int[] maxTree;
  static int[] minTree;
  public static void main(String[] args) throws IOException {
    BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(br.readLine());
    n=Integer.parseInt(st.nextToken());
    m=Integer.parseInt(st.nextToken());
    arr=new int[n+1];
    for(int i=1;i<=n;i++){
      arr[i]=Integer.parseInt(br.readLine());
    }
    maxTree = new int[n*4+1];
    minTree = new int[n*4+1];
    dfs(1, n, 1);
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<m;i++){
      st=new StringTokenizer(br.readLine());
      int l=Integer.parseInt(st.nextToken());
      int r=Integer.parseInt(st.nextToken());
      int max = getMax(1, n, l, r, 1);
      int min = getMin(1, n, l, r, 1);
      sb.append(min).append(" ").append(max).append("\n");
    }
    System.out.print(sb.toString());
  }
  public static int getMax(int start, int end, int l, int r, int node){
    if(end<l || start>r) return -1;
    if(l<=start && r>=end) return maxTree[node];
    int mid = (start+end)/2;
    int leftSide = getMax(start, mid, l, r, node*2);
    int rightSide = getMax(mid+1, end, l, r, node*2+1);
    return Math.max(leftSide, rightSide);
  }
  public static int getMin(int start, int end, int l, int r, int node){
    if(end<l || start>r) return Integer.MAX_VALUE;
    if(l<=start && r>=end) return minTree[node];
    int mid = (start+end)/2;
    int leftSide = getMin(start, mid, l, r, node*2);
    int rightSide = getMin(mid+1, end, l, r, node*2+1);
    return Math.min(leftSide, rightSide);
  }
  public static void dfs(int l, int r, int node){
    if(l==r){
      maxTree[node]=arr[l];
      minTree[node]=arr[l];
      return;
    }
    int mid = (l+r)/2;
    dfs(l,mid, node*2);
    dfs(mid+1, r, node*2+1);
    minTree[node]=Math.min(minTree[node*2], minTree[node*2+1]);
    maxTree[node]=Math.max(maxTree[node*2], maxTree[node*2+1]);
    return;
  }
}
