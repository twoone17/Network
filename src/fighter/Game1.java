package fighter;

import  java.awt.*;
import  java.net.*;
import java.util.concurrent.TimeUnit;
import  java.io.*;
import  java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Game1 extends JFrame implements ActionListener

{
	 private final int PORT = 1120;
	ImageIcon bsImg3;
	JLabel imgLbl3;
	  private  TextArea  msgView;
	static String user = ""; //유저입력
	 static String user2 = ""; //유저입력2
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
    private  DataInputStream  reader;
    private  DataOutputStream  writer;
    JFrame frm;

    public static int sis =0;
    public static int rok =1;
    public static int pap =1;
    Socket socket;
    
	public Game1() {
		try {
		
		}catch(Exception e) {}
		start_display();
	
	}
	
	
	public void start_display() { //display 설정
		JFrame frm = new JFrame("격투게임");
		
		frm.setSize(1000, 750);
		
		frm.setLocationRelativeTo(null);
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		
		FlowLayout fLay = new FlowLayout();
		frm.getContentPane().setLayout(null);
		Panel panel = new Panel();
		btn1 = new JButton("공격");
		btn2 = new JButton("방어");
		btn3 = new JButton("잡기");
		msgView=new  TextArea();
		add(msgView,"Center");
		panel.add(msgView);
		frm.add(panel,"center");
		
		
		add(msgView,"Center");
	
		btn1.setBounds(50, 600, 250, 100);
		btn2.setBounds(378, 600, 250, 100);
		btn3.setBounds(704, 600, 250, 100);
		msgView.setBounds(50, 500, 900, 100);
		
		frm.getContentPane().add(btn1);
		frm.getContentPane().add(btn2);
		frm.getContentPane().add(btn3);
		frm.getContentPane().add(msgView);
		
		
		
	
		
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
		
	

		System.out.println(frm.getContentPane().getSize());
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		//msgView.addComponentListener((ComponentListener) this);
		
		imgLbl3 = new JLabel();
		bsImg3 = new ImageIcon(Game1.class.getResource("../images/achydamage.png"));
		//imgLbl3.setIcon(bsImg3);
		imgLbl3.setBounds(80,0,835,639);
		frm.getContentPane().add(imgLbl3);
	
	   
			frm.setVisible(true);
	}
	
	  private  void  connect(){

	        try{
	        	msgView.append("서버와의  연결을  시도합니다.\n");
	            socket=new  Socket("localhost",1120);
	            msgView.append("게임을  시작합니다.\n");

	            //  소켓의  입·출력  스트림을  얻는다.

	            reader=new  DataInputStream(socket.getInputStream());
	            writer=new  DataOutputStream(socket.getOutputStream());

	        }catch(Exception  e){

	        	msgView.append("연결  실패..");


	        }

	    }

	public static void main(String[] args) {
		
	Game1 client = new Game1();
	client.setVisible(true);
	client.connect();
	System.out.println("hi");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int p= -1, serv = -1;
		if(e.getSource()==btn1)
		{
			//imgLbl3.setIcon(bsImg3);
			//msgView.append("User1: ");
			p = rok;
			
			//공격 이벡트
		}
		
		else if(e.getSource()==btn2)
		{
			p = sis;
		
		}
		
		else if(e.getSource()==btn3)
		{
			p= pap;

		}

	/*try {
			   writer.writeUTF("OK");

	            writer.flush();
	            serv=reader.readInt();
		}catch(IOException  ie){} //여기를 hp로 변환
		   if(p==serv) msgView.append("이겼습니다.\n");
	        else  if(p>serv  ||  serv-p==2)msgView.append("이겼습니다.\n");
	        else  msgView.append("졌습니다.\n");*/
		
	}
	
	
}
