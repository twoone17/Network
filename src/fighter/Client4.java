package fighter;

import java.awt.*;

import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import term3.ClientMainForm;
import term3.Function;

import java.io.*;

import java.awt.event.*;

public class Client4 extends Frame implements ActionListener{

  private TextArea msgView=new TextArea();

  JButton kawi;
private int h1=30;
private int h2=30;

private JButton bawi, bo;        // 가위, 바위, 보에 대한 버튼
JLabel imgLbl3;
ImageIcon bsImg3;
ImageIcon bsImg4;
JLabel imghp;
ImageIcon bshp;
ImageIcon hpImg;
ImageIcon hpImg2;
ImageIcon hpp3;
ImageIcon hpp2;
ImageIcon hpp1;
ImageIcon hpp0;
JLabel hp1;
JLabel hp2;
static JFrame warnFrame = new JFrame();
static JLabel warnLabel = new JLabel();
JButton warnBtn = new JButton("Leave");
JFrame frm;

ClientMainForm me;
String opp;



  public static int KAWI=0;              // 가위를 나타내는 상수

  public static int BAWI=1;              // 바위를 나타내는 상수

  public static int BO=2;                // 보를 나타내는 상수

  Socket socket;

  public Client4(String title, ClientMainForm p1, String p2){            // 생성자
	  
	  me = p1;
	  opp = p2;

	 frm = new JFrame(title);
		warnFrame.setSize(400, 200);
		warnFrame.setLocation(0, 0);
		warnFrame.setLayout(null);
		warnFrame.setResizable(false);
		warnFrame.setVisible(false);
		
		warnLabel.setLocation(10, 10);
		warnLabel.setSize(380, 100);
		warnLabel.setFont(new Font("����ǹ��� �ѳ��� ���ѻ�", Font.PLAIN, 15));
		warnBtn.setSize(100, 50);
		warnBtn.setLocation(150, 100);
		warnBtn.addActionListener(this);
		warnFrame.add(warnLabel);
		warnFrame.add(warnBtn);

		frm.setSize(1000, 750);
	frm.setLocationRelativeTo(null);
	//frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frm.setResizable(false);
	
    msgView.setEditable(false);

    FlowLayout fLay = new FlowLayout();
	frm.getContentPane().setLayout(null);
	Panel panel = new Panel();
	kawi = new JButton("가위");
	bawi = new JButton("바위");
	bo = new JButton("보");
	msgView=new  TextArea();
	add(msgView,"Center");
	panel.add(msgView);
	frm.add(panel,"center");
	
	
	add(msgView,"Center");

	kawi.setBounds(50, 600, 250, 100);
	bawi.setBounds(378, 600, 250, 100);
	bo.setBounds(704, 600, 250, 100);
	msgView.setBounds(50, 500, 900, 100);
	
	frm.getContentPane().add(kawi);
	frm.getContentPane().add(bawi);
	frm.getContentPane().add(bo);
	frm.getContentPane().add(msgView);
	
	hpp2 = new ImageIcon(Game1.class.getResource("../images/hp2.png"));
	hpp1 = new ImageIcon(Game1.class.getResource("../images/hp1.png"));
	hpp0 = new ImageIcon(Game1.class.getResource("../images/hp0.png"));
	
	
	hp1 = new JLabel();
	hpImg = new ImageIcon(Game1.class.getResource("../images/hp3.png"));
	hp1.setIcon(hpImg);
	hp1.setBounds(50,400,250,100);
	hp1.setHorizontalAlignment(JLabel.CENTER);
	frm.getContentPane().add(hp1);
	
	hp2 = new JLabel();
	hpImg2 = new ImageIcon(Game1.class.getResource("../images/hp3.png"));
	hp2.setIcon(hpImg2);
	hp2.setBounds(704,400,250,100);
	hp2.setHorizontalAlignment(JLabel.CENTER);
	frm.getContentPane().add(hp2);
	
	JLabel imgLbl = new JLabel();
	ImageIcon bsImg = new ImageIcon(Game1.class.getResource("../images/achy.png"));
	imgLbl.setIcon(bsImg);
	imgLbl.setBounds(650,100,320,320);
	imgLbl.setHorizontalAlignment(JLabel.CENTER);
	frm.getContentPane().add(imgLbl);
	
	JLabel imgLbl1 = new JLabel();
	ImageIcon bsImg1 = new ImageIcon(Game1.class.getResource("../images/longhair.png"));
	imgLbl1.setIcon(bsImg1);
	imgLbl1.setBounds(25,100,320,320);
	imgLbl1.setHorizontalAlignment(JLabel.CENTER);
	frm.getContentPane().add(imgLbl1);
	frm.setVisible(true);
	
	imgLbl3 = new JLabel();
	bsImg3 = new ImageIcon(Game1.class.getResource("../images/achydamage.png"));
	//imgLbl3.setIcon(bsImg3);
	imgLbl3.setBounds(80,0,835,639);
	
	bsImg4 = new ImageIcon(Game1.class.getResource("../images/longhairdamage.png"));
	
	imghp = new JLabel();
	bshp = new ImageIcon(Game1.class.getResource("../images/achydamage.png"));
	//imgLbl3.setIcon(bsImg3);
	imghp.setBounds(80,0,835,639);
	
	frm.getContentPane().add(imgLbl3);
 
    kawi.addActionListener(this);

    bawi.addActionListener(this);

    bo.addActionListener(this);

 


  }

  public void connect(){

    try{


      //socket=new Socket("127.0.0.1", 7776);
    	System.out.println("connecting..");
    	me.out.write((Function.GAMECONN + "|" + me.id + "|" + opp + "\n").getBytes());
      msgView.append("게임을 시작합니다.\n");

      

      // 소켓의 입·출력 스트림을 얻는다.

//      reader=new DataInputStream(socket.getInputStream());
//
//      writer=new DataOutputStream(socket.getOutputStream());

    }catch(Exception e){

      msgView.append("연결 실패..");

    }

  }

  public void actionPerformed(ActionEvent ae){  // 액션 이벤트 처리

    // 사용자가 선택한 것과 서버가 선택한 것.

    int player=-1, server=-1;         // -1은 선택되지 않은 상태를 나타낸다.
   

    // 사용자가 누른 버튼에 해당하는 값을 기억한다.

    if(ae.getSource()==kawi)
    {
      player=KAWI;
      System.out.println("my move : " + player);
    }
    else if(ae.getSource()==bawi)
    {
      player=BAWI;
      System.out.println("my move : " + player);
    }
    else if(ae.getSource()==bo)
    {
      player=BO;
      System.out.println("my move : " + player);
    }
	String temp = warnBtn.getText();
	if(temp.equals("exit"))
	{
		warnFrame.setVisible(false);
		frm.setVisible(false);
	}

    // 다른 컨트롤에서 발생한 이벤트이면 메소드를 빠져 나온다.

    if(player==-1)return;
    
	try {
		me.out.write((Function.GAMEMOVE + "|" + me.id + "|" + opp + "|" + player +"\n").getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	}
  
  public void result(int player, int server) {
	    if(player==server) //같으면 비긴다
    	{
    	
    	imgLbl3.setIcon(bsImg3);
    	msgView.append("비겼습니다\n");
    	
    	//클라이언트2의 server값(player)과 클라이언트1의 player 값이 일치하면 
    	
    	
    	}//  2       0 
    else if(player>server || server-player==2)//
    	{
    	imgLbl3.setIcon(bsImg4);
    	msgView.append("이겼습니다. \n");
    	h2 = h2 -10;
    	if(h2 ==20)
    	{
    		hp1.setIcon(hpp2);
    	}
    	if(h2 ==10)
    	{
    		hp1.setIcon(hpp1);
    	}
    	if(h2 ==0)
    	{
    		hp1.setIcon(hpp0);
    		msgView.append("player2 승리. \n");
    		warnFrame.setVisible(true);
    		warnFrame.setLocation(500, 300);
    		warnLabel.setText("Player2 Win!");
    		try {
				me.out.write((Function.GARA + "|" + me.id + "|" + opp + "|" +"\n").getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		warnBtn = new JButton("exit");
				
    	}
    
    	}
    else 
    	{
    	imgLbl3.setIcon(bsImg3);
    	msgView.append("졌습니다.\n");
    	h1 = h1 -10;
    	if(h1 ==20)
    	{
    		hp2.setIcon(hpp2);
    	}
    	if(h1 ==10)
    	{
    		hp2.setIcon(hpp1);
    	}
    	if(h1 ==0)
    	{
    		hp2.setIcon(hpp0);
    		msgView.append("player1 승리. \n");
    		warnFrame.setVisible(true);
    		warnFrame.setLocation(500, 300);
    		warnLabel.setText("Player1 Win!");
    		warnBtn = new JButton("exit");
    		
    	}
    	
    
    	}
  }
 

  public static void main(String[] args){

  }

}

 