import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.*;
public class Edit extends JPanel implements ActionListener, KeyListener{
	
	JPanel pnlScreen, pnlBtn;
	JButton btnPlay, btnClear, btnSub, btnExit;
	BeatMap tempBeats = new BeatMap();
	File tempSong;
	
	public Edit(File song, String name){
		tempSong = new File("src/Songs/"+name);
		System.out.println(name);
		Main.frame.setTitle("Rythmn Master - " + name);
		
		try{
			System.out.println(tempSong.mkdirs());
			FileInputStream src = new FileInputStream(song);
			FileOutputStream dest = new FileOutputStream(tempSong+"/"+name+".wav");
			dest.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
			src.close();
			dest.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
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
			int place = this.getWidth()/4;
			for(Note n : notes){
				g.drawRect(place*n.position, 0+5*n.time, place, 5*n.length);
			}
		}
		
		public void add(int x, int y, int pos){
			
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawLine(this.getWidth()/4, 0, this.getWidth()/4, this.getHeight());
			g.drawLine(this.getWidth()/4*2, 0, this.getWidth()/4*2, this.getHeight());
			g.drawLine(this.getWidth()/4*3, 0, this.getWidth()/4*3, this.getHeight());
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {	}

	@Override
	public void keyReleased(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
