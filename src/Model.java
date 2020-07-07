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
				scene.sceneController("Game",event);
			}
		
		}else { //タイピング時の処理
			if(scene.getNowScene().equals("Story")) {
				if(event.equals("")) {
					scene.sceneController("Title",event);
				}
			}else if(scene.getNowScene().equals("Title")) {
				if(event.equals("UP") || event.equals("DOWN")||event.equals("HOME"))
					scene.sceneController("Title",event);
				if(event.equals("")) 
					scene.sceneController("Game",event);
			}else if(scene.getNowScene().equals("Game")) {
				scene.sceneController("Game",event);
			}else if(scene.getNowScene().equals("GameClear") || scene.getNowScene().equals("GameOver")) {
				if(event.equals("")) {
					scene.sceneController("Title", event);
				}
				
			}
		}
	}

	public void run() throws IOException {
		scene.sceneController("Story","");
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
