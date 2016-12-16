import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class Edit extends JPanel implements ActionListener{
	
	JPanel pnlScreen, pnlBtn;
	JButton btnPlay, btnClear, btnSub, btnExit;
	
	public Edit(){
		pnlScreen = new DrawPanel();
		pnlBtn = new JPanel();
		btnPlay = new JButton("Test");
		btnClear = new JButton("Clear");
		btnSub = new JButton("Submit");
		btnExit = new JButton("Exit");
		
		GameFrame.add(this);
		
		this.setLayout(new BorderLayout());
		this.add(pnlScreen, BorderLayout.CENTER);
		pnlBtn.setLayout(new FlowLayout());
		this.add(pnlBtn, BorderLayout.SOUTH);
		
		pnlBtn.add(btnPlay);
		pnlBtn.add(btnClear);
		pnlBtn.add(btnSub);
		pnlBtn.add(btnExit);
		
		btnPlay.addActionListener(this);
		btnClear.addActionListener(this);
		btnSub.addActionListener(this);
		btnExit.addActionListener(this);
		
		this.setVisible(true);
		
		this.revalidate();
		this.repaint();
		System.out.println("In edit mode");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnExit){
			GameFrame.reset();
			GameFrame.add(new MainMenu());
			System.out.println("Going Back");
		}
	}
	
	class DrawPanel extends JPanel{
		public DrawPanel(){
			this.repaint();
		}
		
		public void drawMap(BeatMap beats, Graphics g){
			ArrayList<Note> notes = beats.getMap();
			for(Note n : notes){
				//g.drawRect(x, y, width, 5*n.length);
			}
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawLine(this.getWidth()/4, 0, this.getWidth()/4, this.getHeight());
			g.drawLine(this.getWidth()/4*2, 0, this.getWidth()/4*2, this.getHeight());
			g.drawLine(this.getWidth()/4*3, 0, this.getWidth()/4*3, this.getHeight());
		}
	}

}
