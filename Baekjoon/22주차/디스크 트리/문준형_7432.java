import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

class File{	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	TreeMap<String,File> child;		
	
	public File() {		
		child = new TreeMap<>();
	}
	
	public File(TreeMap<String,File>child) {
		this.child = child;
	}
	
	

	void addchild(ArrayList<String> list,int idx ) {
		if(idx == list.size()) {
			return;
		}
		File nextfile = child.get(list.get(idx));
		
		if(nextfile != null) {
			nextfile.addchild(list, idx+1);
		}else {
			File newfile = new File();
			child.put(list.get(idx), newfile);
			newfile.addchild(list, idx+1);
		}		
				
	}
	
	void print(int idx) throws IOException {
		if(child.isEmpty()) {
			return;
		}
		for(Map.Entry<String,File>entry : child.entrySet()) {
			for(int i=0;i<idx;i++) {
				bw.write(" ");
			}			
			bw.write(entry.getKey()+"\n");
			entry.getValue().print(idx+1);
		}
		bw.flush();
	}
}

public class b7432 {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		File root = new File();
		for(int i=0;i<n;i++) {
			ArrayList<String> list = new ArrayList<>();
			StringTokenizer st= new StringTokenizer(br.readLine(),"\\");
			while(true) {
				if(!st.hasMoreTokens()) {
					break;
				}						
				list.add(st.nextToken());				
			}
			root.addchild(list, 0);
		}
		root.print(0);		
	}
}
