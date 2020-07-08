import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreList {
	private ArrayList<String> ScoreString ;
	private ArrayList<Score> ScoreList ;
	
	public ScoreList() {
		ScoreString = new ArrayList<String>();
		ScoreList = new ArrayList<Score>();
	}
	
	public void LoadScore(String f) {
		ScoreString = new ArrayList<String>();
		ScoreList = new ArrayList<Score>();
		try{
			File file = new File(f);

			if (checkBeforeReadfile(file)){
				BufferedReader br = new BufferedReader(new FileReader(file));

				String str;
		       while((str = br.readLine()) != null){
		    	   ScoreString.add(str);
		        }

		        	br.close();
			}else{
				System.out.println("ファイルが見つからないか開けません");
		      	System.exit(0);
			}
		    }catch(FileNotFoundException e){
		    	System.out.println(e);
		    	System.exit(0);
		    }catch(IOException e){
		    	System.out.println(e);
		    	System.exit(0);
		    }
		
			for(int i=0;i<ScoreString.size();i++) {
				String Temp = ScoreString.get(i);
				ScoreList.add(new Score( Double.parseDouble(Temp.split(" ")[0]),Temp.split(" ")[1]));
			}
		
		    
		}
	
	
	public void WriteScore(String f,Score s) {
	    try{
	        File file = new File(f);

	        if (checkBeforeWritefile(file)){
	          BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

	          bw.write(""+s.getScore()+" "+s.getDate());
	          bw.newLine();
	          
	          bw.close();
	        }else{
	          System.out.println("ファイルに書き込めません");
	          System.exit(0);
	        }
	      }catch(IOException e){
	        System.out.println(e);
	        System.exit(0);
	      }
	}
	
	private static boolean checkBeforeReadfile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canRead()){
	        return true;
	      }
	    }

	    return false;
	  }
	
	private static boolean checkBeforeWritefile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canWrite()){
	        return true;
	      }
	    }

	    return false;
	  }
	
	public void sortScoreList() {
		Collections.sort(ScoreList, new ScoreComparator());
	}
	
	public Score getScore(int rank) {
		return ScoreList.get(rank);
	}
		
}
