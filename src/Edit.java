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
public class Edit extends JPanel implements ActionListener, KeyListener,GlobalVar{
	
	private JPanel pnlScreen, pnlBtn;
	private JButton btnPlay, btnClear, btnSub, btnExit;
	private BeatMap tempBeats = new BeatMap();
	private FileInputStream src;
	private FileOutputStream dest;
	private File tempSong, beatPlace;
	private boolean bd = false, bf = false, bl = false, bs = false, bj = false, bk = false; // If the button bX is being pressed
	private int bed = 0,bef = 0,bel = 0,bes = 0,bej = 0,bek = 0; // The beginning of the key press, and total time passed
	private int songPercent = 0;
	private Audio tempAud;
	private Timer time = new Timer(16, this);
	private String fileName;
	private File songPlace;
	private JLabel lblPercent;
	
	// New editing screen
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
		frame.setTitle("Rythmn Master - " + fileName);
		
		frame.add(this);
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
		//System.out.println("In edit mode");
		tempAud.start();
		time.start();
	}
	
	// Creates the files
	public void createFiles(){
		tempSong = new File("Songs/"+fileName);
		beatPlace = new File("Songs/"+fileName+"/"+fileName+".songMap");
		try{
			System.out.println("Created the folder: " + tempSong.mkdirs());
			src = new FileInputStream(songPlace);
			tempBeats.writeMap(beatPlace);
			dest = new FileOutputStream(tempSong+"/"+fileName+".wav");
			dest.getChannel().transferFrom(src.getChannel(), 0, src.getChannel().size());
			File file = new File(tempSong + "/" + fileName + ".scores");
			file.createNewFile();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// Don't know what this does but it's needed for keylistener
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		// Sets the song percentage
		if(arg0.getSource() == time){
			songPercent = tempAud.percentDone();
			lblPercent.setText(Integer.toString(songPercent) + "% Complete");
			if(songPercent == 100 && !btnSub.isEnabled()){
				time.stop();
				btnSub.setEnabled(true);
			}
			pnlScreen.repaint();
		}
		// Exits to main menu
		else if(arg0.getSource() == btnExit){
			tempAud.remove();
			frame.reset();
			frame.add(new MainMenu());
			System.out.println("Going Back");
		}
		// Submits the beatmap, creates the files, and exits to main menu if 
		else if (arg0.getSource() == btnSub){
			try {
				createFiles();
				//Get the images and stuff
				closeThings();
				frame.reset();
				frame.add(new MainMenu());
				System.out.println("Going Back");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Debug Mode
		// TEMPORARY CODE START-----------------------
		else if (arg0.getSource() == btnPlay){//     |
			try {//                                  |
				createFiles();
				closeThings();
				frame.reset();
				frame.add(new MainMenu());
				System.out.println("Going Back");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// TEMPORARY CODE END -------------------------
	}
	
	// Close all the things needed
	public void closeThings(){
		try {
			tempAud.remove();
			src.close();
			dest.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Drawpanel
	class DrawPanel extends JPanel{
		int std = 0, stf = 0, stj = 0, stk = 0;
		
		public DrawPanel(){
			this.repaint();
		}
		
		// Draws the notes
		public void drawMap(Graphics g){
			int place = this.getWidth()/6;
			ArrayList<Note> notes = tempBeats.getMap();
			for(Note n : notes){
				g.setColor(Color.BLUE);
				g.fillRect(place*n.position,(tempAud.getSecondTime() - n.time-n.length)/3 , place,n.length/3);
			}
		}
		
		// Draws the holding of the notes
		public void paintComponent(Graphics g){
			int place = this.getWidth()/6;
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.drawLine(place, 0, place, this.getHeight());
			g.drawLine(place*2, 0, place*2, this.getHeight());
			g.drawLine(place*3, 0, place*3, this.getHeight());
			g.drawLine(place*4, 0, place*4, this.getHeight());
			g.drawLine(place*5, 0, place*5, this.getHeight());
			if(bs)
				g.fillRect(0, 0, place, (tempAud.getSecondTime()-bes)/3);
			if(bd)
				g.fillRect(place, 0, place, (tempAud.getSecondTime()-bed)/3);
			if(bf)
				g.fillRect(place*2, 0, place, (tempAud.getSecondTime()-bef)/3);
			if(bj)
				g.fillRect(place*3, 0, place, (tempAud.getSecondTime()-bej)/3);
			if(bk)
				g.fillRect(place*4, 0, place, (tempAud.getSecondTime()-bek)/3);
			if(bl)
				g.fillRect(place*5, 0, place, (tempAud.getSecondTime()-bel)/3);
			drawMap(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// Find which key was pressed, and if it isn't being held, set the initial time to the current time
		if(arg0.getKeyChar() == 's'){
			if(!bs){
				bes = tempAud.getSecondTime();
			}
			bs = true;
		}
		if(arg0.getKeyChar() == 'd'){
			if(!bd){
				bed = tempAud.getSecondTime();
			}
			bd = true;
		}
		if(arg0.getKeyChar() == 'f'){
			if(!bf){
				bef = tempAud.getSecondTime();
			}
			bf = true;
		}
		if(arg0.getKeyChar() == 'j'){
			if(!bj){
				bej = tempAud.getSecondTime();
			}
			bj = true;
		}
		if(arg0.getKeyChar() == 'k'){
			if(!bk){
				bek = tempAud.getSecondTime();
			}
			bk = true;
		}
		if(arg0.getKeyChar() == 'l'){
			if(!bl){
				bel = tempAud.getSecondTime();
			}
			bl = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int len;
		// Find which key was released, set the minimum length of note, create the note in the beatmap, and reset vars.
		if(arg0.getKeyChar() == 's'){
			len = tempAud.getSecondTime()-bes;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 0, bes + (int)Calibrate.avg);
			bes = 0;
			bs = false;
		}
		if(arg0.getKeyChar() == 'd'){
			len = tempAud.getSecondTime()-bed;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 1, bed+ (int)Calibrate.avg);
			bed = 0;
			bd = false;
		}
		if(arg0.getKeyChar() == 'f'){
			len = tempAud.getSecondTime()-bef;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 2, bef+ (int)Calibrate.avg);
			bef = 0;
			bf = false;
		}
		if(arg0.getKeyChar() == 'j'){
			len = tempAud.getSecondTime()-bej;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 3, bej + (int)Calibrate.avg);
			bej = 0;
			bj = false;
		}
		if(arg0.getKeyChar() == 'k'){
			len = tempAud.getSecondTime()-bek;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 4, bek + (int)Calibrate.avg);
			bek = 0;
			bk = false;
		}
		if(arg0.getKeyChar() == 'l'){
			len = tempAud.getSecondTime()-bel;
			if(len < 175)
				len = 75;
			tempBeats.add(len, 5, bel + (int)Calibrate.avg);
			bel = 0;
			bl = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
