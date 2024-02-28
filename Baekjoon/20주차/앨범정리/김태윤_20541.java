package Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 김태윤_20541 {
    static class Album{
        TreeSet<String> photoSet;
        String albumName;
        Album upperAlbum; // 상위앨범
        TreeMap<String, Album> lowerAlbum; //(이름 & 하위앨범)
        Album(){}
        Album(String albumName, Album upperAlbum){
           this.albumName = albumName;
           this.upperAlbum = upperAlbum;
           this.photoSet = new TreeSet<>();
           this.lowerAlbum = new TreeMap<>();
        }
    }
    static Album curr;
    static Album root = new Album("album", null);
    static int n;
    static int albumCnt, photoCnt;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        String command, action;
        curr = root;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            action = st.nextToken();
            albumCnt=0;
            photoCnt=0;
            controller(command, action);
        }
        System.out.print(ans.toString());
    }
    static void controller(String command, String action){
        switch(command){
            case "mkalb":
                makeAlbum(action);
                break;
            case "rmalb":
                removeAlbum(action);
                break;
            case "insert":
                insertPhoto(action);
                break;
            case "delete":
                deletePhoto(action);
                break;
            case "ca":
                changeAlbum(action);
                break;
        }
    }
    static void makeAlbum(String newAlbumName){
        if(curr.lowerAlbum.containsKey(newAlbumName)){
            ans.append("duplicated album name\n");
            return;
        }
        Album newAlbum = new Album(newAlbumName, curr);
        curr.lowerAlbum.put(newAlbumName, newAlbum);
    }
    static void removeAlbum(String action){

        switch(action){
            case "-1":
                if(!curr.lowerAlbum.isEmpty()){
                    recursion(curr.lowerAlbum.get(curr.lowerAlbum.firstKey()));
                    curr.lowerAlbum.remove(curr.lowerAlbum.firstKey());
                }
                break;
            case "0":
                while(!curr.lowerAlbum.isEmpty()){
                    recursion(curr.lowerAlbum.get(curr.lowerAlbum.firstKey()));
                    curr.lowerAlbum.remove(curr.lowerAlbum.firstKey());
                }
                break;
            case "1":
                if(!curr.lowerAlbum.isEmpty()){
                    recursion(curr.lowerAlbum.get(curr.lowerAlbum.lastKey()));
                    curr.lowerAlbum.remove(curr.lowerAlbum.lastKey());
                }
                break;
            default:
                if(curr.lowerAlbum.containsKey(action)){
                    recursion(curr.lowerAlbum.get(action));
                    curr.lowerAlbum.remove(action);
                }
        }
        ans.append(albumCnt).append(" ").append(photoCnt).append("\n");
    }
    static void recursion(Album album){
        albumCnt++;
        photoCnt+=album.photoSet.size();
        while(!album.lowerAlbum.isEmpty()){
            recursion(album.lowerAlbum.get(album.lowerAlbum.firstKey()));
            album.lowerAlbum.remove(album.lowerAlbum.firstKey());
        }
    }
    static void insertPhoto(String action){
        if(curr.photoSet.contains(action)){
            ans.append("duplicated photo name\n");
            return;
        }
        curr.photoSet.add(action);
    }
    static void deletePhoto(String action){
        if(!curr.photoSet.isEmpty()){
            switch(action){
                case "-1":
                    curr.photoSet.pollFirst();
                    ans.append("1\n");
                    break;
                case "0":
                    ans.append(curr.photoSet.size()).append("\n");
                    curr.photoSet.clear();
                    break;
                case "1":
                    curr.photoSet.pollLast();
                    ans.append("1\n");
                    break;
                default:
                    if(curr.photoSet.contains(action)){
                        curr.photoSet.remove(action);
                        ans.append("1\n");
                    }
                    else{
                        ans.append("0\n");
                    }
            }
        }
        else{
            ans.append("0\n");
        }
    }
    static void changeAlbum(String action){
        switch(action){
            case "..":
                if(!curr.equals(root)){
                    curr = curr.upperAlbum;
                }
                break;
            case "/":
                curr = root;
                break;
            default:
                if(curr.lowerAlbum.containsKey(action)){
                    curr = curr.lowerAlbum.get(action);
                }
        }
        ans.append(curr.albumName).append("\n");
    }
}
