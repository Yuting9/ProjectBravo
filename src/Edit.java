/**
 * Creates the beatmap from user input
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
public class Edit extends JPanel implements ActionListener, KeyListener{
	
	private JPanel pnlScreen, pnlBtn;
	private JButton btnPlay, btnClear, btnSub, btnExit;
	private BeatMap tempBeats = new BeatMap();
	private FileInputStream src;
	private FileOutputStream fileOut, dest;
	private ObjectOutputStream outStream;
	private File tempSong, beatPlace;
	private boolean bd = false, bf = false, bg = false, bh = false, bj = false, bk = false;
	private int ind = 0,inf = 0,ing = 0,inh = 0,inj = 0,ink = 0;
	private int bed = 0,bef = 0,beg = 0,beh = 0,bej = 0,bek = 0,totime = 0;
	private int songPercent = 0;
	private Audio tempAud;
	private Timer time = new Timer(16, this);
	private String fileName;
	private File songPlace;
	private JLabel lblPercent;
	
	public Edit(File song, String name){
		songPlace = song;
		fileName = name;
		pnlScreen = new DrawPanel();
		pnlBtn = new JPanel();
		btnPlay = new JButton("Test");
		btnClear = new JButton("Clear");
		btnSub = new JButton("Submit");
		btnSub.setEnabled(false);
		btnExit = new JButton("Exit");
		tempAud = new Audio(songPlace);
		lblPercent = new JLabel(Integer.toString(songPercent));
		pnlScreen.setBackground(Color.WHITE);

		System.out.println(fileName);
		Main.frame.setTitle("Rythmn Master - " + fileName);
		
		GameFrame.add(this);
		addKeyListener(this);
		
		setLayout(new BorderLayout());
		add(pnlScreen, BorderLayout.CENTER);
		pnlBtn.setLayout(new FlowLayout());
		add(pnlBtn, BorderLayout.SOUTH);
		
		pnlBtn.add(btnPlay);
		pnlBtn.add(btnClear);
		pnlBtn.add(btnSub);
		pnlBtn.add(btnExit);
		pnlBtn.add(lblPercent);
		
		btnPlay.addActionListener(this);
		btnClear.addActionListener(this);
		btnSub.addActionListener(this);
		btnExit.addActionListener(this);
		
		this.setVisible(true);
		
		this.revalidate();
		this.repaint();
		System.out.println("In edit mode");
		tempAud.start();
		time.start();
	}
	
	public void createFiles(){
		tempSong = new File("src/Songs/"+fileName);
		beatPlace = new File("src/Songs/"+fileName+"/"+fileName+".songMap");
		try{
			System.out.println("Created the folder: " + tempSong.mkdirs());
			System.out.println("Created the Beatmap: " + beatPlace.createNewFile());
			src = new FileInputStream(songPlace);
			fileOut = new FileOutputStream(beatPlace);
			outStream = new ObjectOutputStream(fileOut);
			dest = new FileOutputStream(tempSong+"/"+fileName+".wav");
			dest.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == time){
			songPercent = tempAud.percentDone();
			lblPercent.setText(Integer.toString(songPercent) + "% Complete");
			if(songPercent == 100 && !btnSub.isEnabled()){
				btnSub.setEnabled(true);
			}
			totime++;
			pnlScreen.repaint();
			if(bd){
				ind++;
			}
			if(bf){
				inf++;
			}
			if(bj){
				inj++;
			}
			if(bk){
				ink++;
			}
		}
		else if(arg0.getSource() == btnExit){
			tempAud.remove();
			GameFrame.reset();
			GameFrame.add(new MainMenu());
			System.out.println("Going Back");
		}
		else if (arg0.getSource() == btnSub){
			try {
				createFiles();
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
		// TEMPORARY CODE START-----------------------
		else if (arg0.getSource() == btnPlay){
			try {
				createFiles();
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
		// TEMPORARY CODE END -------------------------
	}
	
	public void closeThings(){
		try {
			tempAud.remove();
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
			int place = this.getWidth()/6;
			ArrayList<Note> notes = tempBeats.getMap();
			for(Note n : notes){
				switch(n.position){
				case 0: g.setColor(Color.BLUE);
						break;
				case 1: g.setColor(Color.GREEN);
						break;
				case 2: g.setColor(Color.BLUE);
						break;
				case 3: g.setColor(Color.GREEN);
						break;
				case 4: g.setColor(Color.BLUE);
						break;
				case 5: g.setColor(Color.GREEN);
				default: g.setColor(Color.BLACK);
						break;
				}
				g.fillRect(place*n.position,5*(totime - n.time) - 5*n.length, place,5*n.length);
			}
		}
		
		public void drawNotes(){
			this.repaint();
		}
		
		public void paintComponent(Graphics g){
			int place = this.getWidth()/6;
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawLine(place, 0, place, this.getHeight());
			g.drawLine(place*2, 0, place*2, this.getHeight());
			g.drawLine(place*3, 0, place*3, this.getHeight());
			g.drawLine(place*4, 0, place*4, this.getHeight());
			g.drawLine(place*5, 0, place*5, this.getHeight());
			g.fillRect(0, 0, place, ind*5);
			g.fillRect(place, 0, place, inf*5);
			g.fillRect(place*2, 0, place, ing*5);
			g.fillRect(place*3, 0, place, inh*5);
			g.fillRect(place*4, 0, place, inj*5);
			g.fillRect(place*5, 0, place, ink*5);
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
		if(arg0.getKeyChar() == 'f'){
			if(!bf){
				bef = totime;
			}
			bf = true;
		}
		if(arg0.getKeyChar() == 'g'){
			if(!bj){
				beg = totime;
			}
			bg = true;
		}
		if(arg0.getKeyChar() == 'h'){
			if(!bk){
				beh = totime;
			}
			bh = true;
		}
		if(arg0.getKeyChar() == 'j'){
			if(!bj){
				bej = totime;
			}
			bj = true;
		}
		if(arg0.getKeyChar() == 'k'){
			if(!bk){
				bek = totime;
			}
			bk = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyChar() == 'd'){
			if(ind < 5)
				ind = 5;
			tempBeats.add(ind, 0, bed);
			ind = 0;
			bed = 0;
			bd = false;
		}
		if(arg0.getKeyChar() == 'f'){
			if(inf < 5)
				inf = 5;
			tempBeats.add(inf, 1, bef);
			inf = 0;
			bef = 0;
			bf = false;
		}
		if(arg0.getKeyChar() == 'g'){
			if(ing < 5)
				ing = 5;
			tempBeats.add(ing, 2, beg);
			ing = 0;
			beg = 0;
			bg = false;
		}
		if(arg0.getKeyChar() == 'h'){
			if(inh < 5)
				inh = 5;
			tempBeats.add(inh, 3, beh);
			inh = 0;
			beh = 0;
			bh = false;
		}
		if(arg0.getKeyChar() == 'j'){
			if(inj < 5)
				inj = 5;
			tempBeats.add(inj, 4, bej);
			inj = 0;
			bej = 0;
			bj = false;
		}
		if(arg0.getKeyChar() == 'k'){
			if(ink < 5)
				ink = 5;
			tempBeats.add(ink, 5, bek);
			ink = 0;
			bek = 0;
			bk = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
