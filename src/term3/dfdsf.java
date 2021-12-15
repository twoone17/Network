package term3;

import java.util.*;
import javax.swing.*;
//import com.sist.common.Function;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
 
public class dfdsf extends JFrame implements ActionListener, Runnable {
    CardLayout card = new CardLayout();
    Login login = new Login();
    WaitRoom wr = new WaitRoom();
    Lobby lb = new Lobby();
    
    // 네트워크
    Socket s; // 전화기
    BufferedReader in; // 수신
    OutputStream out; // 송신
 
    public dfdsf() {
        setLayout(card);
        add("LOGIN", login);
        add("WR", wr);
        add("LB", lb);
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        login.b1.addActionListener(this);
        login.b2.addActionListener(this);
        wr.tf.addActionListener(this);
        lb.tf.addActionListener(this);
    }
 
    public static void main(String[] args) {
        new dfdsf();
    }
 
    public void connection(String id, String name, String sex) {
        // 서버연결 => 로그인 요청
        try {
            s = new Socket("localhost",1120); // localhost=> 본인꺼 , 남들꺼는 남들 IP주소 써야함
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();
            // 연결이 되면 로그인 요청
            out.write((Function.LOGIN + "|" + id + "|" + name + "|" + sex + "\n").getBytes());
        } catch (Exception ex) {
        }
        // 연결이 되면 지시를 받는다
        new Thread(this).start();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==login.b1) { // login action
            String id=login.tf.getText();
            String name=login.pf.getText();
            String sex="";
            if(login.rb1.isSelected())
                sex="남자";
            else
                sex="여자";
            connection(id, name, sex);
        }
        else if(e.getSource()==wr.tf)
        {
            // 입력된 데이터 읽기
            String msg=wr.tf.getText();
            if(msg.length()<1) return;
            
            try {
                out.write((Function.WAITCHAT+"|"+msg+"\n").getBytes());
            }catch(Exception ex) {}
            
            wr.tf.setText("");
        }
        else if(e.getSource()==lb.tf)
        {
            // 입력된 데이터 읽기
            String msg=lb.tf.getText();
            if(msg.length()<1) return;
            
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
                            st.nextToken()
                    };
                    System.out.println(data);
                    NewPlayerRoom pl ;
                    //pl = new NewPlayerRoom(data[0], cl);
                    //lb.gameRoomList.add(pl);
                    lb.model2.addRow(data);
                    lb.setUser(data[0]);
                    lb.repaint();
                    lb.revalidate();
                }
                    break;
                case Function.MYLOG: {
                	String tempName = st.nextToken();
                    setTitle(tempName);
                    lb.myProfile.setText("User : " + tempName);
                    card.show(getContentPane(), "LB");
                }
                break;
                case Function.WAITCHAT:{
                	//wr.bar.setValue(wr.bar.getMaximum());
                	//wr.ta.append(st.nextToken()+"\n");
                	lb.bar.setValue(lb.bar.getMaximum());
                    lb.ta.append(st.nextToken()+"\n");
                }
                }
 
            }
        } catch (Exception ex) {
        }
 
    }
}