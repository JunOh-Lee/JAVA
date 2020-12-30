import java.awt.*;

class BorderLayout_sub extends Frame{
	private Button bt1 = new Button("Ȯ��1");
	private Button bt2 = new Button("��ư2");
	private Button bt3 = new Button("��ư3");
	private Button bt4 = new Button("��ư4");
	private Button bt5 = new Button("��ư5");
	
	private BorderLayout bl = new BorderLayout();
	
	BorderLayout_sub(String title){
		super(title);						//�̰� �־�� title ���� ����
		this.add("North", bt1);
		this.add("South", bt2);
		this.add("West", bt3);
		this.add("East", bt4);
		this.add("Center", bt5);
		
		super.setSize(300,400);				//�ǽ� â�� ������
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		//dimension screen�� ������ 10�ÿ� â�� ����
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - super.getHeight()/2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);			//�ִ�â, �ּ�â����
		super.setVisible(true);
	}
}

public class BorderLayout{
	public static void main(String[] args) {
		BorderLayout_sub ex = new BorderLayout_sub("awt�ǽ�");
	}
}