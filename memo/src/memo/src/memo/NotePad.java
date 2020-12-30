package memo;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JSeparator;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

public class NotePad extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuItem mnOpen,mnNew,mnSave,mnPrint,mnExit;
	private JMenuItem mnCancel,mnCut,mnCopy,mnPaste,mnDel,mnAll,mnDate,mnGo;	
	private JCheckBoxMenuItem mnWordWrap,mnState;
	private JTextArea textArea;
	private JToolBar toolBar;
	private JLabel lncol;
	//�ǵ�����(�������)
	private UndoManager undo;
	//�ؽ�Ʈ ������ �����Ǿ����� Ȯ���ϱ� ����
	private int length;
	private String path;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel
					("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					NotePad frame = new NotePad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NotePad() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NotePad.class.getResource("/memo/memoimage.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("�޸���");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\uD30C\uC77C(F)");
		menuBar.add(mnNewMenu);
		
		mnNew = new JMenuItem("\uC0C8\uB85C\uB9CC\uB4E4\uAE30(N)");
		mnNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnNewMenu.add(mnNew);
		
		mnOpen = new JMenuItem("\uC5F4\uAE30(O)...");
		mnOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mnOpen);
		
		mnSave = new JMenuItem("\uC800\uC7A5(S)");
		mnSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(mnSave);
		
		JMenuItem mnReSave = new JMenuItem("\uB2E4\uB978 \uC774\uB984\uC73C\uB85C \uC800\uC7A5(A)");
		mnNewMenu.add(mnReSave);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mnPage = new JMenuItem("\uD398\uC774\uC9C0 \uC124\uC815(U)");
		mnNewMenu.add(mnPage);
		
		mnPrint = new JMenuItem("\uC778\uC1C4(P)");
		mnPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnNewMenu.add(mnPrint);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		mnExit = new JMenuItem("\uB05D\uB0B4\uAE30(X)");
		mnNewMenu.add(mnExit);
		
		JMenu mnNewMenu_1 = new JMenu("\uD3B8\uC9D1(E)");
		mnNewMenu_1.setMnemonic('E');
		menuBar.add(mnNewMenu_1);
		
		mnCancel = new JMenuItem("\uC2E4\uD589\uCDE8\uC18C(U)");
		mnCancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnCancel);
		
		JSeparator separator_2 = new JSeparator();
		mnNewMenu_1.add(separator_2);
		
		mnCut = new JMenuItem("\uC798\uB77C\uB0B4\uAE30(T)");
		mnCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnCut);
		
		mnCopy = new JMenuItem("\uBCF5\uC0AC(C)");
		mnCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnCopy);
		
		mnPaste = new JMenuItem("\uBD99\uC5EC\uB123\uAE30(P)");
		mnPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnPaste);
		
		mnDel = new JMenuItem("\uC0AD\uC81C(L)");
		mnDel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		mnNewMenu_1.add(mnDel);
		
		JSeparator separator_3 = new JSeparator();
		mnNewMenu_1.add(separator_3);
		
		JMenuItem mnFind = new JMenuItem("\uCC3E\uAE30(F)...");
		mnFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnFind);
		
		JMenuItem mnNextFind = new JMenuItem("\uB2E4\uC74C \uCC3E\uAE30(N)");
		mnNextFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnNextFind);
		
		JMenuItem mnChange = new JMenuItem("\uBC14\uAFB8\uAE30(R)...");
		mnChange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnChange);
		
		mnGo = new JMenuItem("\uC774\uB3D9(G)");
		mnGo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnGo);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_1.add(separator_4);
		
		mnAll = new JMenuItem("\uBAA8\uB450 \uC120\uD0DD(A)");
		mnAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnNewMenu_1.add(mnAll);
		
		mnDate = new JMenuItem("\uC2DC\uAC04/\uB0A0\uC9DC(D)");
		mnDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu_1.add(mnDate);
		
		JMenu mnNewMenu_2 = new JMenu("\uC11C\uC2DD(O)");
		mnNewMenu_2.setMnemonic('O');
		menuBar.add(mnNewMenu_2);
		
		mnWordWrap = new JCheckBoxMenuItem("\uC790\uB3D9 \uC904 \uBC14\uAFC8(W)");
		mnWordWrap.setSelected(true);		
		mnNewMenu_2.add(mnWordWrap);
		
		JMenuItem mnFont = new JMenuItem("\uAE00\uAF34(F)...");
		mnNewMenu_2.add(mnFont);		
		
		JMenu mnNewMenu_3 = new JMenu("\uBCF4\uAE30(V)");
		menuBar.add(mnNewMenu_3);
		
		mnState = new JCheckBoxMenuItem("\uC0C1\uD0DC\uD45C\uC2DC\uC904");
		mnState.setEnabled(false);
		mnNewMenu_3.add(mnState);
		
		JMenu mnNewMenu_4 = new JMenu("\uB3C4\uC6C0\uB9D0(H)");
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("\uB3C4\uC6C0\uB9D0 \uBCF4\uAE30");
		mnNewMenu_4.add(mntmNewMenuItem_13);
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("\uBA54\uBAA8\uC7A5 \uC815\uBCF4");
		mnNewMenu_4.add(mntmNewMenuItem_14);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		textArea = new JTextArea();
		//�ǵ�����
		undo=new UndoManager();
		textArea.getDocument().addUndoableEditListener(undo);
		//Ű���� ������
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pos();
			}			
		});
		//���콺 ������
		textArea.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				pos();
			}			
		});
		
			
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 26));
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		toolBar = new JToolBar();	
		toolBar.setVisible(false);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		lncol = new JLabel("Ln:        Col:");
		toolBar.add(lncol);
		//����
		mnOpen.addActionListener(this);
		mnNew.addActionListener(this);
		mnSave.addActionListener(this);
		mnPrint.addActionListener(this);
		mnExit.addActionListener(this);
		//����
		mnCancel.addActionListener(this);
		mnCut.addActionListener(this);
		mnCopy.addActionListener(this);
		mnPaste.addActionListener(this);
		mnDel.addActionListener(this);
		mnAll.addActionListener(this);
		mnDate.addActionListener(this);
		mnGo.addActionListener(this);
		
		//����
		mnWordWrap.addActionListener(this);
		//����
		mnState.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item=(JMenuItem)e.getSource();
		
		if(item==mnNew) { //���θ����
			newfile();
		}else if(item==mnOpen) {	//����
			newOpen();
		}else if(item==mnSave) {	//����
			newSave();
		}else if(item==mnPrint) {
			try {
				textArea.print();
			} catch (PrinterException e1) {				
				e1.printStackTrace();
			}
		}else if(item==mnExit) {//������
			//������ ������ �ִ� ���
			
			//������ ������ ���� ���
			dispose();			
			//System.exit(0);
		}else if(item==mnCancel) {
			undo.undo();
		}else if(item==mnCut) { //�߶󳻱�
			textArea.cut();
		}else if(item==mnCopy) {//����
			textArea.copy();
		}else if(item==mnPaste) {//�ٿ��ֱ�
			textArea.paste();			
		}else if(item==mnDel) {//����
			textArea.replaceSelection("");
		}else if(item==mnAll) { //��μ���
			textArea.selectAll();
		}else if(item==mnDate) {//�ð�/��¥
			SimpleDateFormat sdf=new SimpleDateFormat
					("a h:mm yyyy-MM-dd");
			textArea.replaceSelection(sdf.format(new Date()));
		}else if(item==mnGo) { //�̵�
			go();			
		}else if(item==mnWordWrap) {
			//mnWordWrap �� ���ÿ��θ� Ȯ���� �� 
			//������ �����ϸ� ����ǥ������ �޴��� Ȱ��ȭ ��Ŵ				
			if(!mnWordWrap.isSelected()) {
				mnState.setEnabled(true);
				mnState.setSelected(true);
				//toolbar �����ֱ�
				toolBar.setVisible(true);
			}else {			
				mnState.setEnabled(false);
				mnState.setSelected(false);
				//toolbar ���߱�
				toolBar.setVisible(false);
			}			
		}else if(item==mnState) {
			//����ǥ������ üũ�Ǹ� toolbar �����ֱ�			
			//����ǥ������ üũ �����Ǹ� toolbar ���߱�
			if(mnState.isSelected()) {
				toolBar.setVisible(true);
				pos();
			}else {
				toolBar.setVisible(false);
			}
		}
	}
	
	//���� Ŀ���� ��ġ ���
	private void pos() {
		int pos=textArea.getCaretPosition();
		int line=0,start=0;
		
		try {
				line=textArea.getLineOfOffset(pos)+1;
				start=textArea.getLineStartOffset(line-1);
		} catch (BadLocationException e) {			
			e.printStackTrace();
		}
		lncol.setText("Ln : "+line+", Col : "+(pos-start+1));		
	}	
	private void newfile() {		
		int curlen=textArea.getText().length();
		if(curlen>0) {//������ �ִ� ���
			//������ �Ǿ� �ִ� �������� �ƴ����� ���� �޶���
			if(getTitle().equals("�޸���")) {
				String options[]= {"����","���� �� ��(N)","���"};
				int retval=JOptionPane.showOptionDialog(this, 
						"���� ������ ���� ������ �����Ͻðڽ��ϱ�?",
						"�޸���", JOptionPane.DEFAULT_OPTION,-1,
						null,options,options[0]);
				if(retval==0) {//���� : JFileChooser ����
					JFileChooser chooser=getFileChooser();
					//���� â ����
					retval=chooser.showSaveDialog(this);
					//����ڰ� ��θ� ������ �� ���ϸ��� �ְ� ������ ���� ���
					File f=null;
					if(retval==JFileChooser.APPROVE_OPTION) {
						//����ڰ� ������ ��ο� ���ϸ� ��������
						f=chooser.getSelectedFile();			
						filecheck(f);						
					}
				}else if(retval==1) { //�������
					//textArea �� �ִ� ��� ���� �����
					textArea.setText("");
					setTitle("�޸���");
				}else { //���-�ƹ��͵� ����
				  }
			}
		}else{//������ ���� ���
			
		}
	}//newfile() ����
	private JFileChooser getFileChooser() {
		JFileChooser chooser=new JFileChooser();
		//������ �����Ǵ� �������� �� ��� ���� ���ֱ�
		chooser.setAcceptAllFileFilterUsed(false);
		//���ο� ���� ���� �߰��ϱ�
		chooser.addChoosableFileFilter(new FileNameExtensionFilter
				("�ؽ�Ʈ����(*.txt)","txt"));
		//���� ���� �߿��� ��� ���� �߰��ϱ�
		chooser.addChoosableFileFilter(chooser.getAcceptAllFileFilter());
		//�����̸� ó������ �����ϱ�
		chooser.setSelectedFile(new File("*.txt"));	
		return chooser;
	}
	private void filecheck(File f) {
		int retval=0;
		//���ϸ� Ȯ���ڸ� �����ϰ� �ִ��� �ƴ��� ���ο� ����
		//Ȯ���� �۾� �ʿ�  => endsWith() ������ ������ �κ� Ȯ�ΰ���
		if(!f.getName().endsWith(".txt")) {
			f=new File(f.getPath()+".txt");
		}
		//������ ��ο� ������ ���ϸ��̶�� 
		//���â�� ��� ��� ������,  �ٸ� ���ο� �̸��� ���� ������ �����ؾ���		
		if(f.exists()) {
			retval=JOptionPane.showConfirmDialog(textArea, 
					f.getName()+"�� �̹� �ֽ��ϴ�.\n �ٲٽðڽ��ϱ�?",
					"�ٸ� �̸����� ���� Ȯ��",JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			//retVal==APPROVE_OPTION �� ����
			if(retval==0) {
				save(f);
			}else{//�ٽ� �ѹ� ���� â �����ֱ�
				JFileChooser chooser=getFileChooser();
				chooser.setSelectedFile(new File(""));
				//���� â ����
				retval=chooser.showSaveDialog(this);
				if(retval==0) {
					f=chooser.getSelectedFile();
					filecheck(f);
				}
			}			
		}else {
			save(f);
		}
	}
	private void save(File f) {
		//��������
		try(BufferedWriter writer=new BufferedWriter(new FileWriter(f))) {
			textArea.setText(textArea.getText().replaceAll("\n", "\r\n"));
			writer.write(textArea.getText());
		}catch(Exception e) {
			e.printStackTrace();
		}
		//���� �� textArea �����
		textArea.setText("");
	}	//save ����
	private void newSave() {
		JFileChooser chooser=getFileChooser();
		int retval=chooser.showSaveDialog(this);
		if(retval==JFileChooser.APPROVE_OPTION) {
			//����ڰ� ������ ��ο� ���ϸ� ��������
			File f=chooser.getSelectedFile();
			filecheck(f);
		}
	}
	private void newOpen()	{
		//������ ���� ���
				//����ڰ� ������ ������ �޸��忡 �����ֱ�
				if(textArea.getText().length()==0) {
					//getFileChooser() �޼ҵ� ���
					JFileChooser chooser=getFileChooser();			
					//showOpenDialog() - ����
					int retval=chooser.showOpenDialog(this);
					//����ڰ� ������ ������ �о �޸��忡 �����ֱ�
					File f=null;
					if(retval==JFileChooser.APPROVE_OPTION) {
						f=chooser.getSelectedFile();
						
						try(BufferedReader buf=new BufferedReader(new FileReader(
											f.getPath()))){
							while(buf.readLine()!=null)
								textArea.append(buf.readLine()+"\n");					
						}catch(IOException e) {
							e.printStackTrace();
						}
						//�޸��� Ÿ��Ʋ ����
						setTitle(f.getName());
						//������ ��� ������ �� ����� ������ �ִ��� Ȯ���ϱ� ����
						length=textArea.getText().length();
						path=f.getPath();
					}
				}else	{	//������ �ִ� ���
					if(getTitle().equals("�޸���"))	{	//������->�����ϰ� ���� ����
						newfile();
						newOpen();
					}else	{
						//���� ������ ���� ������ ���
						//���泻���� �����ϰڳĴ� �޼���â ����
						int cur_len=textArea.getText().length();
						if(length>cur_len || length<cur_len)	{
							String options[]= {"����","���� �� ��(N)","���"};
							int retval=JOptionPane.showOptionDialog(this,"���� ������ "+path+"�� �����Ͻðڽ��ϱ�?",
									"�޸���",JOptionPane.DEFAULT_OPTION,-1,null,options,options[0]);
							if(retval==JFileChooser.APPROVE_OPTION)	{//����
								File f=new File(path);
								save(f);
								setTitle("�޸���");
								newOpen();
							}else	{//�������
								textArea.setText("");
								setTitle("�޸���");
								newOpen();
							}
						}else	{	//������ �� �� �������� ���� ���
							textArea.setText("");
							setTitle("�޸���");
							newOpen();
						}
						//�������� ���� ���
						//���� ȭ�� ����
					}
				}
			}//newOpen ����
	private void go() {
		int curpos=textArea.getCaretPosition();
		try {
			//������� Ŀ���� �ִ� ������ ��ȣ�� ���
			int line=textArea.getLineOfOffset(curpos)+1;
			
			//�Է��� �޵��� �Ѵ�.
			String linenum=(String)JOptionPane.showInputDialog(this, "�� ��ȣ(L):",
					"�� �̵�", JOptionPane.CLOSED_OPTION,null,null,line);
			//���ι�ȣ�� �־ �ش� ������ �÷� ������ġ�� �˾Ƴ� ����
			int cur=textArea.getLineStartOffset(Integer.parseInt(linenum)-1);
			//Ŀ���� ���� ��ġ�� �д�
			textArea.select(cur, cur);
			
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}//go ����
}
















