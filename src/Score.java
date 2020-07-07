import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Score {
	private String data;
	private double score;
	
	public Score(double score,String data) {
		this.data = data;
		this.score = score;
	}

	public String getData() {
		return data;
	}

	public static String getNowData() {
		Calendar cl = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		return sdf.format(cl.getTime());
	}

	public double getScore() {
		return score;
	}	
}
