package term3;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeNewRoom extends JFrame {
	private String name;
	private JPanel roomList;
	
	public String getName() {
		return name;
	}
	public MakeNewRoom(JPanel room, Lobby frame) {
		roomList = room;
		
        setTitle("Create New Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Font roomFont = new Font("LTYPE", Font.PLAIN, 20);
        Color buttonColor = new Color(0, 0, 0, 130);
        
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE); 
        contentPane.setLayout(new FlowLayout()); 
        
        JButton createBtn = new JButton("Create");
        JLabel title = new JLabel("Room Name");
        JTextField roomName = new JTextField(20);
        contentPane.add(title);
        contentPane.add(roomName); 
        contentPane.add(createBtn);
        
        createBtn.addActionListener(event -> {
        	name = roomName.getText();
        	JButton newRoom = new JButton(name);
        	newRoom.setSize(480, 20);
        	newRoom.setFont(roomFont);
        	newRoom.setOpaque(false);
        	newRoom.setForeground(Color.WHITE);
        	newRoom.setBackground(buttonColor);
        	newRoom.setBorderPainted(false);
        	newRoom.setFocusPainted(false);
        	newRoom.setFocusable(false);
        	
        	newRoom.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseEntered(MouseEvent e){
                	frame.revalidate();
                	frame.repaint();
                    
                }
                @Override
                public void mouseExited(MouseEvent e){
                	frame.revalidate();
                	frame.repaint();
                }
            });
        	roomList.add(newRoom);
        	frame.revalidate();
        	frame.repaint();
        	dispose();
        });
        setSize(300, 150); 
        setVisible(true); 
        
    }
}
