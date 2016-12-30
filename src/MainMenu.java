/**
 * The main menu, leading to all other panels
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenu extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener{	
	/**
	 * 
	 */
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
		addKeyListener(this);
		btnPanel.setBackground(Color.WHITE);
		for(int i=0; i<4; i++){
			btnArray[i].setLocation(150, 300+(i*55));
			lblArray[i].setLocation(100, 300+(i*55));
			btnArray[i].setSize(150, 50);
			lblArray[i].setSize(50,50);
			btnArray[i].addActionListener(this);
			btnArray[i].addMouseListener(this);
			btnPanel.add(lblArray[i]);
			btnPanel.add(btnArray[i]);
		}
		
	}
	
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnArray[0]){
			GameFrame.timer.stop();
			GameFrame.clear();
			GameFrame.state="Instructions";
			new Instructions();
		}
		else if(arg0.getSource() == btnArray[1]){
			btnArray[1].setText("Loading...");
			btnArray[1].repaint();
			this.repaint();
			GameFrame.timer.stop();
			GameFrame.clear();
			GameFrame.state="Game";
			System.out.println("Game Setup");
			Game.GameSetup();
		}
		else if(arg0.getSource() == btnArray[2]){
			GameFrame.timer.stop();
			JOptionPane.showMessageDialog(this, "Please Choose a song (Must be in WAV format)");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV File", "wav");
			JFileChooser choose = new JFileChooser();
			choose.setVisible(true);
			choose.setFileFilter(filter);
			choose.setAcceptAllFileFilterUsed(false);
			int result = choose.showOpenDialog(this);
			while((!choose.getSelectedFile().getName().substring(
					choose.getSelectedFile().getName().lastIndexOf(".")+1,
					choose.getSelectedFile().getName().length()).equals("wav") || 
					choose.getSelectedFile() == null) && 
					result == JFileChooser.APPROVE_OPTION){
				result = choose.showOpenDialog(this);
			}
			if(choose.getSelectedFile() != null && result == JFileChooser.APPROVE_OPTION){
				String s = null;
				do{
					if(s != null){
						JOptionPane.showMessageDialog(this, "Invalid Choice - Song already exists");
					}
					s = (String) JOptionPane.showInputDialog(this, "Enter the song's name:\n","Enter Name", JOptionPane.PLAIN_MESSAGE);	
					if(s == null){
						break;
					}
				}while(!(new File("/src/Songs/"+s).mkdirs()));
				new File("/src/Songs/"+s).delete();
				GameFrame.clear();
				GameFrame.state="Edit";
				if(s != null)
					new Edit(choose.getSelectedFile().getAbsoluteFile(),s);
			}
		}
		else if(arg0.getSource() == btnArray[3]){
			GameFrame.timer.stop();
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
		if(arg0.getY() >= 465){
			setIcons(3);
		}
		else if(arg0.getY() >= 408){
			setIcons(2);
		}
		else if(arg0.getY() >= 352){
			setIcons(1);
		}
		else{
			setIcons(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnArray[0]){
			setIcons(0);
		}
		else if(e.getSource() == btnArray[1]){
			setIcons(1);
		}
		else if(e.getSource() == btnArray[2]){
			setIcons(2);
		}
		else if(e.getSource() == btnArray[3]){
			setIcons(3);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {	}

	@Override
	public void mousePressed(MouseEvent e) {	}

	@Override
	public void mouseReleased(MouseEvent e) {	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// This part works now! yay
		System.out.println(arg0.getKeyCode() + "check");
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
