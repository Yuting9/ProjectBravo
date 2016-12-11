import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainMenu extends JPanel implements ActionListener{	
	private static final long serialVersionUID = 1L;
	JButton btnArray[] = new JButton[4];
	JPanel btnPanel = new JPanel();
	
	public MainMenu(){
		this.setLayout(new BorderLayout());
		btnArray[0] = new JButton("How to Play");
		btnArray[1] = new JButton("Player Mode");
		btnArray[2] = new JButton("Creator Mode");
		btnArray[3] = new JButton("EXIT");
		btnPanel.setLayout(null);
		this.add(btnPanel, BorderLayout.CENTER);
		for(int i=0; i<4; i++){
			btnArray[i].setLocation(150, 300+(i*55));
			btnArray[i].setSize(150, 50);
			btnArray[i].addActionListener(this);
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
			System.out.println("magic");
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
}
