import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Score {
	private String date;
	private double score;
	
	public Score(double score,String date) {
		this.date = date;
		this.score = score;
	}

	public String getDate() {
		return date;
	}

	public static String getNowDate() {
		Calendar cl = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		return sdf.format(cl.getTime());
	}

	public double getScore() {
		return score;
	}	
}
