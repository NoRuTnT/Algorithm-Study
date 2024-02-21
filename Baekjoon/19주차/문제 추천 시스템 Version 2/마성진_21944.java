import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static TreeSet<Problem> ts;
    static Map<Integer, TreeSet<Problem>> tsMap;
    static Map<Integer, Integer> LMap;
    static Map<Integer, Integer> GMap;

    static class Problem implements Comparable<Problem> {
        int P, L, G;

        Problem(int P, int L, int G) {
            this.P = P;
            this.L = L;
            this.G = G;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.L - o.L == 0)
                return this.P - o.P;
            else
                return this.L - o.L;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        ts = new TreeSet<>();
        tsMap = new HashMap<>();
        LMap = new HashMap<>();
        GMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            insert(tsMap, P, L, G);
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x, P, L, G;
            Problem problem;
            switch (command) {
                case "recommend":
                    G = Integer.parseInt(st.nextToken());
                    x = Integer.parseInt(st.nextToken());
                    if (x == 1)
                        sb.append(tsMap.get(G).last().P).append("\n");
                    else
                        sb.append(tsMap.get(G).first().P).append("\n");
                    break;
                case "recommend2":
                    x = Integer.parseInt(st.nextToken());
                    if (x == 1)
                        sb.append(ts.last().P).append("\n");
                    else
                        sb.append(ts.first().P).append("\n");
                    break;
                case "recommend3":
                    x = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());
                    if (x == 1)
                        problem = ts.ceiling(new Problem(0, L, 0));
                    else
                        problem = ts.floor(new Problem(0, L, 0));
                    if (problem == null)
                        sb.append(-1).append("\n");
                    else
                        sb.append(problem.P).append("\n");
                    break;
                case "add":
                    P = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());
                    G = Integer.parseInt(st.nextToken());
                    insert(tsMap, P, L, G);
                    break;
                case "solved":
                    P = Integer.parseInt(st.nextToken());
                    L = LMap.get(P);
                    G = GMap.get(P);
                    remove(P, L, G);
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void remove(int p, int l, int g) {
        ts.remove(new Problem(p, l, g));
        tsMap.get(g).remove(new Problem(p, l, g));
        LMap.remove(p);
        GMap.remove(p);
    }

    private static void insert(Map<Integer, TreeSet<Problem>> tsMap, int p, int l, int g) {
        ts.add(new Problem(p, l, g));
        if (tsMap.containsKey(g)) {
            tsMap.get(g).add(new Problem(p, l, g));
        } else {
            TreeSet<Problem> tmp = new TreeSet<>();
            tmp.add(new Problem(p, l, g));
            tsMap.put(g, tmp);
        }
        LMap.put(p, l);
        GMap.put(p, g);
    }

}
