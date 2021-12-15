package term3;

import java.util.*;
import javax.swing.*;

import fighter.Client4;

//import com.sist.common.Function;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
 
public class ClientMainForm extends JFrame implements ActionListener, Runnable {
    CardLayout card = new CardLayout();
    ClientMainForm cl;
    public String id;
	String name;
	String birthdate;
	String email;
	String sns;
	String win;
	String lose;
	String loginCount;
	String ip;
	String currentlogin;
	
    //Login login = new Login();
    LoginUI loginUI;
    //WaitRoom wr = new WaitRoom();
    Lobby lb = new Lobby();
    // 네트워크
    Socket s; // 전화기
    BufferedReader in; // 수신
    Client4 gclient;// 게임
    public OutputStream out; // 송신
 
    public ClientMainForm() {
    	cl = this;
    	loginUI = new LoginUI(cl);
        setLayout(card);
        //add("LOGIN", login);
        add("LOGINUI", loginUI); 
        add("LB", lb);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
//        login.b1.addActionListener(this);
//        login.b2.addActionListener(this);
        loginUI.loginButton.addActionListener(this);
        lb.tf.addActionListener(this);
    }
 
    public static void main(String[] args) {
        new ClientMainForm();
    }
 
    public void connection(String id, String name, String birthdate, String email, String sns
    		, String win, String lose, String loginCount, String ip, String currentlogin) {
        // 서버연결 => 로그인 요청
        try {
            s = new Socket("localhost",1120); // localhost=> 본인꺼 , 남들꺼는 남들 IP주소 써야함
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();
            // 연결이 되면 로그인 요청
            out.write((Function.LOGIN + "|" + id + "|" + name + "|" + birthdate+ "|" + email
            		+ "|" + sns+ "|" + win+ "|" + lose+ "|" + loginCount+ "|" + ip
            		+ "|" + currentlogin+ "\n").getBytes());
        } catch (Exception ex) {
        }
        // 연결이 되면 지시를 받는다
        new Thread(this).start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginUI.loginButton) { // login action
        	System.out.println("로그인시도");
        }

        else if(e.getSource()==lb.tf)
        {
            // 입력된 데이터 읽기
        	System.out.println("in action");
            String msg=lb.tf.getText();
            if(msg.length()<1) {
            	System.out.println("return");
            	return;}
            
            try {
                out.write((Function.WAITCHAT+"|"+msg+"\n").getBytes());
            }catch(Exception ex) {}
            
            lb.tf.setText("");
        }
 
    }
 
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while (true) {
                String msg = in.readLine();
                StringTokenizer st = new StringTokenizer(msg, "|");
                int protocol = Integer.parseInt(st.nextToken());
                switch (protocol) {
                case Function.LOGIN: {
                    String[] data= {
                            st.nextToken(),
                            st.nextToken(),
                            st.nextToken(),
                            st.nextToken(),
                    };
                    System.out.println(data);
                    NewPlayerRoom pl ;
                    pl = new NewPlayerRoom(data, cl);
                    lb.gameRoomList.add(pl);
                    lb.model2.addRow(data);
                    lb.setUser(data[1]);
                    lb.repaint();
                    lb.revalidate();
                }
                    break;
                case Function.MYLOG: {
                	String tempName = st.nextToken();
                    setTitle(tempName);
                    lb.myProfile.setText("User : " + tempName);
                    lb.myRecord.setText("Record : " + win + "승 " + lose + "패");
                    card.show(getContentPane(), "LB");
                }
                break;
                case Function.WAITCHAT:{
                	//wr.bar.setValue(wr.bar.getMaximum());
                	//wr.ta.append(st.nextToken()+"\n");
                	lb.bar.setValue(lb.bar.getMaximum());
                	String chat = st.nextToken();
                	System.out.println(chat);
                    lb.ta.append(chat+"\n");
                    break;
                }
                case Function.GAMESTART:{ // 게임 신청 handling
                	String opponent = st.nextToken();
                	lb.bar.setValue(lb.bar.getMaximum());
            		lb.ta.append("대전신청이 왔습니다. 신청자 : " + opponent +"\n");
            		GameYesNo ch = new GameYesNo(opponent, cl);
                	break;
                }
                case Function.GAMEBEGIN:{ // 게임 화면을 각각 열어줌
                	String opponent = st.nextToken();
                	
                	System.out.println("my opponent : " + opponent);
                	System.out.println("out socket : " + out);
                	lb.bar.setValue(lb.bar.getMaximum());
                	lb.ta.append("my opponent : " + opponent +"\n");
                	gclient=new Client4("Player : " + name + "VS" + opponent, cl, opponent);
                	gclient.setVisible(true);
                	//gclient.connect();// 각자 게임화면을 서버와 연동
                	break;
                }
                case Function.GAMEING:{ // 게임 본격진행
                	String opp = st.nextToken();
                	System.out.println("move! ");
                	lb.bar.setValue(lb.bar.getMaximum());
                	lb.ta.append("move!"+"\n");
                	//gclient
                	break;
                }

                case Function.MOVERESULT:{ // 게임 본격진행
                	int p1Move = Integer.parseInt(st.nextToken());
                	int p2Move = Integer.parseInt(st.nextToken());
                	System.out.println("my: " + p1Move + " opp: " + p2Move);
                	gclient.result(p1Move, p2Move);
                	break;
                }
                case Function.GARA:{ // 게임 본격진행
                	lb.rank.setText("<html>1 : jimin (12전 9승 0무 3패)<br/><br/>2 : euisung (7전 2승 5패)<br/><br/>3 : jungwoong (7전 1승 6패)</html>");
                	break;
                }
                
                }
 
            }
        } catch (Exception ex) {
        }
 
    }
}