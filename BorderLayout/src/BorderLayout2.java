import java.awt.*;

class BorderLayout2_sub extends Frame{
	private Button bt1 = new Button("Ȯ��");
	private Button bt2 = new Button("Ȯ��");
	private Button bt3 = new Button("Ȯ��");
	private Button bt4 = new Button("Ȯ��");
	private Button bt5 = new Button("Ȯ��");
	
	private BorderLayout bl = new BorderLayout();
	
	BorderLayout2_sub(String title){
		super(title);						//�̰� �־�� title ���� ����
		this.add("North", bt1);
		this.add("South", bt2);
		this.add("West", bt3);
		this.add("East", bt4);
		this.add("Center", bt5);
		
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

public class BorderLayout2{
	public static void main(String[] args) {
		BorderLayout2_sub ex = new BorderLayout2_sub("awt�ǽ�2");
	}
}