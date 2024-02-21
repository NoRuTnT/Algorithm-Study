package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class b21944 {
    static class Problem implements Comparable<Problem>{
        int num;
        int level;
        int group;        
        Problem(int num, int level, int group){
            this.num=num;
            this.level=level;
            this.group=group;
        }
        @Override
        public int compareTo(Problem o) {
            if(this.level != o.level) {
            	return this.level-o.level;
            }else {
            	return this.num-o.num;            	
            }
        }
    }
    static TreeSet<Problem>[] algotreeset = new TreeSet[101];
    static TreeSet<Problem> treeset = new TreeSet<>();
    static HashMap<Integer,Problem> map = new HashMap<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    public static void main(String[] args) throws IOException {
        init();
        bw.flush();
        bw.close();        
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=1;i<=100;i++){
            algotreeset[i] = new TreeSet<>();
        }
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l, g);
            map.put(p, problem);
            treeset.add(problem);
            algotreeset[g].add(problem);
        }
        m = Integer.parseInt(br.readLine());
        int p,l,g,x;
        
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            switch(str){
                case "add":
                    p = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    g = Integer.parseInt(st.nextToken());
                    add(p,l,g);
                    break;
                    
                case "recommend":
                    g = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    recommend(g, x);
                    break;
                    
                case "recommend2":
                    x = Integer.parseInt(st.nextToken());
                    recommend2(x);
                    break;
                    
                case "recommend3":
                    x = Integer.parseInt(st.nextToken());
                    l = Integer.parseInt(st.nextToken());
                    recommend3(x,l);
                    break;
                    
                case "solved":
                    p = Integer.parseInt(st.nextToken());
                    solved(p);
                    break;
                    
            }
        }
    }

    static void add(int p, int l, int g){
        Problem problem = new Problem(p, l, g);
        map.put(p, problem);
        treeset.add(problem);
        algotreeset[g].add(problem);
    }

    static void recommend(int g, int x) throws IOException{
        if(x==1) {
        	bw.write(algotreeset[g].last().num+"\n");
        }else {
        	bw.write(algotreeset[g].first().num+"\n");        	
        }
    }

    static void recommend2(int x) throws IOException{
        if(x==1) {
        	bw.write(treeset.last().num+"\n");
        }else {
        	bw.write(treeset.first().num+"\n");        	
        }
    }

    static void recommend3(int x, int l) throws IOException{
        if(x==1) {
            SortedSet<Problem> problems = treeset.tailSet(new Problem(0, l, 1));
            if(problems.isEmpty()) {
            	bw.write("-1\n");
            }else {
            	bw.write(problems.first().num+"\n");
            }
        }else{
            SortedSet<Problem> problems = treeset.headSet(new Problem(0, l, 1));
            if(problems.isEmpty()) {
            	bw.write("-1\n");
            }else {
            	bw.write(problems.last().num+"\n");            	
            }
        }
    }

    static void solved(int p){
        Problem problem = map.get(p);
        map.remove(p);
        treeset.remove(problem);
        algotreeset[problem.group].remove(problem);
    }
}
