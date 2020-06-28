import java.util.Arrays;
import java.util.List;

public class Screen {

	//ScreenNameList {"Story" , "Title" , "Game" , "GameOver" , "GameClear"};
	private ConsoleView view;
	private String nowScreen;
	private Text text;
	private int width;
	private int height;
	String input = "";
	String problem = "";
	private int TitleCursor=0;
	private int input_num=0;
	private int check_num=0;
	private int remaining_problem_num=0;
	private int match_num=0;
	private boolean clearFlag = false;
	int time;

	
	public Screen(ConsoleView view) {
		this.view = view;
		this.width = Model.WIDTH;
		this.height = Model.HEIGHT;
		text = new Text();
	}
	
	public Screen(ConsoleView view,int width,int height) {
		this.view = view;
		this.width = width;
		this.height = height;
		text = new Text();
	}
	
	public int centered(String s) {
		return ((width - s.length())/2);
	}
	
	public String getNowScreen() {
		return nowScreen;
	}
	
	public boolean getClearFlag() {
		return clearFlag;
	}
		
	public void ScreenController(String ScreenName,String event) {
		if(ScreenName == "Story") {
			nowScreen = "Story";
			drawStory();
		}else if(ScreenName == "Title") {
			nowScreen = "Title";
			if(event.equals("UP") && TitleCursor>0)
				TitleCursor--;
			else if(event.equals("DOWN") && TitleCursor<2)
				TitleCursor++;
			drawTitle(TitleCursor);
		}else if(ScreenName == "Game") {
			nowScreen = "Game";
			if(remaining_problem_num == 0) {
				setGame();
				setProblem();
			}
			drawGame(event);
		}
	}
	//Story処理
	private void drawStory() {
		view.clear();
		String story = "雪だるまのあなたは、ひょんなことから砂漠に迷い込んでしまった。砂漠は暑く、雪だるまは溶けてしまうため、一刻も早く寒い雪国に移動しなければならない。魔法を使って氷の道を作り、自分が溶ける前に砂漠から脱出せよ。";
		view.drawFramedStringIndent(story,'*',30,9,16);
		view.drawString("-Press Enter Key to Get Start-",25,5);
		view.paint();
	}
	
	//Title処理
	private void drawTitle(int i) {
		view.clear();
		view.drawString("砂漠からの脱出",33,4); //Title
		view.drawString("初級",38,12);
		view.drawString("中級",38,15);
		view.drawString("上級",38,18);
		if(i==0)
			view.drawString("->",36,12);
		else if(i==1)
			view.drawString("->",36,15);
		else if(i==2)
			view.drawString("->",36,18);
		view.paint();
	}
	
	//Game処理	
	
	private void setGame() {
		if(TitleCursor == 0) {
			text.setWordList("./word_list.txt");
			remaining_problem_num=20;
		}else if(TitleCursor == 1) {
			text.setWordList("./word_list.txt");
			remaining_problem_num=30;
		}else if(TitleCursor == 2) {
			text.setWordList("./word_list.txt");
			remaining_problem_num=50;
		}
	}
	
	private void setProblem() {
		problem = text.getRandomWord();
	}
	
	private void resetGame() {
		TitleCursor=0;
		input_num=0;
		check_num=0;
		match_num=0;
		remaining_problem_num=0;
		input="";
		problem="";
		clearFlag=false;
		time=0;
	}
	
	private void drawGame(String event) {
		if (event.equals("TIME_ELAPSED")) {
			time++;
			if(time%20 == 0) {
				view.drawString(""+(time/20), 40, 8);
				view.paint();
			}
		}else {
			view.clear();	
			view.drawRect('*', 5, 2, 70, 5);
			if(event.length() > 0 && !Text.matchText(problem,input)) {
				input_num++;
				if(Text.CheckText(problem, event.charAt(0), check_num) ) {
					check_num++;
					match_num++;
					input += event;
				}
			}
			if(Text.matchText(problem,input) && event.equals("") && !clearFlag) {
				setProblem();
				remaining_problem_num--;
				check_num=0;
				input = "";
			}
			if(remaining_problem_num == 0) {
				String clear="ゲームクリア";
				String clearTime = "クリアタイム: "+(time/20);
				view.drawString(clear, (width - clear.length())/2 ,4);
				view.drawString(clearTime, (width - clearTime.length())/2, 8);
				clearFlag=true;
			}else {
				view.drawString(problem, (width - problem.length())/2 ,4);
				view.drawString(""+(time/20), 40, 8);
			}
			view.drawString(input, (width - problem.length())/2 ,20);
			view.drawString("あと "+remaining_problem_num+" 問", 60,20);
			view.drawString("総タイプ数　："+input_num, 60, 18);
			view.drawString("正解タイプ数："+match_num, 60, 19);
			
			view.paint();
			
		}
		if(clearFlag) {
			nowScreen = "Story";
			resetGame();
		}
		
	}
	
}
