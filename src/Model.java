import java.io.IOException;

public class Model {

	private ConsoleView			view;
	private ConsoleController	controller;
	private Screen				screen;
	public final static int WIDTH = 80;
	public final static int HEIGHT = 24;

	public Model() {
		view		=	new ConsoleView(this,WIDTH,HEIGHT);
		controller	=	new ConsoleController(this);
		screen		= 	new Screen(view,WIDTH,HEIGHT);
	}

	public synchronized void process(String event) {
		if (event.equals("TIME_ELAPSED")) { //時間経過時の処理
			if(screen.getNowScreen().equals("Game")) {
				screen.ScreenController("Game",event);
			}
		
		}else { //タイピング時の処理
			if(screen.getNowScreen().equals("Story")) {
				if(event.equals("")) {
					screen.ScreenController("Title",event);
				}
			}else if(screen.getNowScreen().equals("Title")) {
				if(event.equals("UP") || event.equals("DOWN")||event.equals("HOME"))
					screen.ScreenController("Title",event);
				if(event.equals("")) 
					screen.ScreenController("Game",event);
			}else if(screen.getNowScreen().equals("Game")) {
					screen.ScreenController("Game",event);
			}
		}
	}

	public void run() throws IOException {
		screen.ScreenController("Story","");
		controller.run();
		view.clear();
		view.drawString("GAME END", 36, 12);
		view.paint();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("");
		Model model = new Model();
		model.run();
		System.exit(0);
	}

}
