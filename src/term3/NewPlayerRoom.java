package term3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;

public class NewPlayerRoom extends JButton implements ActionListener {
	Font titleFont = new Font("210 SOMETIME B", Font.BOLD, 22);
    Font contentFont = new Font("210 SOMETIME B", Font.PLAIN, 18);
    Color buttonColor = new Color(0, 0, 0);
    
    Socket s; // 전화기
    BufferedReader in; // 수신
    OutputStream out; // 송신
    String[] bangjangData;
    ClientMainForm client;
    
	public NewPlayerRoom() { super(); } 

    public NewPlayerRoom(String[] data, ClientMainForm cl) {
	  super(data[1]);
	  
	  bangjangData = data;
	  client = cl;
	  
	  addActionListener(this);
	  // style
	  setForeground(Color.WHITE);
	  setBackground(buttonColor);
	  setBorderPainted(false);
	  setFocusPainted(false);
	  setFont(contentFont);
	  setSize(300, 20);
	  setLocation(30, 100);
	  
	  //socket
	  
;}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		PlayerClickPopup popup = new PlayerClickPopup(bangjangData, client);
		
	} 
    
}
