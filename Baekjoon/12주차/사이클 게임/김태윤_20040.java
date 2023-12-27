import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 김태윤_20040 {
    static int[] rank;
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        root=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            root[i]=i;
            rank[i]=1;
        }

        int ans=0;
        for(int tc=1;tc<=m;tc++){
            st=new StringTokenizer(br.readLine());
            int num1=Integer.parseInt(st.nextToken());
            int num2=Integer.parseInt(st.nextToken());
            if(find(num1)==find(num2)) {
                ans=tc;
                break;
            }
            union(num1,num2);
        }
        System.out.println(ans);
    }
    
    public static int find(int num){
        if(root[num]!=num) root[num]=find(root[num]);
        return root[num];
    }
    
    public static void union(int num1, int num2){
        if(rank[root[num1]]>rank[root[num2]]){
            root[root[num2]]=root[num1];
        }
        else if(rank[root[num2]]>rank[root[num1]]){
            root[root[num1]]=root[num2];
        }
        else{
            rank[root[num1]]++;
            root[root[num2]]=root[num1];
        }
    }
}
