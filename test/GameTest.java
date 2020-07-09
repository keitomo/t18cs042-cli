import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {
	
	private static int EASY = 0;
	private static int NORMAL = 1;
	private static int HARD = 2;
	
	@Test
	void 一定時間経過したらフラグを立てる() {
		Game test = new Game(EASY);
		
		for(int j=0;j<600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(true,test.getUpdateFlag());
	}
	
	@Test
	void 一定時間経過したらHPが減る() {
		Game test = new Game(EASY);
		
		for(int j=0;j<=600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(4,test.getHP());
		for(int j=0;j<=600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(3,test.getHP());
		for(int j=0;j<=600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(2,test.getHP());
		for(int j=0;j<=600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(1,test.getHP());
		for(int j=0;j<=600;j++) {
			test.processingGame("TIME_ELAPSED");
		}
		assertEquals(0,test.getHP());
	}
	
	@Test
	void 難易度ごとに問題数を決める() {
		Game easy = new Game(EASY);
		Game normal = new Game(NORMAL);
		Game hard = new Game(HARD);
		assertEquals(20,easy.getRemainingProblem());
		assertEquals(30,normal.getRemainingProblem());
		assertEquals(50,hard.getRemainingProblem());
	}
	
	@Test
	void 時間経過を記録する() {
		Game test = new Game(EASY);
		int limit = test.getTimeLimit();
		test.processingGame("TIME_ELAPSED");
		assertEquals(1,test.getTime());
		assertEquals(limit-1,test.getTimeLimit());
	}
	
	@Test
	void 問題を取得() {
		Game test = new Game(EASY);
		char p = test.getProblem().charAt(0);
		test.processingGame(""+p);
		assertEquals(1,test.getInputNum());
		assertEquals(1,test.getMatchNum());
	}
	
	@Test
	void 難易度ごとに減る時間が変わる() {
		Game easy = new Game(EASY);
		Game normal = new Game(NORMAL);
		Game hard = new Game(HARD);
		int elimit = easy.getTimeLimit();
		int nlimit = normal.getTimeLimit();
		int hlimit = hard.getTimeLimit();
		easy.processingGame(""+'!');
		normal.processingGame(""+'!');
		hard.processingGame(""+'!');
		assertEquals(elimit-20,easy.getTimeLimit());
		assertEquals(nlimit-40,normal.getTimeLimit());
		assertEquals(hlimit-60,hard.getTimeLimit());
	}
	
	@Test
	void アップデートフラグをおる() {
		Game test = new Game(EASY);
		assertEquals(true,test.getUpdateFlag());
		test.setUpdateFlag();
		assertEquals(false,test.getUpdateFlag());
	}
	
	@Test
	void 入力文字を取得() {
		Game test = new Game(EASY);
		char p = test.getProblem().charAt(0);
		String input = ""+p;
		test.processingGame(""+p);
		assertEquals(input,test.getInput());
		p = test.getProblem().charAt(1);
		input += ""+p;
		test.processingGame(""+p);
		assertEquals(input,test.getInput());
	}
	
	@Test
	void 次の問題をセットする() {
		Game test = new Game(EASY);
		String p = test.getProblem();
		for(int i=0;i<p.length();i++) {
			test.processingGame(""+p.charAt(i));
		}
		test.processingGame("");
		assertEquals(false, p.equals(test.getProblem()));
	}
	
	@Test
	void スコアを取得() {
		Game test = new Game(EASY);
		String p = test.getProblem();
		for(int i=0;i<p.length();i++) {
			test.processingGame(""+p.charAt(i));
		}
		test.processingGame("");
		assertEquals(100.0,test.getScore());
	}
	

}
