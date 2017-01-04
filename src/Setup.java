import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Setup extends JPanel implements ActionListener{
	
	private JPanel pnlConfirm;
	private JPanel pnlChoices;
	private JPanel pnlChoSong;
	private JPanel pnlPicture;
	
	private JButton btnConfirm;
	private JButton btnCancel; 
	private JButton btnChoSong;
	private JButton btnChoPic;
	
	private JLabel lblSong;
	private JLabel lblPict;
	private JLabel lblInfo;
	
	private File song;
	
	private Audio audTest;
	
	private JProgressBar songProgress;
	      
	public Setup(){
		pnlConfirm = new JPanel();
		pnlChoices = new JPanel();
		pnlChoSong = new JPanel();
		pnlPicture = new JPanel();
		
		pnlChoSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btnConfirm = new JButton("Confirm");
		btnCancel  = new JButton("Cancel");
		btnChoSong = new JButton("Choose Song");
		btnChoPic  = new JButton("Choose Thumbnail");

		Audio sampleAud = new Audio();
		
		JLabel lblSong = new JLabel("Selected File: Choose a File");
		JLabel lblPict = new JLabel();
		JLabel lblInfo = new JLabel("Song Length: " + sampleAud.getTime());
		
		JProgressBar songProgress = new JProgressBar();
		songProgress.setMaximum((int)sampleAud.getLength());
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new GridLayout());
		pnlConfirm.setLayout(new FlowLayout());

		pnlConfirm.add(btnCancel);
		pnlConfirm.add(btnConfirm);
		add(pnlConfirm, BorderLayout.SOUTH);

		pnlChoices.add(pnlChoSong);
		pnlChoSong.setLayout(new GridLayout(0, 1, 0, 0));
		pnlChoSong.add(lblSong);
		pnlChoSong.add(btnChoSong);
		pnlChoSong.add(lblInfo);
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
