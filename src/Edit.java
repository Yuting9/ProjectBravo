/**
 * Creates the beatmap from user input
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.*;
public class Edit extends JPanel implements ActionListener, KeyListener{
	
	JPanel pnlScreen, pnlBtn;
	JButton btnPlay, btnClear, btnSub, btnExit;
	BeatMap tempBeats = new BeatMap();
	FileInputStream src;
	FileOutputStream fileOut, dest;
	ObjectOutputStream outStream;
	File tempSong, beatPlace;
	boolean bd = false, bf = false, bj = false, bk = false;
	int ind = 0,inf = 0,inj = 0,ink = 0;
	int bed = 0,bef = 0,bej = 0,bek = 0, totime = 0;
	Timer time = new Timer(16, this);
	
	public Edit(File song, String name){
		tempSong = new File("src/Songs/"+name);
		beatPlace = new File("src/Songs/"+name+"/"+name+".songMap");
		System.out.println(name);
		Main.frame.setTitle("Rythmn Master - " + name);
		try{
			System.out.println("Created the folder: " + tempSong.mkdirs());
			System.out.println("Created the Beatmap: " + beatPlace.createNewFile());
			src = new FileInputStream(song);
			fileOut = new FileOutputStream(beatPlace);
			outStream = new ObjectOutputStream(fileOut);
			dest = new FileOutputStream(tempSong+"/"+name+".wav");
			dest.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
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
		this.addKeyListener(this);
		
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
		time.start();
	}


	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == time){
			totime++;
			pnlScreen.repaint();
			if(bd){
				ind++;
			}
			else if(bf){
				inf++;
			}
			else if(bj){
				inj++;
			}
			else if(bk){
				ink++;
			}
		}
		else if(arg0.getSource() == btnExit){
			closeThings();
			GameFrame.reset();
			GameFrame.add(new MainMenu());
			System.out.println("Going Back");
		}
		else if (arg0.getSource() == btnSub){
			try {
				outStream.writeObject(tempBeats);
				//Get the images and stuff
				closeThings();
				GameFrame.reset();
				GameFrame.add(new MainMenu());
				System.out.println("Going Back");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeThings(){
		try {
			src.close();
			dest.close();
			fileOut.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class DrawPanel extends JPanel{
		int std = 0, stf = 0, stj = 0, stk = 0;
		
		public DrawPanel(){
			this.repaint();
		}
		
		public void drawMap(Graphics g){
			int place = this.getWidth()/4;
			ArrayList<Note> notes = tempBeats.getMap();
			for(Note n : notes){
				g.drawRect(place*n.position, 0+5*(totime - n.time), place, 5*n.length);
			}
		}
		
		public void drawNotes(){
			this.repaint();
		}
		
		public void paintComponent(Graphics g){
			int place = this.getWidth()/4;
			super.paintComponent(g);
			System.out.println(place);
			g.drawLine(place, 0, place, this.getHeight());
			g.drawLine(place*2, 0, place*2, this.getHeight());
			g.drawLine(place*3, 0, place*3, this.getHeight());
			System.out.println("Lines Painted");
			g.drawRect(0, 0, place, ind*5);
			g.drawRect(place, 0, place, inf*5);
			g.drawRect(place*2, 0, place, inj*5);
			g.drawRect(place*3, 0, place, ink*5);
			drawMap(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyChar() == 'd'){
			if(!bd){
				bed = totime;
			}
			bd = true;
		}
		else if(arg0.getKeyChar() == 'f'){
			if(!bf){
				bef = totime;
			}
			bf = true;
		}
		else if(arg0.getKeyChar() == 'j'){
			if(!bj){
				bej = totime;
			}
			bj = true;
		}
		else if(arg0.getKeyChar() == 'k'){
			if(!bk){
				bek = totime;
			}
			bk = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyChar() == 'd'){
			tempBeats.add(ind, 0, bed);
			ind = 0;
			bed = 0;
			bd = false;
		}
		else if(arg0.getKeyChar() == 'f'){
			tempBeats.add(inf, 1, bef);
			inf = 0;
			bef = 0;
			bf = false;
		}
		else if(arg0.getKeyChar() == 'j'){
			tempBeats.add(inj, 2, bej);
			inj = 0;
			bej = 0;
			bj = false;
		}
		else if(arg0.getKeyChar() == 'k'){
			tempBeats.add(ink, 3, bek);
			ink = 0;
			bek = 0;
			bk = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
