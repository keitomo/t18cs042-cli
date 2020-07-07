
public class Game {
	
	private static int EASY = 0;
	private static int NORMAL = 1;
	private static int HARD = 2;
	
	private static String EASYLIST = "./easy_list.txt";
	private static String NORMALLIST = "./normal_list.txt";
	private static String HARDLIST = "./hard_list.txt";
	
	private Text text;
	String input = "";
	String problem = "";
	private int difficult=EASY;
	private int inputNum=0;
	private int checkNum=0;
	private int matchNum=0;
	private int remainingProblemNum=0;
	private int time=0;
	private int timeLimit=3000;
	private int HP=5;
	private boolean updateFlag = true;
	

	
	
	public Game(int difficult) {
		this.difficult = difficult;
		text = new Text();
		setGame();
	}

	private void setGame() {
		if(difficult == EASY) {
			text.setWordList(EASYLIST);
			remainingProblemNum=20;
		}else if(difficult == NORMAL) {
			text.setWordList(NORMALLIST);
			remainingProblemNum=30;
		}else if(difficult == HARD) {
			text.setWordList(HARDLIST);
			remainingProblemNum=50;
		}
		setProblem();
	}
	
	private void setProblem() {
		problem = text.getRandomWord();
	}
		
	public int getTime() {
		return time;
	}
	
	public int getTimeLimit() {
		return timeLimit;
	}
	
	public int getHP() {
		return HP;
	}
	
	public String getProblem() {
		return problem;
	}
	
	public int getRemainingProblem() {
		return remainingProblemNum;
	}

	public String getInput() {
		return input;
	}
	
	public int getInputNum() {
		return inputNum;
	}

	public int getMatchNum() {
		return matchNum;
	}
	
	public boolean getUpdateFlag() {
		return updateFlag;
	}
	
	public void setUpdateFlag() {
		updateFlag = false;
	}
	
	public double getScore() {
		double missType = (double)inputNum - (double)matchNum;
		double score = (((double)inputNum-missType)/(double)inputNum)*100;
		return ((double)Math.round(score * 10))/10;
	}

	
	public void processingGame(String event) {
		if (event.equals("TIME_ELAPSED")) {
			time++;
			timeLimit--;
			if(time%600 == 0)
				updateFlag = true;
		}else {
			if(event.length() > 0 && !Text.matchText(problem,input)) {
				inputNum++;
				if(Text.checkText(problem, event.charAt(0), checkNum) ) {
					checkNum++;
					matchNum++;
					input += event;
				}else {
					if(difficult == EASY)
						timeLimit-=20;
					else if(difficult == NORMAL)
						timeLimit-=40;
					else if(difficult == HARD)
						timeLimit-=60;
				}
			}
			if(Text.matchText(problem,input) && event.equals("") && remainingProblemNum!=0) {
				setProblem();
				remainingProblemNum--;
				checkNum=0;
				input = "";
			}
			updateFlag = true;
		}
		HP=timeLimit/600+1;
		if(timeLimit <=0)
			HP = 0;
	}
}
