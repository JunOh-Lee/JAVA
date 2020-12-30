package memo;

import java.io.*;
import java.beans.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.print.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.undo.*;
import javax.swing.border.*;


public class Jmemo extends JFrame implements ClipboardOwner,ActionListener,Printable,UndoableEditListener
{
   Container contentPane;
 private JTextArea ta = new JTextArea(); //������ �ؽ�Ʈ�����
 private JMenuBar mb = new JMenuBar();//�޴���
 private JMenu m1,m2,m3,m4;//�޴�
 private JMenuItem mi11,mi12,mi13,mi14,mi15,mi16,mi17
  ,mi21,mi22,mi23,mi24,mi25,mi26,mi27,mi28,mi29,mi2a,mi2b
  ,mi32
  ,mi41,mi42 ;//�޴� ������
 private JCheckBoxMenuItem mi31;
 
 String st="";
 File file;
 private JOptionPane jOptionPane;
 Object options[]={"��","�ƴϿ�","���"};
 int cnt;

 JViewport viewPort;
 JScrollPane scrollPane;
 
 UndoManager undoManager=new UndoManager();

  public Jmemo(String title)
  {
  super(title);
  
/*  
  try
  {//����� ���� : ������(�����쿡���� ����)
   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  }
  catch(Exception e)
  {
   System.err.println("�ڹ� ����� ���� :"+e.getMessage());
   JOptionPane.showMessageDialog(this,"Windowsȯ�濡���� �����մϴ�."
    ,"�ڹ� ����� ����",JOptionPane.ERROR_MESSAGE);
  }
*/
  try
  {//����� ���� : ũ�ν�
   UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
  }
  catch(Exception e)
  {
   System.err.println("�ڹ� ����� ���� :"+e.getMessage());
     JOptionPane.showMessageDialog(this,"�����Ͻ� ������� �������� �ʽ��ϴ�.","�ڹ� ����� ����",JOptionPane.ERROR_MESSAGE);
  }


  
  Image img=getToolkit().getImage("img/memo.gif");
  setIconImage(img);//������ ������ ����
  addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent we){
           System.exit(0);}});//������ ����� ���α׷� ����   
  
  ta.getDocument().addUndoableEditListener(this);   
  contentPane = getContentPane();  
      scrollPane =new JScrollPane(ta);
      //scrollPane.add(ta);
      viewPort=scrollPane.getViewport();    
      viewPort.add(ta);     
  contentPane.add(scrollPane);//TextArea       
       
      
     
  
  this.setJMenuBar(mb);//�޴���  
 ///////////////////////////////�޴� ����////////////////////////////////////////////// 
  m1 = new JMenu("����");  
  
  mi11 = new JMenuItem("���θ����");
  mi12 = new JMenuItem("����");
  mi13 = new JMenuItem("����");
  mi14 = new JMenuItem("�ٸ��̸���������");
  mi15 = new JMenuItem("����������");
  mi16 = new JMenuItem("�μ�");
  mi17 = new JMenuItem("������");

  mi11.addActionListener(this);//�׼Ǹ����� ���
  mi12.addActionListener(this);
  mi13.addActionListener(this);
  mi14.addActionListener(this);
  mi15.addActionListener(this);
  mi16.addActionListener(this);
  mi17.addActionListener(this);
  
  mi11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
  mi12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
  mi13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
  mi14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
  mi15.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
  mi16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
  mi17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
  
  mb.add(m1);  
  
  m1.add(mi11);
  m1.add(mi12);
  m1.add(mi13);
  m1.add(mi14);
  m1.addSeparator();
  m1.add(mi15);
  m1.add(mi16);
  m1.addSeparator();
  m1.add(mi17);
  
  m2 = new JMenu("����");
  
  mi21 = new JMenuItem("�������");
  mi22 = new JMenuItem(new DefaultEditorKit.CutAction());
      mi22.setText("�߶󳻱�");
      mi23 = new JMenuItem(new DefaultEditorKit.CopyAction());
      mi23.setText("����");
  mi24 = new JMenuItem(new DefaultEditorKit.PasteAction());
      mi24.setText("�ٿ��ֱ�");
  mi25 = new JMenuItem("����");
  mi26 = new JMenuItem("ã��");
  mi27 = new JMenuItem("����ã��");
  mi28 = new JMenuItem("�ٲٱ�");
  mi29 = new JMenuItem("�̵�");
  mi2a = new JMenuItem("��μ���");
  mi2b = new JMenuItem("�ð���¥");
  
  mi21.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
  mi22.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
  mi23.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
  mi24.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
  //mi25.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE));
  mi26.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
  //mi27.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,ActionEvent.META_MASK));
  mi28.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
  mi29.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
  mi2a.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
  //mi2b.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,ActionEvent.META_MASK));
  
  mi21.addActionListener(this);
  mi22.addActionListener(this);
  mi23.addActionListener(this);
  mi24.addActionListener(this);
  mi25.addActionListener(this);
  mi26.addActionListener(this);
  mi27.addActionListener(this);
  mi28.addActionListener(this);
  mi29.addActionListener(this);
  mi2a.addActionListener(this);
  mi2b.addActionListener(this);
  
  mb.add(m2);
  
  m2.add(mi21);
  m2.addSeparator();
  m2.add(mi22);
  m2.add(mi23);
  m2.add(mi24);
  m2.add(mi25);
  m2.addSeparator();
  m2.add(mi26);
  m2.add(mi27);
  m2.add(mi28);
  m2.add(mi29);
  m2.addSeparator();
  m2.add(mi2a);
  m2.add(mi2b);
  
  m3 = new JMenu("����");
  
  mi31 = new JCheckBoxMenuItem("�ڵ��ٹٲ�");
  mi32 = new JMenuItem("�۲�");
  
  mi31.addActionListener(this);
  mi32.addActionListener(this);
  
  mb.add(m3);
  
  m3.add(mi31);
  m3.add(mi32);
  
  m4 = new JMenu("����");
  
  mi41 = new JMenuItem("�����׸�");
  mi42 = new JMenuItem("�޸�������");
  
  mi41.addActionListener(this);
  mi42.addActionListener(this);
  
  mb.add(m4);
  
  m4.add(mi41);
  m4.addSeparator();
  
  m4.add(mi42);  
      
  }
 
 public boolean keyDown(Event e,int key)
 {
  cnt++;
  System.out.println("SD");
  return false;
 }
 
 public void actionPerformed(ActionEvent ae)//�޴� �׼� ó��
 {
  try{
   String gac=ae.getActionCommand();
   if(gac.equals("���θ����"))
   {    
    File f=getFile();
    if(!("".equals(ta.getText()))&&f==null)  
    {
     jOptionPane = new JOptionPane();
     jOptionPane.showOptionDialog(this,getTitle().replaceAll("- �޸���","")+
      " ������ ������ ����� �����ϴ�.\n ����� ������ ���� �Ͻðڽ��ϱ�?",
      "�޸���",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,
        null, options,options[0]);
     if(jOptionPane.getValue().equals(options[0]))
       saveDocument(true);
     else
      ta.setText("");
     /*
     JOptionPane.showOptionDialog(this,getTitle().replaceAll("- �޸���","")+
     " ������ ������ ����� �����ϴ�.\n ����� ������ ���� �Ͻðڽ��ϱ�?",
            "�޸���",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, options,options[0]);
     */
    }   
    else if(cnt>0)
    {
     JOptionPane.showOptionDialog(this,getTitle().replaceAll("- �޸���","")+
     " ������ ������ ����� �����ϴ�.\n ����� ������ ���� �Ͻðڽ��ϱ�?",
            "�޸���",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,null, options,options[0]);
    }
   }
   else if(gac.equals("����"))
   {
    cnt=0;
    ta.setText("");
    openDocument();
   }
   else if(gac.equals("����"))
   {
    saveDocument(false);
   }
   else if(gac.equals("�ٸ��̸���������"))
   {
    saveDocument(true);
   }
   else if(gac.equals("����������"))
   {
      printPage();
   }
   else if(gac.equals("�μ�"))
   {
      printDocument();
   }
   else if(gac.equals("������"))
   {
    System.exit(0);
   }
   else if(gac.equals("�������"))
   {
    undoManager.undo();
   }
   else if(gac.equals("����"))
   {
    ta.replaceRange("",ta.getSelectionStart(), ta.getSelectionEnd());
   }
   else if(gac.equals("ã��"))
   {
    FindDialog fd=new FindDialog(this);
    fd.setBounds(getX()+40,getY()+70,450,150);
    fd.show();    
   }
   else if(gac.equals("�ٲٱ�"))
   {
    ChangeDialog cd=new ChangeDialog(this);
    cd.setBounds(getX()+40,getY()+70,450,200);
    cd.show();      
   }
   else if(gac.equals("�̵�"))
   {
    MoveDialog md=new MoveDialog(this);
    md.setBounds(getX()+40,getY()+70,250,130);
    md.show();  
    int line= md.lineNumber();
    //viewPort.setViewPosition(new Point(0,line));
    ta.setCaretPosition(line);
    //System.out.print(line);
   }   
   else if(gac.equals("�ڵ��ٹٲ�"))
   {
    if(mi31.getState())
     ta.setLineWrap(true);
    else
     ta.setLineWrap(false);
   }
   else if(gac.equals("�۲�"))
   {
    FontDialog ftd=new FontDialog(this);
    ftd.setBounds(getX()+40,getY()+70,530,320);    
    ftd.show(); 
    Font ft= ftd.fontSet();
    ta.setFont(ft);
   }
   else if(gac.equals("��μ���"))
   {    
    ta.selectAll ();
    ta.requestFocus();
   }
   else if(gac.equals("�޸�������"))
   {    
    HelpDialog hd=new HelpDialog(this);
    hd.setBounds(getX()+40,getY()+70,411,313);
    hd.show();
   }
   else if(gac.equals("�����׸�"))
   {
    //String [] cmdar={"/img/hh.exe","/img/notepad.chm","/img/hh.exe"};
    //Process p = Runtime.getRuntime().exec("/img/hh.exe notepad.chm");
    //Process p = Runtime.getRuntime().exec(cmdar);
    //Process p = Runtime.getRuntime().exec("C:/WINNT/help/hh.exe notepad.chm");
    Process p = Runtime.getRuntime().exec("C:/WINNT/notepad.exe"); 
   }
  }catch(Exception e){}
 }
 
 public void openDocument()
 {
   JFileChooser chooser=new JFileChooser();
  System.out.println(chooser.getFileFilter() );
   chooser.setDialogTitle("���� ����");
   int returnVal=chooser.showOpenDialog(this);
   if(returnVal !=JFileChooser.APPROVE_OPTION)//cancel ��ư�� �������� ���
    return;
    File f=chooser.getSelectedFile();//��ȭ���ڿ��� ���õ� ���ϰ�ü �ν��Ͻ��� ���Ѵ�
   
   if(!f.exists())
   {//������ �������� ������ ���� �޼����� ����� ���
     JOptionPane.showMessageDialog(this,file.getName()+" ������ ã�� �� �����ϴ�.",
      "���� ���� ����",JOptionPane.ERROR_MESSAGE);
      return;
   }
  openFile(f);
 }///////////////////////openDocument() ////////////////////
 
 public void openFile(File file)
 {///������ �о�鿩 jta�� ǥ��
  
  BufferedReader in=null;//���� ���� �Է� ��Ʈ��
  //ta.setText("");//�ؽ�Ʈ������ ���� �ʱ�ȭ
  setTitle(file.getName());//������ ������ ���� �̸�����
   try
   {
   in=new BufferedReader(new FileReader(file));
   }
   catch(FileNotFoundException fnfe)
   {//���� �޼���
    System.err.println("���� ���� ���� :"+file.getName()+"������ ã�� �� �����ϴ�.");
    JOptionPane.showMessageDialog(this,file.getName()+"������ ã�� �� �����ϴ�.","���� ���⿡��",JOptionPane.ERROR_MESSAGE);
    return;
   }
   catch(Exception e)
   {
    System.err.println("���� ���� ���� :"+file.getName()+"������ ã�� �� �����ϴ�.");
    JOptionPane.showMessageDialog(this,e.getMessage(),"���� ���⿡��",JOptionPane.ERROR_MESSAGE);
    return;
   }
   
   try
   {////�� �پ� �о string���� �������� ta�� ������
    String string="";    
    while((string=in.readLine())!=null)
    {
     st=st+(string+'\n');
    }
   ta.setText("");
   ta.setText(st);   
   }
   catch(IOException ie)
   {
   System.err.println("���� �б� ���� :"+ie.getMessage());
   }
   try
   {
    in.close();//�Է� ��Ʈ�� �ݴ´�.
   }catch(IOException ie){}
   
   ta.setCaretPosition(0);//Ŀ���� ó������ ��ġ
   viewPort.setViewPosition(new Point(0,0));//������ ������
  System.out.print(file.getName());
   this.file=file;//�о���� ������ file��� �ʵ�� ����
 }//////////////////////////////////////openFile(File file) end/////////////////

 public File getFile()
 {///���� �������� ������ ���Ϸ� �˸�
  return file;
 }
 
 public void saveDocument(boolean isSaveAs)
 {//true�� ���̸����� false�� ����
  if(isSaveAs==true)
  {//���̸����� ������ ��� ���� ��ȭ���� ǥ��
   JFileChooser chooser=new JFileChooser();
   chooser.setDialogTitle("�� �̸����� ����");
   int returnVal=chooser.showSaveDialog(this);
   
    if(returnVal !=JFileChooser.APPROVE_OPTION)
     return;
    File f=chooser.getSelectedFile();
    if(f.exists())
    {//���� �̸��� �̹� �����ϸ� ��� ������ �����
     Object options[]={"��","�ƴϿ�"};
     if(JOptionPane.showOptionDialog(this,"������ �̹� ���� �մϴ�.������?","���",
      JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]) !=0)
       return;//�ƴϿ��� �����ϸ� �������
    }
    saveFile(f);
   }
    else if(isSaveAs==false)
    {//���� ���� �����쿡 ������ ���� �ν��Ͻ��� �����´�.
    File f=getFile();
   if(f==null)
   {
    saveDocument(true);
   }
     if(!f.exists())
     {//������ �̹� �������� ������ ������ ����� ������ �ѹ��� Ȯ�� �Ѵ�.
      Object options[]={"��","�ƴϿ�"};
     if(JOptionPane.showOptionDialog(this,"������ �������� �ʽ��ϴ�.�׷��� ���� �ұ��?","���",
      JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]) !=0)
       return;
     }     
    }
   }/////////saveDocument(boolean isSaveAs) end//////////////
 
 public void saveFile(File file)
 {///////////file ���ڷ� ������ ���Ϸ� ���� ���� ����
  PrintWriter out=null;//���� ��� ��Ʈ��
  
   try
   {
    out=new PrintWriter(new BufferedWriter(new FileWriter(file)));
   }
   catch(IOException ie)
   {
    System.err.println("���� ���� ���� :"+file.getName()+"������ ���� �� �� �����ϴ�.");
    JOptionPane.showMessageDialog(this,file.getName()+"������ ���� �� �� �����ϴ�.",
     "���� ���� ����",JOptionPane.ERROR_MESSAGE);
   }
   
   String string=ta.getText();
   out.print(string);//���� ���Ͽ� ����
   if(out.checkError())
   {//print() �޼����  IOException�� ������ �����Ƿ� ���� �˻縦 �غ��ƾ� �Ѵ�.
     System.err.println("���� ���� ����");
   }
   else
   {
     JOptionPane.showMessageDialog(this,file.getName()+" ������ ���� �Ͽ����ϴ�.","�ȳ� �޼���",JOptionPane.INFORMATION_MESSAGE);
   }
   out.close();//��� ��Ʈ���� �ݴ´�.
   setTitle(file.getName());//���� ���� �̸����� ������ ���� ����
   this.file=file;//���� ����� ���� ��ü �ν��Ͻ��� ��� �ʵ尪���� ����
 }////////////////////////////////////////saveFile(File file) end//////////////////
 
 public void printPage()
 {/////�� ����////
  PrinterJob pj=PrinterJob.getPrinterJob();
  pj.setPrintable(this);
  pj.pageDialog(pj.defaultPage());     
 }///�ʼ��� ��////
 
 public void printDocument()
 {////����Ʈ//////
  try{
   PrinterJob pj=PrinterJob.getPrinterJob();
   pj.setPrintable(this);
   pj.printDialog();
   pj.print();
   }catch(Exception e){}
 }////����Ʈ ��//// 
  
 public int print(Graphics g, PageFormat pf, int pi)throws PrinterException
 {////////Printable ����/////////  
  if (pi >= 1)
  {
   return Printable.NO_SUCH_PAGE;
  }
  Graphics g2=(Graphics2D)g;
  g2.translate((int)pf.getImageableX(),(int)pf.getImageableY());
  this.paint(g2);
  return Printable.PAGE_EXISTS;
  }////////Printable ���� ��/////////
 
 public void lostOwnership(Clipboard cb, Transferable contents){}//ClipboardOwner ����
 
 public void undoableEditHappened(UndoableEditEvent undoe)
 {//UndoableEditListener ����
  if(undoManager !=null)
   undoManager.addEdit(undoe.getEdit());
 }//UndoableEditListener ���� ��   
 
  public static void main(String args[])
 {
  Jmemo jm=new Jmemo("���� ���� - �޸���");
  jm.setBounds(300,200,600,400);
  jm.setVisible(true) ;
 }
}

class FindDialog extends JDialog implements ActionListener
{
  private JLabel fjdl = new JLabel();
  private JTextField jfdt = new JTextField();
  private JButton jdsbn = new JButton();
  private JButton jfdc = new JButton();
  private JPanel jfdp = new JPanel();
  private TitledBorder titledBorder1;
  private JRadioButton jfdup = new JRadioButton();
  private JRadioButton jfddown = new JRadioButton();
  private JCheckBox jfdul = new JCheckBox();

  public FindDialog(Frame parent)
 {
    super(parent,"ã��",false);
  
  setResizable(false);

    titledBorder1 = new TitledBorder(new EtchedBorder(EtchedBorder.RAISED,Color.gray,Color.white),"����");
    jfdc.setBounds(342, 63, 98, 26);
    jfdc.setText("���");
  jfdc.addActionListener(this);
    jdsbn.setBounds(342, 28, 98, 26);
    jdsbn.setText("���� ã��");  
  jdsbn.addActionListener(this);  
  jdsbn.setEnabled(false);//ù ���α׷� ����� ���� ���� �ʰ� ����
  fjdl.setText("ã�� ����:");
    fjdl.setBounds(17, 29, 58, 24);
    this.getContentPane().setLayout(null);
    jfdt.setBounds(94, 29, 235, 21);
    jfdp.setBorder(titledBorder1);
    jfdp.setBounds(128, 59, 200, 45);
    jfdp.setLayout(null);
    jfdup.setText("����");
    jfdup.setBounds(18, 20, 62, 14);
    jfddown.setText("�Ʒ���");
    jfddown.setBounds(108, 20, 62, 14);
    jfdul.setText("��/�ҹ��� ����");
    jfdul.setBounds(20, 75, 101, 26);
    this.getContentPane().add(fjdl, null);
    this.getContentPane().add(jfdt, null);
    this.getContentPane().add(jdsbn, null);
    this.getContentPane().add(jfdc, null);
    this.getContentPane().add(jfdp, null);
    jfdp.add(jfdup, null);
    jfdp.add(jfddown, null);
    this.getContentPane().add(jfdul, null);
  
  //jfdt.requestFocus();
   jfdt.setCaretPosition(0);
  }
 
 public void actionPerformed(ActionEvent ae)
 {
  try{
  String gac=ae.getActionCommand();
  
   if(gac.equals("���"))
   {
    dispose();
   }
   else if(gac.equals("���� ã��"))
   {
   }
  }catch(Exception e){} 
 }  
}

class ChangeDialog extends JDialog implements ActionListener
{
  private JLabel jcdl1 = new JLabel();
  private JLabel jcdl2 = new JLabel();
  private JTextField jcdtf1 = new JTextField();
  private JTextField jcdtf2 = new JTextField();
  private JButton jcdb1 = new JButton();
  private JButton jcdb2 = new JButton();
  private JButton jcdb3 = new JButton();
  private JButton jcdb4 = new JButton();
  private JCheckBox jcdcb = new JCheckBox();

  public ChangeDialog(Frame parent)
 {
    super(parent,"�ٲٱ�",false);
  
  setResizable(false);

    jcdcb.setText("��/�ҹ��� ����");
    jcdcb.setBounds(24, 125, 178, 24);
    jcdb4.setBounds(318, 135, 106, 28);
    jcdb4.setText("���");
    jcdb3.setBounds(318, 102, 106, 28);
    jcdb3.setText("��� �ٲٱ�");

    jcdb2.setBounds(318, 68, 106, 28);
    jcdb2.setText("�ٲٱ�");
    jcdb1.setBounds(318, 35, 106, 28);
    jcdb1.setText("���� ã��");
    jcdtf2.setBounds(100, 75, 197, 22);
    jcdtf1.setBounds(100, 39, 197, 22);
    jcdl2.setText("�ٲ� ����: ");
    jcdl2.setBounds(20, 59, 79, 28);
    jcdl1.setText("ã�� ����: ");
    jcdl1.setBounds(20, 29, 79, 28);
    this.getContentPane().setLayout(null);
    this.getContentPane().add(jcdtf2, null);
    this.getContentPane().add(jcdl1, null);
    this.getContentPane().add(jcdl2, null);
    this.getContentPane().add(jcdtf1, null);
    this.getContentPane().add(jcdb1, null);
    this.getContentPane().add(jcdb2, null);
    this.getContentPane().add(jcdb3, null);
    this.getContentPane().add(jcdb4, null);
    this.getContentPane().add(jcdcb, null);
  }
 
 public void actionPerformed(ActionEvent ae)
 {
  dispose();
 }  
}

class MoveDialog extends JDialog implements ActionListener
{
 private JTextField jmdt = new JTextField();
 private JButton jmdb1 = new JButton();
 private JButton jmdb2 = new JButton();
 String getline   = new String();
 
 public MoveDialog(Frame parent)
 {
    super(parent,"�̵�",true);
  
  jmdt.setBounds(14, 21, 115, 26);
    this.getContentPane().setLayout(null);
    jmdb1.setBounds(143, 16, 87, 29);
    jmdb1.setText("Ȯ��");
  jmdb1.addActionListener(this);
    jmdb2.setBounds(144, 60, 87, 29);
    jmdb2.setText("���");
  jmdb2.addActionListener(this);
    this.getContentPane().add(jmdb1, null);
    this.getContentPane().add(jmdb2, null);
    this.getContentPane().add(jmdt, null);  
 }
 
 public int lineNumber()
 {  
  return Integer.parseInt(getline);
 }
 
 public void actionPerformed(ActionEvent ae)
 {
  try{
   String gac=ae.getActionCommand();
   
   if(gac.equals("Ȯ��"))
   {    
    getline=jmdt.getText();  
    dispose();
   }
   else if(gac.equals("���"))
   {
    dispose();
   }
  }catch(Exception e){}
 } 
}

class FontDialog extends Dialog implements ActionListener
{
  private Label jfdl1 = new Label();
  private Label jfdl2 = new Label();
  private Label jfdl3 = new Label();
  private Label jfdl4 = new Label();
  private Label jfdl5 = new Label();
  private Label jfdl6 = new Label();
  private TextField jfdtf1 = new TextField();
  private TextField jfdtf2 = new TextField();
  private TextField jfdtf3 = new TextField();
  private List jfdls1 = new List();
  private List jfdls2 = new List();
  private List jfdls3 = new List();
  private Choice jfdcb = new Choice();
  private Button jfdb1 = new Button();
  private Button jfdb2 = new Button();
  private TextField jfdtf4 = new TextField();
  Graphics g;
 
 Font f;
 String fontname="SansSerif";
 int fontstyle=Font.PLAIN;
 int size=8;
 
  String [] allFonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
  private String [] allSizes = {"8","9","10","11","12","14","16","18","20","22","24","26","28","36","48","72"};
  private String [] allStyle ={"����","����Ӳ�","����","���� ����Ӳ�"};
  FontDialog(Frame parent) 
 {
    super(parent,"�۲�",true);
  
  setResizable(false);
  addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){dispose();}});
      setLayout(null);
  
  for(int i=0;i<allFonts.length;i++)
  {
  jfdls1.add(allFonts[i]);
  }
  
  for(int i=0;i<16;i++)
  {
   jfdls3.add(allSizes[i]);
  }  
  
  for(int i=0;i<4;i++)
  {
   jfdls2.add(allStyle[i]);
  }
    jfdl1.setText("�۲�");
    jfdl1.setBounds(10, 24, 98, 26);
    jfdl2.setText("�۲� ��Ÿ��");
    jfdl2.setBounds(198, 26, 98, 26);
    jfdl3.setText("ũ��");
    jfdl3.setBounds(340, 24, 98, 26);
    jfdl4.setText("����");
  jfdl4.setBounds(266,157,49,25);
  jfdl5.setText("��ũ��Ʈ");
    jfdl5.setBounds(266, 236, 98, 25);
    jfdtf1.setBounds(10, 49, 181, 24);
    jfdtf2.setBounds(201, 49, 129, 24);
    jfdtf3.setBounds(340, 49, 91, 24);
    jfdls1.setBounds(10, 77, 181, 79);
  jfdls1.addActionListener(this);
    jfdls2.setBounds(201, 77, 129, 79);
  jfdls2.addActionListener(this);
    jfdls3.setBounds(340, 77, 91, 79);
  jfdls3.addActionListener(this);
    jfdcb.setBounds(258, 263, 180, 22);
    jfdb1.setBounds(436, 51, 82, 28);
    jfdb1.setLabel("Ȯ��");
  jfdb1.addActionListener(this);
    jfdb2.setBounds(436, 85, 82, 28);
    jfdb2.setLabel("���");
  jfdb2.addActionListener(this);

  add(jfdcb);
  add(jfdl4);
  add(jfdl5);
  add(jfdl1);
  add(jfdtf1);
  add(jfdls1);
  add(jfdb1);
  add(jfdb2);
  add(jfdls3);
  add(jfdl3);
  add(jfdtf3);
  add(jfdls2);
  add(jfdtf2);
  add(jfdl2);  
  
  jfdl6.setBounds(212, 190,220,40);
  jfdl6.setText("������AaBbYyZz");
  add(jfdl6);
 }
 
 public void paint(Graphics g)
 {
  g.setColor(Color.lightGray);
  g.draw3DRect (202, 182, 232, 50,false);
  g.setColor(Color.black);  
 }
 
 public Font fontSet()
 {
  jfdls1.getSelectedItem();
  fontname=jfdls1.getSelectedItem();
  
  if(jfdls2.getSelectedItem().equals("����"))
   fontstyle=Font.PLAIN;
  if(jfdls2.getSelectedItem().equals("����Ӳ�"))
   fontstyle=Font.ITALIC;   
  if(jfdls2.getSelectedItem().equals("����"))
   fontstyle=Font.BOLD;
  if(jfdls2.getSelectedItem().equals("���� ����Ӳ�"))
   fontstyle=Font.ITALIC+Font.BOLD;
  
  jfdtf3.setText(jfdls3.getSelectedItem());   
   size=Integer.parseInt(jfdls3.getSelectedItem());
  
  return f=new Font(fontname,fontstyle,size);
 }
  
 public void actionPerformed(ActionEvent ae)
 {
  if((ae.getSource()).equals(jfdls1))
  {
   jfdtf1.setText(jfdls1.getSelectedItem());
   fontname=jfdls1.getSelectedItem();
   f=new Font(fontname,fontstyle,size);
   jfdl6.setFont(f); 
  }
  else if((ae.getSource()).equals(jfdls2))
  {
   jfdtf2.setText(jfdls2.getSelectedItem());
   
   if(jfdls2.getSelectedItem().equals("����"))
    fontstyle=Font.PLAIN;
   if(jfdls2.getSelectedItem().equals("����Ӳ�"))
    fontstyle=Font.ITALIC;   
   if(jfdls2.getSelectedItem().equals("����"))
    fontstyle=Font.BOLD;
   if(jfdls2.getSelectedItem().equals("���� ����Ӳ�"))
    fontstyle=Font.ITALIC+Font.BOLD;
  
   f=new Font(fontname,fontstyle,size);
   jfdl6.setFont(f);   
  }
  else if((ae.getSource()).equals(jfdls3))
  {
   jfdtf3.setText(jfdls3.getSelectedItem());   
   size=Integer.parseInt(jfdls3.getSelectedItem());
   f=new Font(fontname,fontstyle,size);
   jfdl6.setFont(f);  
  }
  else if((ae.getSource()).equals(jfdb1))
  {
   dispose();
  }
  else if((ae.getSource()).equals(jfdb2))
  {
   dispose();
  }
 }  
}

class HelpDialog extends Dialog implements ActionListener
{//���� ��â
 Button ok;//������ â�� ������ ��ư
 Image img=getToolkit().getImage("img/memo.gif");
 Image mh=getToolkit().getImage("img/mh.gif");
 Image minfo=getToolkit().getImage("img/minfo.gif");
 Font f=new Font("����",Font.PLAIN,12);
 
 HelpDialog(Frame parent)
 {
  super(parent,"�޸��� ����",true);
  
  setResizable(false);  
  
  setLayout(null);
  ok=new Button("Ȯ ��");
  ok.addActionListener(this);
  ok.setBounds(290,280,90,25);  
  add(ok);    
    
  addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent we){dispose();}}); 
 } 
 
 public void paint(Graphics g)
 {
  Insets insets=getInsets();  
  
  g.setFont(f);
  g.drawImage(minfo,insets.left,insets.top,410,77,this);
  g.drawImage(mh,15,105,32,30,this);  
  g.drawString("Wicrosoft (R) �޸���",66,120);
  g.drawString("���� 1.0 (���� 2195: Service Pack 3)",66,137);
  g.drawString("Copyright (C) 1981-1999 Wicrosoft Corp.",66,153);
  g.drawString("�� ��ǰ�� ���� ����ڿ��� ����� �㰡�Ǿ����ϴ�.",66,200);
  g.drawString("jdk �� ����",66,216);
  g.setColor(Color.lightGray);
  g.draw3DRect (66,248,334,1,false);
  g.setColor(Color.black);
  String p= Runtime.getRuntime().totalMemory()/1024+"";   
  g.drawString("Virtual Machine���� ����� �� �ִ� ���� �޸�: "+p ,66,265); 
 }
 
 public void actionPerformed(ActionEvent ae)
 {
  dispose();
 }  
}

 

 

