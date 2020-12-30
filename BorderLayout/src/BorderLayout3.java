import java.awt.*;

class BorderLayout3_sub extends Frame{
	private Button bt1 = new Button("확인");
	private Button bt2 = new Button("취소");
	private Button bt3 = new Button("확인");
	private Button bt4 = new Button("확인");
	private Button bt5 = new Button("확인");
	
	private BorderLayout bl = new BorderLayout();
	private Panel p1 = new Panel();
	private GridLayout gl1 = new GridLayout(1,2);
	
	BorderLayout3_sub(String title){
		super(title);						//이게 있어야 title 제목 생성
	
		this.add("South", p1);
		p1.setLayout(gl1);
		p1.add(bt1);
		p1.add(bt2);
		
		
		super.setSize(400,300);				//실습 창의 사이즈
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//dimension screen이 없으면 10시에 창이 생성
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);			//최대창, 최소창유무
		super.setVisible(true);				//화면에 표시유무
	}
}

public class BorderLayout3{
	public static void main(String[] args) {
		BorderLayout3_sub ex = new BorderLayout3_sub("awt실습3");
	}
}