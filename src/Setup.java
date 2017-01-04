import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Setup extends JPanel implements ActionListener{
	
	private JPanel pnlConfirm;
	private JPanel pnlChoices;
	private JPanel pnlChoSong;
	private JPanel pnlPicture;
	private JPanel pnlPlaSong;
	private JPanel pnlControl;
	
	private JButton btnConfirm;
	private JButton btnCancel; 
	private JButton btnChoSong;
	private JButton btnChoPic;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnPause;
	
	
	private JLabel lblSong;
	private JLabel lblPict;
	private JLabel lblInfo;
	
	private File song;
	
	private Audio audTest;
	
	private JProgressBar songProgress;
	
	private Timer timer;
	      
	public Setup(){
		//--------------------------Variable Initialization------------------------
		pnlConfirm = new JPanel();
		pnlChoices = new JPanel();
		pnlChoSong = new JPanel();
		pnlPicture = new JPanel();
		pnlPlaSong = new JPanel();
		pnlControl = new JPanel();
		
		btnConfirm = new JButton("Confirm");
		btnCancel  = new JButton("Cancel");
		btnChoSong = new JButton("Choose Song");
		btnChoPic  = new JButton("Choose Thumbnail");
		btnPlay    = new JButton("Play");
		btnStop    = new JButton("Stop");
		btnPause   = new JButton("Pause");

		Audio sampleAud = new Audio();
		
		JLabel lblSong = new JLabel("Selected File: Choose a File");
		JLabel lblPict = new JLabel();
		JLabel lblInfo = new JLabel("Song Length: " + sampleAud.getTime());
		
		JProgressBar songProgress = new JProgressBar();
		
		//----------------------Setting Parameters------------------------------
		
		pnlChoSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		songProgress.setMaximum((int)sampleAud.getLength());
		songProgress.setSize((this.getWidth()/4)-10, 10);
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new GridLayout());
		pnlConfirm.setLayout(new FlowLayout());
		pnlChoSong.setLayout(new GridLayout(0, 1, 0, 0));
		pnlPlaSong.setLayout(new GridLayout(0, 1, 0, 0));

		pnlConfirm.add(btnCancel);
		pnlConfirm.add(btnConfirm);
		add(pnlConfirm, BorderLayout.SOUTH);

		pnlChoices.add(pnlChoSong);
		pnlChoSong.add(lblSong);
		pnlChoSong.add(btnChoSong);
		pnlChoSong.add(lblInfo);
		pnlChoSong.add(pnlPlaSong);
		pnlPlaSong.add(songProgress);
		pnlPlaSong.add(pnlControl);
		pnlControl.add(btnPlay);
		pnlControl.add(btnPause);
		pnlControl.add(btnStop);
		
		pnlChoices.add(pnlPicture);
		add(pnlChoices, BorderLayout.CENTER);
		
		GameFrame.add(this);

		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnCancel){
			
		}
	}

}
