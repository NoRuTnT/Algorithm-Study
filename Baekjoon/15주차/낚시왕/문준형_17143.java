package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b17143 {
    static int r,c,m,total;
    static int[] dr = {0,0,0,1,-1};
    static int[] dc = {0,-1,1,0,0};
    static ArrayList<shark> map;
    static int fisher;
    static class shark{
        int row;
        int col;
        int speed;
        int dir;
        int size;

        public shark(int row, int col, int speed, int dir, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        fisher = -1;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            map.add(new shark(row,col,speed,dir,size));

        }
        total = 0;
        for (int i = 0; i < c; i++) {
            fisher++;
            if(map.isEmpty()){
                System.out.println(total);
                return;
            }
            hunt();
            sharkmove();
        }
        System.out.println(total);
    }

    private static void hunt() {
        Collections.sort(map, new Comparator<shark>() {
            @Override
            public int compare(shark o1, shark o2) {
                if(o1.col >= o2.col){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for (int i = 0; i < map.size(); i++) {
            if(map.get(i).col==fisher){
                total += map.get(i).size;
                map.remove(i);
                return;
            }
        }
    }

    private static void sharkmove() {
    	int[][] check = new int[r+1][c+1];
        int a = map.size();
        for (int i = 0; i < a; i++) {
            int irow = map.get(i).row;
            int icol = map.get(i).col;
            int idir = map.get(i).dir;
            int ispeed = map.get(i).speed;
            
            for (int j = 0; j < ispeed; j++) {
                irow+=dr[idir];
                icol+=dc[idir];
                if(irow>=r||irow<0||icol>=c||icol<0){
                    change(idir);
                    irow+=dr[idir];
                    icol+=dc[idir];
                    irow+=dr[idir];
                    icol+=dc[idir];                    
                }                
            }            
            check[irow][icol]+=1;
        }
        Collections.sort(map, new Comparator<shark>() {
            @Override
            public int compare(shark o1, shark o2) {
                if(o1.size >= o2.size){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
        for(int i=0;i<r;i++) {
        	for(int j=0;j<c;j++) {
        		if(check[i][j]>1) {
        			for(int k=0;k<map.size();k++) {
        				if(map.get(k).row==i && map.get(k).col==j) {
        					for(int z=1;z<check[i][j];z++) {
        						map.remove(k);        						
        					}
        				}
        			}
        		}
        	}
        }       

    }

    private static void change(int idir) {
        if(idir == 1){
            idir=2;
        }else if(idir == 2){
            idir=1;
        }else if(idir == 3){
            idir =4;
        }else{
            idir=3;
        }
    }

}
