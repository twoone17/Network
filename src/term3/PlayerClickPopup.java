package term3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PlayerClickPopup extends JFrame implements ActionListener{
	JButton stat;
	JButton game;
	
	ClientMainForm client;
	String[] bangjangData;
	JFrame warnFrame;
	
	public PlayerClickPopup (String[] data, ClientMainForm cl) {
		super(data[0] +" 의 방");
		Container c = this.getContentPane();
		
		bangjangData = data;
		client = cl;
		
		Font f3 = new Font("Serif", Font.BOLD, 20);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(true);
		c.setBackground(Color.black);
		c.setLayout(null);
		
		stat = new JButton("stats");
		game = new JButton("1대1 대결");
		stat.setForeground(Color.white);
		stat.setBackground(Color.black);
		game.setBackground(Color.black);
		game.setForeground(Color.white);
		game.setBorderPainted(false);
		game.setFocusPainted(false);
		game.setFont(f3);
		stat.setFont(f3);
		stat.setBorderPainted(false);
		stat.setFocusPainted(false);
		stat.setBounds(100, 30, 150, 60);  
		game.setBounds(100, 110, 150, 60);
		c.add(stat);
		c.add(game);
		
		stat.addActionListener(this);
		game.addActionListener(this);
		
		
	}
	public static void main(String[] args) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==stat) {
        	System.out.println("this is rooom of " + bangjangData[1]);
        	warnFrame = new JFrame();
    		warnFrame.setSize(500, 400);
    		warnFrame.getContentPane().setBackground(Color.red);
    		warnFrame.getContentPane().setLayout(null);
    		JLabel details = new JLabel("<html>player name : " + bangjangData[0] + 
    				"<br/>player nickname : " + bangjangData[1] + 
    				"<br/>birthdate : " + bangjangData[2] +
    				"<br/> Location : " + bangjangData[3] +
    				"<br/> email : jimin_lee98@naver.com"+
    				"<br/> sns : @rastle_fashion"  +
    				"<br/> win : 8" +
    				"<br/> lose : 3" + 
    				"<br/> loginCount : 9" + 
    				"<br/> ip :192.168.172.1 "  +
    				"<br/> currentlogin : 2021-12-15 07:26:24" + "</html>" );
    		details.setBounds(30, 0, 200, 300);
    		details.setForeground(Color.white);
    		warnFrame.getContentPane().add(details);
    		warnFrame.setLocation(0, 0);
    		warnFrame.setLayout(null);
    		warnFrame.setResizable(false);
    		warnFrame.setVisible(false);
        	warnFrame.setVisible(true);
        	
        }

        else if(e.getSource()==game) {
        	try {
        		System.out.println(bangjangData[1] + "과의 대전신청중...");
				client.out.write((Function.GAMESTART + "|" + client.id + "|" + bangjangData[0] + "\n").getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
}