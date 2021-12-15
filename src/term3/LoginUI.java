package term3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.net.*;



public class LoginUI extends JPanel {
   
   public static final int DEFAULT = 0;
   public static final int LOGINCHECK = 1;
   public static final int CREATEACCOUNT = 2;
   public static final int REDUNTCHECK = 3;
   static InetAddress ip;
   public static final int port_num = 9999;
   JButton loginButton;
   ClientMainForm client;
   Scanner in;
    PrintWriter out;
    {try {
      ip = InetAddress.getLocalHost();
      }
   catch(UnknownHostException e) {
   }
      
   }

    String url = "jdbc:mariadb://localhost/tp";
   String user_name = "root";
   String password = "jm0185++";
   
   String hey;
   String what;
   String col;
   String username;
   String nickname;
   String name;
   String passwd;
   String email;
   String birthdate;
   String namecheck;
   int check = 0;
   int logincount = 0;
   int mode = DEFAULT;
   
   int checkboxnum;
   int adminLog = 0;
   

    
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   Scanner sc = new Scanner(System.in);
   
   static JFrame warnFrame = new JFrame();
   static JLabel warnLabel = new JLabel();
   JButton warnBtn = new JButton("Got it");
   
   JFrame createFrame = new JFrame("Create Account");
   JPanel createPanel = new JPanel();

   JButton [] newBtn = new JButton[4];
   JLabel [] newLabel = new JLabel[7];
   JTextField [] newField = new JTextField[7];

   JPasswordField pwField = new JPasswordField();
   JTextField idField = new JTextField();
   JRadioButton rb1, rb2;
   
   
   Dimension dim_Frame = new Dimension(400,800);
   Dimension dim_Label = new Dimension(300,30);
   Dimension dim_Field = new Dimension(300,40);
   Dimension dim_Button = new Dimension(300,70);
   Dimension dim_SButton = new Dimension(150,70);
   
   static LoginUI login = null;
   /**
    * Launch the application.
    * @throws IOException 
    */

   /**
    * Create the frame.
    */
   public LoginUI(ClientMainForm cl) {
      client = cl;
      newLabel[0] = new JLabel("UserName");
      newField[0] = new JTextField();
      newLabel[1] = new JLabel("Password(should be longer than 7)");
      newField[1] = new JTextField();
      newLabel[2] = new JLabel("Rewrite Password");
      newField[2] = new JTextField();
      newLabel[3] = new JLabel("Name");
      newField[3] = new JTextField();
      newLabel[4] = new JLabel("Nickname");
      newField[4] = new JTextField();
      newLabel[5] = new JLabel("Birth Date (ex.19981925)");
      newField[5] = new JTextField();
      newLabel[6] = new JLabel("E-mail");
      newField[6] = new JTextField();
      newBtn[0] = new JButton("Save");
      newBtn[1] = new JButton("Cancel");
      newBtn[2] = new JButton(" ߺ Ȯ  ");
      newLabel[0].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[1].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[2].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[3].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[4].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[5].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newLabel[6].setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 12));
      newField[0].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[1].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[2].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[3].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[4].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[5].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      newField[6].setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 12));
      
      
        
         
      warnFrame.setSize(400, 200);
      warnFrame.setLocation(0, 0);
      warnFrame.setLayout(null);
      warnFrame.setResizable(false);
      warnFrame.setVisible(false);
      
      warnLabel.setLocation(10, 10);
      warnLabel.setSize(380, 100);
      warnLabel.setFont(new Font("    ǹ     ѳ       ѻ ", Font.PLAIN, 15));

      warnBtn.setSize(100, 50);
      warnBtn.setLocation(150, 100);
      warnBtn.addActionListener(new ButtonAction());
      
      
      warnFrame.add(warnLabel);
      warnFrame.add(warnBtn);

      
      //setSize(950,800);
       //setResizable(false);
       //setLocation(360,20);
       //setVisible(true);
      setLayout(null);
      
      idField.setSize(350, 50);
      idField.setLocation(350, 200);
      idField.setFont(new Font("    ǹ     ѳ       ѻ ", Font.BOLD, 40));
      add(idField);
      pwField.setSize(350,50);
      pwField.setLocation(350, 300);
      pwField.setFont(new Font(" ü ",Font.BOLD, 40));
      add(pwField);
      
      //login버튼 창-----------------
      loginButton = new JButton("Log in",new ImageIcon(LoginUI.class.getResource("loginbutton.jpg")));
      
       loginButton.setBorderPainted(false);
       loginButton.setContentAreaFilled(false);
       loginButton.setFocusPainted(false);
       loginButton.setBounds(500, 500, 242, 116);
       add(loginButton);
       
       JButton joinButton = new JButton("Create Account",new ImageIcon(LoginUI.class.getResource("joinbutton.jpg")));
       
       joinButton.setBorderPainted(false);
      joinButton.setContentAreaFilled(false);
      joinButton.setFocusPainted(false);
      joinButton.setBounds(150, 500, 243, 116);
      add(joinButton);
      
      JLabel mainBG = new JLabel("");
      mainBG.setBounds(0, 0, 1000, 800);
      add(mainBG);
      mainBG.setIcon(new ImageIcon(LoginUI.class.getResource("login.jpg")));
      
      loginButton.addActionListener(new ButtonAction());
      joinButton.addActionListener(new ButtonAction());
      newBtn[0].addActionListener(new ButtonAction());
      newBtn[1].addActionListener(new ButtonAction());
      
//      rb1=new JRadioButton("남자");
//         rb2=new JRadioButton("여자");
//         rb1.setBounds(350, 500, 70, 30);
//         rb2.setBounds(420, 500, 70, 30);
//         getContentPane().add(rb1);
//         getContentPane().add(rb2);
      
   }
   class ButtonAction implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         JButton myButton = (JButton)e.getSource();
         String temp = myButton.getText();

         warnFrame.setVisible(false);

         if(temp.equals("Log in")) {
            username = idField.getText();
            passwd = new String(pwField.getPassword());
            idField.setText("");
            pwField.setText("");

            System.out.println("Login  " + username + " " + passwd);
            //Client.out.println("LOGINCHECK|" + username +"|"+passwd);
            mode = LOGINCHECK;
            command();
            //login.setVisible(false);
            
         }
         
         else if(temp.equals("Create Account")) {
            mode = CREATEACCOUNT;
            
            createPanel.setLayout(null);
            createFrame.setSize(dim_Frame);
            createFrame.add(createPanel);
            createFrame.repaint();
            createFrame.setVisible(true);
            
            
            int x = 50, y = 10;

            for(int i =  0; i< newLabel.length ; i++) {
               newLabel[i].setLocation(x, y);
               newLabel[i].setSize(dim_Label);
               createPanel.add(newLabel[i]);
               y += 30;
               newField[i].setLocation(x, y);
               newField[i].setSize(dim_Field);
               createPanel.add(newField[i]);
               y += 40;
               

            }

            for(int i = 0; i < newBtn.length -2 ; i++) {
               newBtn[i].setLocation(x, y);
               newBtn[i].setSize(dim_SButton);
               newBtn[i].addActionListener(this);
               createPanel.add(newBtn[i]);
               x += 150;
            }
            
         }


         else if(temp.equals("Save")) {            
            int admission = 1;
            String warnMessage = "";
            
            if(!newField[1].getText().equals(newField[2].getText()) ) {
               warnMessage = "Rewrite Password.";
               admission = 0;
            }
            else if(newField[1].getText().length() < 8) {
               warnMessage = "password too short.";
               admission = 0;

            }

            else {
               for(int i = 0; i < newField.length; i++) {
                  if(newField[i].getText().length()==0) {
                     warnMessage = warnMessage + " " + newField[i].getText(); 
                     admission = 0;

                  }
               }
               if(admission == 0) warnMessage = "All data should be filled.";
            }
            if(admission == 0) {
               warnLabel.setText(warnMessage);
               warnFrame.setVisible(true);
            }
            else {
               System.out.println("new account added");
               username = newField[0].getText();
               passwd = newField[1].getText();
               name = newField[3].getText();
               nickname = newField[4].getText();
               birthdate = newField[5].getText();
               email = newField[6].getText();
               mode = CREATEACCOUNT;
               //Client.out.println("CREATEACCOUNT|"+username+"|"+passwd+"|"+name+"|"+nickname+"|"+birthdate+"|"+email);
               command();
               
               warnLabel.setText("Account created! Login with your Account!");
               warnFrame.setVisible(true);
               createFrame.remove(createPanel);
               createFrame.revalidate();
               createFrame.repaint();
               mode = DEFAULT;
            
            }
         }
         
         else if(temp.equals("Got it")){
            warnFrame.setVisible(false);
            createFrame.setVisible(false);
            
         }
         else if(temp.equals("Cancel")) {
            remove(createFrame);
            createFrame.setVisible(false);
            
            for(int i = 0; i < newField.length; i++) {
               newField[i].setText("");
            }
            
            mode = DEFAULT;
         }
         
      }
   }
   
   public static void main(String[] args) throws IOException {
//      JFrame jf = new JFrame();
//       jf.setSize(1000,1000);
//       login = new LoginUI(); //Ŭ   ̾ Ʈ      ϰ  
//       jf.add(login);
//        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //      ݴ°  
//        jf.setVisible(true);//    ̰     ְ  
   }


public void command() {
   try {

	   Class.forName("org.mariadb.jdbc.Driver"); // JDBC 드라이버 로드
      System.out.println("드라이버 연결 성공!" + mode);

      conn = DriverManager.getConnection(url,user_name,password);
      System.out.println("데이터베이스 연결 성공!");

      stmt = conn.createStatement();


      String useXproject = "use tp";
      stmt.executeUpdate(useXproject);

      if(mode == LOGINCHECK) {
         col = "username";
         what = username;
         //username으로 검색하기
         String search = "select * from user2 where " + col + " like '" + what +"';";
         //username으로 받은 데이터가 rs에 저장되고 그 내용을 확인!
         rs = stmt.executeQuery(search);
         if(rs.next()) {
            if(passwd.equals(rs.getString("passwd"))){
   
               System.out.println("login succeeded");
               warnFrame.setVisible(true);
               warnLabel.setText("login succeeded.");
                  client.id=rs.getString("name");
                  client.name=rs.getString("nickname");
                  client.birthdate=rs.getString("birthdate");
                  client.email=rs.getString("email");
                  client.sns=rs.getString("sns");
                  client.win=rs.getString("win");
                  client.lose=rs.getString("lose");
                  client.loginCount=rs.getString("loginCount");
                  client.ip=rs.getString("ip");
                  client.currentlogin=rs.getString("currentlogin");
                  
                 // String logincount=rs.getString("loginCount");
                  int logincount = rs.getInt("loginCount");
                  System.out.println(client.id);
                  System.out.println(client.name);
                  System.out.println(client.birthdate);
                  System.out.println(client.email);
                  System.out.println(client.sns);
                  System.out.println(client.win);
                  System.out.println(client.lose);
                  System.out.println(client.loginCount);
                  System.out.println(client.ip);
                  System.out.println(client.currentlogin);
                  
                  String ipadd = ip.getHostAddress();
                  logincount = logincount+1;
                  
                  String now = "update user2 set currentlogin = now() where username = '" + what + "';";
                  String addip = "update user2 set ip = '" + ipadd + "' where username = '" + what + "';";
                  String count = "update user2 set loginCount = '" + logincount  + "' where username = '" + what + "';";
                  //String addcount = "update user2 set loginCount = " + loginCount + " where username = '" + what + "';";
               System.out.println(addip);
               stmt.executeUpdate(addip);
               stmt.executeUpdate(count);
               stmt.executeUpdate(now);
               
                  client.connection(client.id, client.name, client.birthdate,
                		  client.email,client.sns,client.win,client.lose
                		  ,client.loginCount,client.ip,client.currentlogin);
               }
            
            
            else {
               System.out.println("login failed");
               warnFrame.setVisible(true);
               warnLabel.setText("Password is wrong.");
            }
         }
         else {
            System.out.println("login failed");
            warnFrame.setVisible(true);
            warnLabel.setText("UserName does not exist.");


         }
   }

      else if(mode == CREATEACCOUNT) {

         String adduser = "insert into user2(username, passwd, name,nickname, birthdate, email) "
               + "values('"+ username + "', '" + passwd + "', '"
               + name + "','" + nickname + "', '" + birthdate + "', '" + email + "');";
         System.out.println(adduser);
         stmt.executeUpdate(adduser);
      }
      
      else if(mode == REDUNTCHECK) { 
         String search = "select * from user where username like '" + namecheck +"';";
         rs = stmt.executeQuery(search);
         if(rs.next()) {
            System.out.println(rs.getString("username"));
            check = 0;
         }
         
         else {
            System.out.println("check changed");
            check = 1;
         }
      }
      
      
   

   }
   catch(ClassNotFoundException e) {
      e.printStackTrace();
   }
   catch(SQLException e) {
      e.printStackTrace(); 
   }
}
public void usermode()
{
   createPanel.setLayout(null);
   createFrame.setSize(1000,1000);
   createFrame.setVisible(true);
   JLabel mainBG = new JLabel("");
   mainBG.setBounds(0, 0, 950, 800);
   add(mainBG);
}

}