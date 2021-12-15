package term3;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.font.*;

public class Lobby extends JPanel {
    JScrollPane scrollPane;
        // 멤버 필드에 ImageIcon 클래스 생성자  
        ImageIcon bg;
        
        JTable table1, table2;
        DefaultTableModel model1, model2;
        JTextField tf;
        JTextArea ta;
        JButton b1, b2, b3, b4, b5, b6;
        JScrollBar bar;
        JPanel gameRoomList;
        JLabel rank;
        String user;
    	String record = "11전 8승 0무 3패";
    	JLabel myRecord;
    	JLabel myProfile;
    	public void setUser(String s) {
    		user = s;
    		System.out.println(user);
    		revalidate();
        	repaint();
    	}
    public Lobby() {
    	
        bg = new ImageIcon("img/lobby.jpg");

       // 백그라운드 이미지 삽입할 메소드에 이름없는 클래스로 구현
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
            	//백그라운드 사진 구현
                 Dimension d = getSize();
                 g.drawImage(bg.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        //920 * 720
        
        Font titleFont = new Font("210 SOMETIME B", Font.BOLD, 22);
        Font contentFont = new Font("210 SOMETIME B", Font.PLAIN, 18);
        Font chatFont = new Font("210 SOMETIME B", Font.PLAIN, 14);
        Font rankFont = new Font("210 SOMETIME B", Font.PLAIN, 15);
        
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEFT);
        
        Color backgroundColor = new Color(0, 0, 0, 100);
        Color buttonColor = new Color(0, 0, 0, 130);
        
        JPanel ranking = new JPanel();
        gameRoomList = new JPanel();
        JPanel profile = new JPanel();
        JPanel chat = new JPanel();
        
        JLabel participantsTitle = new JLabel("Participants");
        JLabel gameRoomTitle = new JLabel("GameRoom");
        JLabel rankingTitle = new JLabel("Ranking");
        JLabel profileTitle = new JLabel("Profile");
        JLabel chatTitle = new JLabel("Chat");
        
        //Participants
        
        participantsTitle.setForeground(Color.WHITE);
        participantsTitle.setFont(titleFont);
        participantsTitle.setSize(300, 20);
        participantsTitle.setLocation(50, 50);
        panel.add(participantsTitle);

        
        // PLAYER LOGIN  TABLE
        String[] col2 = { "ID", "대화명", "생년월일", "위치" };
        String[][] row2 = new String[0][3];
 
        model2 = new DefaultTableModel(row2, col2);
        table2 = new JTable(model2);
        JScrollPane js2 = new JScrollPane(table2);
        js2.getViewport().setBackground(Color.black);
        JTableHeader header = table2.getTableHeader();
        header.setBackground(Color.black);
        header.setForeground(Color.yellow);
        js2.setBounds(25 ,85, 460, 250);
        table2.setBackground(new Color(0,0,0));
        table2.setForeground(Color.white);
        panel.add(js2);
        
        //Chat
     
        chatTitle.setForeground(Color.WHITE);
        chatTitle.setFont(titleFont);
        chatTitle.setSize(150, 20);
        chatTitle.setLocation(50,370);
        panel.add(chatTitle);
        	//textField
        tf = new JTextField();
        tf.setForeground(Color.WHITE);
        tf.setBackground(Color.black);
        tf.setFont(contentFont);
        tf.setSize(460, 30);
        tf.setLocation(30, 650);
//        tf.addActionListener(event -> { // 텍스트창을 투명하게 하고싶은데 잘 안됨
//        	tf.revalidate();
//        	tf.repaint();
//        });
        panel.add(tf);
        	
        
        //TextArea=
        ta = new JTextArea();
        ta.setEditable(false);
        JScrollPane js3 = new JScrollPane(ta);
        bar = js3.getVerticalScrollBar();
        ta.setForeground(Color.white);
        ta.setBackground(Color.black);
        ta.setFont(chatFont);
        js3.setSize(460, 250);
        js3.setLocation(30, 400);
        panel.add(js3);
        
        
        chat.setLayout(fl);
        chat.setSize(480, 280);
        chat.setLocation(18, 435);
        chat.setBackground(backgroundColor);
        panel.add(chat);
        
        
        
        //Ranking
        rankingTitle.setForeground(Color.WHITE);
        rankingTitle.setFont(titleFont);
        rankingTitle.setSize(150, 40);
        rankingTitle.setLocation(550, 50);
        panel.add(rankingTitle);
        rank = new JLabel("<html>1 : jimin (11전 8승 0무 3패)<br/><br/>2 : euisung (6전 2승 4패)<br/><br/>3 : jungwoong (7전 1승 6패)</html>");
        rank.setForeground(Color.WHITE);
        rank.setFont(rankFont);

        rank.setSize(500, 120);
        rank.setLocation(550, 70);
        panel.add(rank);


        
        ranking.setLayout(fl);
        ranking.setSize(400, 100);
        ranking.setLocation(520, 85);
        ranking.setBackground(backgroundColor);
        panel.add(ranking);
        
        
        //GameRoom
        		//GameRoom Title
        gameRoomTitle.setForeground(Color.WHITE);
        gameRoomTitle.setFont(titleFont);
        gameRoomTitle.setSize(300, 20);
        gameRoomTitle.setLocation(550, 190);
        panel.add(gameRoomTitle);
        		//GameRoomList
        gameRoomList.setLayout(new BoxLayout(gameRoomList, BoxLayout.Y_AXIS));
        gameRoomList.setBackground(backgroundColor);
        
        JScrollPane js4 = new JScrollPane(gameRoomList);
        js4.setBounds(520, 220, 420, 250);
        js4.setBackground(new Color(0,0,0));
        panel.add(js4);
        
        // Profile 
        profileTitle.setForeground(Color.WHITE);
        profileTitle.setFont(titleFont);
        profileTitle.setSize(150, 20);
        profileTitle.setLocation(550, 480);
        panel.add(profileTitle);
        
        
        myProfile = new JLabel("User : " + user);
        myProfile.setForeground(Color.WHITE);
        myProfile.setFont(contentFont);
        myProfile.setSize(300, 20);
        myProfile.setLocation(535, 520);
        panel.add(myProfile);
        
        myRecord = new JLabel("Record : " + record);
        myRecord.setForeground(Color.WHITE);
        myRecord.setFont(contentFont);
        myRecord.setSize(300, 20);
        myRecord.setLocation(535, 565);
        panel.add(myRecord);
        
        profile.setLayout(fl);
        profile.setSize(400, 170);
        profile.setLocation(520, 515);
        profile.setBackground(backgroundColor);
        panel.add(profile);
        
        
        //방 만들기버튼	
        b1 = new JButton("방 만들기");
        b2 = new JButton("입장하기");
        b3 = new JButton("정보보기");
        b4 = new JButton("쪽지보내기");
        b5 = new JButton("일대일게임");
        b6 = new JButton("나가기");
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(3, 2, 5, 5));
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
 
        p.setBounds(500, 500, 300, 300);
        //panel.add(p);
        
        
        panel.setLayout(null);
        setLayout(null);
        add(panel);
        Dimension d = getSize();
        panel.setBounds(0, 0, 1000, 800);
    }
    
    public static void main(String[] args) {
        }
 
}