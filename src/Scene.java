public class Scene {

	//ScreenNameList {"Story" , "Title" , "Game" , "GameOver" , "GameClear"};
	private ConsoleView view;
	private String nowScene;
	private Game game;
	private ScoreList scorelist;
	private int difficult=0;
	private static int EASY = 0;
	private static int NORMAL = 1;
	private static int HARD = 2;
	private final static String EASYSCORE = "./text/easy_score.txt";
	private final static String NORMALSCORE = "./text/normal_score.txt";
	private final static String HARDSCORE = "./text/hard_score.txt";
	
	public Scene(ConsoleView view) {
		this.view = view;
		this.scorelist = new ScoreList();
	}
	
	public boolean getSceneComp(String scene) {
		return nowScene.equals(scene);
	}
			
	public void controller(String sceneName,String event) {
		if(sceneName == "Story") {
			nowScene = "Story";
			drawStory();
		}else if(sceneName == "Title") {
			nowScene = "Title";
			if(event.equals("UP") && difficult>EASY)
				difficult--;
			else if(event.equals("DOWN") && difficult<HARD)
				difficult++;
			drawTitle(difficult);
		}else if(sceneName == "Game") {
			nowScene = "Game";
			if(game == null) {
				game = new Game(difficult);
			}
			drawGame(event);
		}else if(sceneName == "GameClear") {
			nowScene = "GameClear";
			drawGameClear();
		}else if(sceneName == "GameOver") {
			nowScene = "GameOver";
			drawGameOver();
		}else {
			System.out.println("sceneController error");
			System.exit(0);
		}
	}
	
	//Story処理
	
	private void drawStory() {
		view.clear();
		String story = "雪だるまのあなたは、ひょんなことから砂漠に迷い込んでしまった。砂漠は暑く、雪だるまは溶けてしまうため、一刻も早く寒い雪国に移動しなければならない。魔法を使って氷の道を作り、自分が溶ける前に砂漠から脱出せよ。";
		view.drawFramedStringIndent(story,'*',30,9,16);
		view.drawString("-Press Enter Key to Get Start-",view.Center("-Press Enter Key to Get Start-"),5);
		view.paint();
	}
	
	//Title処理
	
	private void drawTitle(int difficult) {
		view.clear();
		view.drawString("砂漠からの脱出",view.Center("砂漠からの脱出"),4); //Title
		view.drawString("初級",view.Center("初級"),12);
		view.drawString("中級",view.Center("初級"),15);
		view.drawString("上級",view.Center("初級"),18);
		if(difficult==EASY)
			view.drawString("->",36,12);
		else if(difficult==NORMAL)
			view.drawString("->",36,15);
		else if(difficult==HARD)
			view.drawString("->",36,18);
		view.paint();
	}
	
	//Game処理	
		
	private void drawGame(String event) {
		game.processingGame(event);
		if(game.getUpdateFlag()) {
			view.clear();	
			view.drawRect('*', 5, 1, 70, 4);
			if(game.getRemainingProblem() == 0) {
				nowScene="GameClear";
				drawGameClear();
				return;
			}else if(game.getHP()==0){
				nowScene="GameOver";
				drawGameOver();
				return;
			}else{
				view.drawString(game.getProblem(), view.Center(game.getProblem()) ,2);
				view.drawSnowman('*', 40, 20, game.getHP());
			}
			view.drawString(game.getInput(), view.Center(game.getProblem()) ,3);
			view.drawString("あと "+game.getRemainingProblem()+" 問", 60,20);
			view.drawString("総タイプ数　："+game.getInputNum(), 60, 18);
			view.drawString("正解タイプ数："+game.getMatchNum(), 60, 19);
			view.paint();
			game.setUpdateFlag();
		}		
	}
	
	//GameOver処理
	
	private void drawGameOver() {
		view.clear();
		view.drawRect('*', 10, 8 , 60, 8);
		view.drawString("脱出失敗...", view.Center("脱出失敗..."), 10);
		String chars = "文字数："+game.getMatchNum()+"文字";
		String time = "タイム："+(game.getTime()/20)+"秒";
		String miss = "ミス："+(game.getInputNum()-game.getMatchNum())+"回";
		view.drawString(chars, view.Center(chars), 11);
		view.drawString(time, view.Center(time), 12);
		view.drawString(miss, view.Center(miss), 13);
		view.paint();
		game = null;
	}
	
	
	//GameClear処理
	
	private void drawGameClear() {
		view.clear();
		
		//ここから自己スコア表示
		view.drawRect('*', 10, 3 , 60, 5);
		view.drawString("脱出成功！！", view.Center("脱出成功！！"), 4);
		String timeAndMiss = "タイム："+(game.getTime()/20)+"秒　ミス："+(game.getInputNum()-game.getMatchNum())+"回";
		view.drawString(timeAndMiss, view.Center(timeAndMiss), 5);
		String score = "スコア："+game.getScore()+"%";
		view.drawString(score, view.Center(score), 6);
		if(difficult == EASY) {
			scorelist.WriteScore(EASYSCORE, new Score(game.getScore(),Score.getNowDate()));
		}else if(difficult == NORMAL){
			scorelist.WriteScore(NORMALSCORE, new Score(game.getScore(),Score.getNowDate()));
		}else if(difficult == HARD){
			scorelist.WriteScore(HARDSCORE, new Score(game.getScore(),Score.getNowDate()));
		}
		

		//ここからランキング表示
		view.drawRect('*', 13 , 10 , 54, 9 );
		if(difficult == EASY) {
			scorelist.LoadScore(EASYSCORE);
		}else if(difficult == NORMAL){
			scorelist.LoadScore(NORMALSCORE);
		}else if(difficult == HARD){
			scorelist.LoadScore(HARDSCORE);
		}
		scorelist.sortScoreList();
		String score1 = "1          "+
				String.format("%5s", Double.toString(scorelist.getScore(0).getScore()))+
				"%          "+scorelist.getScore(0).getDate();
		String score2 = "2          "+
				String.format("%5s", Double.toString(scorelist.getScore(1).getScore()))+
				"%          "+scorelist.getScore(1).getDate();
		String score3 = "3          "+
				String.format("%5s", Double.toString(scorelist.getScore(2).getScore()))+
				"%          "+scorelist.getScore(2).getDate();
		
		view.drawString(score1, view.Center(score1), 12);
		view.drawString(score2, view.Center(score2), 14);
		view.drawString(score3, view.Center(score3), 16);
		
		
		view.paint();
		game = null;
	}
	
}
