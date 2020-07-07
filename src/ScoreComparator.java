import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {

	@Override
	public int compare(Score s1, Score s2) {
		if(s1.getScore() < s2.getScore()) {
			return 1;
		}else if(s1.getScore() > s2.getScore()) {
			return -1;
		}else {
			return 0;
		}
	}

}
