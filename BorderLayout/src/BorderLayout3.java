import java.awt.*;

class BorderLayout3_sub extends Frame{
	private Button bt1 = new Button("Ȯ��");
	private Button bt2 = new Button("���");
	private Button bt3 = new Button("Ȯ��");
	private Button bt4 = new Button("Ȯ��");
	private Button bt5 = new Button("Ȯ��");
	
	private BorderLayout bl = new BorderLayout();
	private Panel p1 = new Panel();
	private GridLayout gl1 = new GridLayout(1,2);
	
	BorderLayout3_sub(String title){
		super(title);						//�̰� �־�� title ���� ����
	
		this.add("South", p1);
		p1.setLayout(gl1);
		p1.add(bt1);
		p1.add(bt2);
		
		
		super.setSize(400,300);				//�ǽ� â�� ������
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//dimension screen�� ������ 10�ÿ� â�� ����
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);			//�ִ�â, �ּ�â����
		super.setVisible(true);				//ȭ�鿡 ǥ������
	}
}

public class BorderLayout3{
	public static void main(String[] args) {
		BorderLayout3_sub ex = new BorderLayout3_sub("awt�ǽ�3");
	}
}