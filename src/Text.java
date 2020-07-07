import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Text {
	
	private Random random = new Random();
	private ArrayList<String> WordList = new ArrayList<String>();
	static Map<String, String> m = new HashMap<String, String>();
	
	public Text() {
		setKana2Rome();
	}
	
	public void setWordList(String f){
	    try{
	      File file = new File(f);

	      if (checkBeforeReadfile(file)){
	        BufferedReader br = new BufferedReader(new FileReader(file));

	        String str;
	        while((str = br.readLine()) != null){
	        	WordList.add(str);
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
	    }
	
	public String getRandomWord() {
		int randomValue = random.nextInt(WordList.size());
		return kana2rome(WordList.get(randomValue));
	}

	public static boolean checkText(String problem, char input,int count) {
		return (problem.charAt(count) == input);
	}
	
	public static boolean matchText(String problem, String input) {
		return problem.equals(input);
	}
	
	private static boolean checkBeforeReadfile(File file){
	    if (file.exists()){
	      if (file.isFile() && file.canRead()){
	        return true;
	      }
	    }

	    return false;
	  }
	
	private void setKana2Rome() {
		m.put("あ", "a");m.put("い", "i");m.put("う", "u");m.put("え", "e");m.put("お", "o");
		m.put("か", "ka");m.put("き", "ki");m.put("く", "ku");m.put("け", "ke");m.put("こ", "ko");
		m.put("さ", "sa");m.put("し", "shi");m.put("す", "su");m.put("せ", "se");m.put("そ", "so");
		m.put("た", "ta");m.put("ち", "chi");m.put("つ", "tu");m.put("て", "te");m.put("と", "to");
		m.put("な", "na");m.put("に", "ni");m.put("ぬ", "nu");m.put("ね", "ne");m.put("の", "no");
		m.put("は", "ha");m.put("ひ", "hi");m.put("ふ", "fu");m.put("へ", "he");m.put("ほ", "ho");
		m.put("ま", "ma");m.put("み", "mi");m.put("む", "mu");m.put("め", "me");m.put("も", "mo");
		m.put("や", "ya");m.put("ゆ", "yu");m.put("よ", "yo");
		m.put("ら", "ra");m.put("り", "ri");m.put("る", "ru");m.put("れ", "re");m.put("ろ", "ro");
		m.put("わ", "wa");m.put("を", "wo");m.put("ん", "nn");
		m.put("が", "ga");m.put("ぎ", "gi");m.put("ぐ", "gu");m.put("げ", "ge");m.put("ご", "go");
		m.put("ざ", "za");m.put("じ", "zi");m.put("ず", "zu");m.put("ぜ", "ze");m.put("ぞ", "zo");
		m.put("だ", "da");m.put("ぢ", "di");m.put("づ", "du");m.put("で", "de");m.put("ど", "do");
		m.put("ば", "ba");m.put("び", "bi");m.put("ぶ", "bu");m.put("べ", "be");m.put("ぼ", "bo");
		m.put("ぱ", "pa");m.put("ぴ", "pi");m.put("ぷ", "pu");m.put("ぺ", "pe");m.put("ぽ", "po");
		m.put("きゃ", "kya");m.put("きゅ", "kyu");m.put("きょ", "kyo");
		m.put("しゃ", "sya");m.put("しゅ", "syu");m.put("しょ", "syo");
		m.put("ちゃ", "tya");m.put("ちゅ", "tyu");m.put("ちょ", "tyo");
		m.put("にゃ", "nya");m.put("にゅ", "nyu");m.put("にょ", "nyo");
		m.put("ひゃ", "hya");m.put("ひゅ", "hyu");m.put("ひょ", "hyo");
		m.put("みゃ","mya");m.put("みゅ","myu");m.put("みょ","myo");
		m.put("りゃ", "rya");m.put("りゅ", "ryu");m.put("りょ", "ryo");
		m.put("ぎゃ", "gya");m.put("ぎゅ", "gyu");m.put("ぎょ", "gyo");
		m.put("じゃ", "ja");m.put("じゅ", "ju");m.put("じょ", "jo");
		m.put("ぢゃ", "dya");m.put("ぢゅ", "dyu");m.put("ぢょ", "dyo");
		m.put("びゃ", "bya");m.put("びゅ", "byu");m.put("びょ", "byo");
		m.put("ぴゃ", "pya");m.put("びゅ", "pyu");m.put("ぴょ", "pyo");
		m.put("ふぁ","fa");m.put("ふぃ","fi");m.put("ふぇ","fe");m.put("ふぉ","fo");
		m.put("しぇ","sye");m.put("ちぇ","che");m.put("てぃ","tei");m.put("しぇ","sye");
		m.put("うぃ","wi");m.put("うぇ", "we");
		m.put("ー", "-");
	}
	
	public String kana2rome(String s) {
		StringBuilder t = new StringBuilder();
		for ( int i = 0; i < s.length(); i++ ) {
			if ( i <= s.length() - 2 )  {
				if ( m.containsKey(s.substring(i,i+2))) {
					t.append(m.get(s.substring(i, i+2)));
					i++;
				} else if (m.containsKey(s.substring(i, i+1))) {
					t.append(m.get(s.substring(i, i+1)));
				} else if ( s.charAt(i) == 'っ' ) {
					t.append(((String) m.get(s.substring(i+1, i+2))).charAt(0));
				} else {
					t.append(s.charAt(i));
		        }
			} else {
				if (m.containsKey(s.substring(i, i+1))) {
					t.append(m.get(s.substring(i, i+1)));
				} else {
					t.append(s.charAt(i));
				}
			}
		}
		return t.toString();
	}
	
	public static boolean isZenkaku(char c) {
        return String.valueOf(c).getBytes().length > 1;
	}
	
	public static int countStringLength(String s) {
		int count=0;
		for(int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (isZenkaku(c)) count+=2;
			else count++;
		}
		return count;
	}

}
