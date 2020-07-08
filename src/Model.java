import java.io.IOException;

public class Model {

	private ConsoleView			view;
	private ConsoleController	controller;
	private Scene				scene;
	private final static int WIDTH = 80;
	private final static int HEIGHT = 24;

	public Model() {
		view		=	new ConsoleView(WIDTH,HEIGHT);
		controller	=	new ConsoleController(this);
		scene		= 	new Scene(view);
	}

	public synchronized void process(String event) {
		if (event.equals("TIME_ELAPSED")) { //時間経過時の処理
			if(scene.getNowScene().equals("Game")) {
				scene.controller("Game",event);
			}
		
		}else { //タイピング時の処理
			if(scene.getNowScene().equals("Story")) {
				if(event.equals("")) {
					scene.controller("Title",event);
				}
			}else if(scene.getNowScene().equals("Title")) {
				if(event.equals("UP") || event.equals("DOWN"))
					scene.controller("Title",event);
				if(event.equals("")) 
					scene.controller("Game",event);
			}else if(scene.getNowScene().equals("Game")) {
				scene.controller("Game",event);
			}else if(scene.getNowScene().equals("GameClear") || scene.getNowScene().equals("GameOver")) {
				if(event.equals("")) {
					scene.controller("Title", event);
				}
				
			}
		}
	}

	public void run() throws IOException {
		scene.controller("Story","");
		controller.run();
		view.clear();
		view.drawString("GAME END", view.Center("GAME END"), 12);
		view.drawString("-Please Press Enter Key-",view.Center("-Please Press Enter Key-"),17);
		view.paint();
	}

	public static void main(String[] args) throws IOException {
		System.out.println("");
		Model model = new Model();
		model.run();
		System.exit(0);
	}

}
