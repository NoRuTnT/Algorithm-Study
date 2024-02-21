package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김태윤_21944 {
    static class Problem implements Comparable<Problem>{
        int number;
        int level;
        int group;
        Problem(){}
        Problem(int number, int level, int group){
            this.number=number;
            this.level=level;
            this.group=group;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.level != o.level) return this.level-o.level;
            return this.number-o.number;
        }
    }

    static TreeSet<Problem>[] groupProblemSet = new TreeSet[101];
    static TreeSet<Problem> problemSet = new TreeSet<>();
    static HashMap<Integer, Problem> problemMap = new HashMap<>();
    static StringBuilder ans = new StringBuilder();
    static int n, m;
    public static void main(String[] args) throws IOException {
        init();
        System.out.print(ans.toString());
    }
    public static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=1; i<=100; i++){
            groupProblemSet[i] = new TreeSet<>();
        }
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(p, l, g);
            problemMap.put(p, problem);
            problemSet.add(problem);
            groupProblemSet[g].add(problem);
        }
        m = Integer.parseInt(br.readLine());
        int p, l, g, x;
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            String action = st.nextToken();
            switch(action){
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
        problemMap.put(p, problem);
        problemSet.add(problem);
        groupProblemSet[g].add(problem);
    }

    static void recommend(int g, int x){
        if(x==1) ans.append(groupProblemSet[g].last().number).append("\n");
        else ans.append(groupProblemSet[g].first().number).append("\n");
    }

    static void recommend2(int x){
        if(x==1) ans.append(problemSet.last().number).append("\n");
        else ans.append(problemSet.first().number).append("\n");
    }

    static void recommend3(int x, int l){
        if(x==1) {
            SortedSet<Problem> problems = problemSet.tailSet(new Problem(0, l, 1));
            if(problems.isEmpty()) ans.append("-1\n");
            else ans.append(problems.first().number).append("\n");
        }
        else{
            SortedSet<Problem> problems = problemSet.headSet(new Problem(0, l, 1));
            if(problems.isEmpty()) ans.append("-1\n");
            else ans.append(problems.last().number).append("\n");
        }
    }

    static void solved(int p){
        Problem problem = problemMap.get(p);
        problemMap.remove(p);
        problemSet.remove(problem);
        groupProblemSet[problem.group].remove(problem);
    }
}
