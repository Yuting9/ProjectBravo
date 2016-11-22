import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MainMenu extends JPanel
{	
	private static final long serialVersionUID = 1L;
	JButton btnArray[] = new JButton[4];
	
	public MainMenu()
	{
		this.setLayout(null);
		ButtonListener btnListener = new ButtonListener();
		btnArray[0] = new JButton("How to Play");
		btnArray[1] = new JButton("Player Mode");
		btnArray[2] = new JButton("Creator Mode");
		btnArray[3] = new JButton("EXIT");
		for(int i=0; i<4; i++)
		{
			btnArray[i].setLocation(150, 300+(i*55));
			btnArray[i].setSize(100, 50);
			btnArray[i].addActionListener(btnListener);
			this.add(btnArray[i]);
		}
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Main.frame.repaint();
			if(arg0.getSource() == btnArray[0])
			{
				GameFrame.clear();
				GameFrame.state="Instructions";
				GameFrame.add(new Instructions());
			}
			else if(arg0.getSource() == btnArray[1])
			{
				GameFrame.clear();
				GameFrame.state="Game";
				Game.GameSetup();
			}
			else if(arg0.getSource() == btnArray[2])
			{
				GameFrame.clear();
				GameFrame.state="Edit";
				GameFrame.add(new Edit());
			}
			else if(arg0.getSource() == btnArray[3])
			{
				Main.frame.dispose();
			}
		}
		
	}
	
	
}
