
public class ConsoleView {

	private char[][] screen;
	private int width;
	private int height;
	private final static int WIDTH = 80;
	private final static int HEIGHT = 24;
	private final static char BLANK = ' ';
	
	public ConsoleView() {
		this(WIDTH,HEIGHT);
	}
	public ConsoleView(int width,int height) {
		this.width = width;
		this.height= height;
		screen = new char[width][height];
		clear();
	}
	
	public int Center(String s) {
		return (width - Text.countStringLength(s))/2 ;
	}

	public void clear() { //　スクリーン初期化
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				screen[x][y]=BLANK;
			}
		}
	}

	public void paint() { //　スクリーン出力
		for(int y=0;y< height;y++) {
			for(int x=0;x<width;x++) {
				char c = screen[x][y];
				System.out.print(c);
				if (Text.isZenkaku(c))
					x++;
			}
			System.out.println();
		}
	}

	public void put(char c,int x,int y) { // 座標(x,y)に文字cを表示
		if(x >= 0 && x < width && y >= 0 && y < height)
			screen[x][y]=c;
	}

	public void drawString(String s,int x,int y) { // 座標(x,y)から文字列sを表示
		for(int i=0,j=0;i<s.length();i++,j++) {
			char c = s.charAt(i);
			put(c,x+j,y);
			if (Text.isZenkaku(c)) j++;
		}
	}

	public int drawStringIndent(String s,int ind ,int x,int y) { // 座標(x,y)から文字列sを文字数indで改行して表示
		int indent  = 0; //改行する回数
		int s_width = 0; //文字の最小横幅
		for(int i=0,j=0;i<s.length();i++,j++) {
			if(s_width > ind) {
				indent++;
				s_width = 0;
				j=0;
			}
			char c = s.charAt(i);
			put(c,x+j,y+indent);
			if (Text.isZenkaku(c)) j++;
			s_width++;
		}
		return indent;
	}
	
	public void drawRect(char c,int x,int y,int w,int h) { // 座標(x,y)から長さw高さhの文字cで囲まれた四角形を表示
		for(int i=0;i<w;i++) {
			for(int j=0;j<h;j++) {
				if(i==0 || j==0 || i==w-1 || j==h-1)
					put(c,x+i,y+j);
			}
		}
	}
	
	public void drawFramedString(String s,char c,int x,int y) { // 座標(x,y)から文字列sを文字cで囲んだ四角形を表示
		drawString(s,x,y);
		drawRect(c,x-1,y-1,s.length()+2,3);
	}
	
	public void drawFramedStringIndent(String s,char c,int ind,int x,int y) { // 座標(x,y)から文字列sを文字cで囲んだ四角形を表示
		int indent = drawStringIndent(s,ind,x,y);
		ind*=2;
		drawRect(c,x-1,y-1,ind+4,indent+3);
	}

	public void drawOval(char c, int x, int y, int w, int h) {
		double a = w/2.0;
		double b = h/2.0;

		for (double i=-a;i<=a;i+=0.01) {
			double j = b* Math.sqrt(1 - Math.pow(i,2) / Math.pow(a, 2));
			put(c,(int)(x+i+0.5),(int)(y+j+0.5));
			put(c,(int)(x+i+0.5),(int)(y-j+0.5));
		}
	}
	
	public void drawSnowman(char c,int x,int y,int HP) {
		if(HP == 1) {
			drawOval(c,x,y-1,3,2);
		}else if(HP == 2) {
			drawOval(c,x,y-1,5,3);
			drawOval(c,x,y-4,3,2);
			put(' ',x,y+1);
		}else if(HP == 3) {
			drawOval(c,x,y-2,7,4);
			drawOval(c,x,y-5,5,3);
			put(' ',x,y-3);
		}else if(HP == 4) {
			drawOval(c,x,y-3,9,6);
			drawOval(c,x,y-8,7,4);
		}else if(HP >= 5) {
			drawOval(c,x,y-4,12,8);
			drawOval(c,x,y-11,9,6);
		}
	}
	
	public void update() {
		paint();
		clear();
	}

}
