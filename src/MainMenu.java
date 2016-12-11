import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener, MouseMotionListener{	
	JButton[] btnArray = new JButton[4];
	JLabel[] lblArray = new JLabel[4];
	JPanel btnPanel = new JPanel();
	Icon arrow = null;
	
	public MainMenu(){
		try{
			arrow = new ImageIcon("src/Arrow_Right.png");
		}catch(Exception e){
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
		for(int i = 0; i<4; i++){
			lblArray[i] = new JLabel("");
		}
		btnArray[0] = new JButton("How to Play");
		btnArray[1] = new JButton("Player Mode");
		btnArray[2] = new JButton("Creator Mode");
		btnArray[3] = new JButton("EXIT");
		btnPanel.setLayout(null);
		this.add(btnPanel, BorderLayout.CENTER);
		btnPanel.addMouseMotionListener(this);
		for(int i=0; i<4; i++){
			btnArray[i].setLocation(150, 300+(i*55));
			lblArray[i].setLocation(100, 300+(i*55));
			btnArray[i].setSize(150, 50);
			lblArray[i].setSize(50,50);
			lblArray[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			btnArray[i].addActionListener(this);
			btnArray[i].addMouseMotionListener(this);
			btnPanel.add(lblArray[i]);
			btnPanel.add(btnArray[i]);
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnArray[0]){
			GameFrame.clear();
			GameFrame.state="Instructions";
			GameFrame.add(new Instructions());
		}
		else if(arg0.getSource() == btnArray[1]){
			GameFrame.clear();
			GameFrame.state="Game";
			System.out.println("Game Setup");
			Game.GameSetup();
		}
		else if(arg0.getSource() == btnArray[2]){
			GameFrame.clear();
			GameFrame.state="Edit";
			GameFrame.add(new Edit());
		}
		else if(arg0.getSource() == btnArray[3]){
			Main.frame.dispose();
		}
	}
	
	private void setIcons(int n){
		for(int i = 0; i<4; i++){
			if(i == n){
				lblArray[i].setIcon(arrow);
			}
			else{
				lblArray[i].setIcon(null);
			}
			lblArray[i].repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if(arg0.getYOnScreen() >= 485){
			setIcons(3);
		}
		else if(arg0.getYOnScreen() >= 430){
			setIcons(2);
		}
		else if(arg0.getYOnScreen() >= 375){
			setIcons(1);
		}
		else{
			setIcons(0);
		}
	}
}
