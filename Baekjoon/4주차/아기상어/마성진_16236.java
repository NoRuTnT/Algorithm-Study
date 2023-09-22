import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int[][] map;
    public static int sharkI, sharkJ;
    public static int sharkSize;
    public static int sharkEatCnt;
    public static int time;
    public static List<int[]> sharkCanEat;
    public static int[] iDir = { -1, 0, 1, 0 };
    public static int[] jDir = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sharkSize = 2;
        sharkEatCnt = 0;
        time = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 9) {
                    sharkI = i;
                    sharkJ = j;
                }
            }
        }
        gameStart();
        System.out.println(time);
    }

    private static void gameStart() {
    	while(bfs(sharkI, sharkJ)) {
    		int[] fishForEat = chooseForEat();
    		eat(fishForEat);
    	}
	}

	private static boolean bfs(int sharkI, int sharkJ) {
		sharkCanEat = new ArrayList<>();
        int distance = 1;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] { sharkI, sharkJ, 0 });
        visited[sharkI][sharkJ] = true;
        while (!que.isEmpty()) {
            int reqeat = que.size();
            for (int round = 0; round < reqeat; round++) {
                int[] now = que.poll();
                int nowI = now[0];
                int nowJ = now[1];
                for (int i = 0; i < 4; i++) {
                    int nextI = nowI + iDir[i];
                    int nextJ = nowJ + jDir[i];
                    if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < N && !visited[nextI][nextJ]) {
                        int next = map[nextI][nextJ];
                        visited[nextI][nextJ] = true;
                        if (next != 0 && next < sharkSize) {
                            sharkCanEat.add(new int[] { nextI, nextJ, distance });
                        } else if (next > sharkSize) {
                            continue;
                        }
                        que.add(new int[] { nextI, nextJ });
                    }
                }
            }
            distance++;
        }
        if(sharkCanEat.size()>0) {
        	return true;
        }
        return false;
    }

    private static void eat(int[] fishForEat) {
    	map[sharkI][sharkJ] = 0;
    	time += fishForEat[2];
    	sharkI = fishForEat[0];
    	sharkJ = fishForEat[1];
    	sharkEatCnt++;
    	map[sharkI][sharkJ] = 9;
    	if(sharkEatCnt == sharkSize) {
    		sharkSize++;
    		sharkEatCnt = 0;
    	}
	}

	private static int[] chooseForEat() {
        int[] fishForEat = new int[3];
        fishForEat[2] = Integer.MAX_VALUE;
        for (int i = 0; i < sharkCanEat.size(); i++) {
            boolean flag = false;
            int[] fish = sharkCanEat.get(i);
            if (fish[2] < fishForEat[2]) {    // 가까운 거리일 때
                flag = true;
            } else if (fish[2] == fishForEat[2]) {    // 거리가 같은 물고리일 때
                if (fish[0] < fishForEat[0]) {    // 더 위에 있는 물고기 일 때
                    flag = true;
                } else if (fish[0] == fishForEat[0]) {    // 더 위에 있는 물고기가 또 있을 때
                    if (fish[1] < fishForEat[1]) {    // 더 왼쪽에 있는 물고기 일 떄
                        flag = true;
                    }
                }
            }
            if (flag) {
                for (int j = 0; j < 3; j++) {
                    fishForEat[j] = fish[j];
                }
            }
        }
        return fishForEat;
    }

}
