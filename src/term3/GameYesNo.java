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

public class GameYesNo extends JFrame implements ActionListener{
	JButton yes;
	JButton no;
	
	ClientMainForm client;
	String opponent;
	JFrame warnFrame;
	
	public GameYesNo (String s, ClientMainForm cl) {
		super("대전신청이 왔습니다. 신청자 : " + s);
		Container c = this.getContentPane();
		
		opponent = s;
		client = cl;
		
		Font f3 = new Font("Serif", Font.BOLD, 20);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(200, 200);
		this.setSize(400, 300);
		this.setVisible(true);
		c.setBackground(Color.black);
		c.setLayout(null);
		
		yes = new JButton("수락");
		no = new JButton("거절");
		yes.setForeground(Color.white);
		yes.setBackground(Color.black);
		no.setBackground(Color.black);
		no.setForeground(Color.white);
		no.setBorderPainted(false);
		no.setFocusPainted(false);
		no.setFont(f3);
		yes.setFont(f3);
		yes.setBorderPainted(false);
		yes.setFocusPainted(false);
		yes.setBounds(100, 30, 150, 60);  
		no.setBounds(100, 110, 150, 60);
		c.add(yes);
		c.add(no);
		
		yes.addActionListener(this);
		no.addActionListener(this);
		
		
	}
	public static void main(String[] args) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==yes) {
			System.out.println("YES");
			client.lb.bar.setValue(client.lb.bar.getMaximum());
			client.lb.ta.append("YES" + "\n");
			try {
				client.out.write((Function.GAMEBEGIN + "|" + opponent + "|"+ client.id + "\n").getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }

        else if(e.getSource()==no) {
        	System.out.println("NO");
        	client.lb.ta.append("NO" + "\n");
//        	try {
//				client.out.write((Function.GAMESTART + "|" + client.name + "|" + bangjangData[0] + "\n").getBytes());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        }
	}
}