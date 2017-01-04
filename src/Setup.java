import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Setup extends JPanel implements ActionListener{
	
	private JPanel pnlConfirm;
	private JPanel pnlChoices;
	private JPanel pnlChoSong;
	private JPanel pnlPicture;
	private JPanel pnlFileNom;
	private JPanel pnlGetSong;
	private JPanel pnlPlaSong;
	private JPanel pnlSongBtn;
	private JPanel pnlSongLen;
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
		pnlFileNom = new JPanel();
		pnlSongLen = new JPanel();
		pnlSongBtn = new JPanel();
		pnlGetSong = new JPanel();
		pnlPlaSong = new JPanel();
		pnlControl = new JPanel();
		
		btnConfirm = new JButton("Confirm");
		btnCancel  = new JButton("Cancel");
		btnChoSong = new JButton("Choose Song");
		btnChoPic  = new JButton("Choose Thumbnail");
		btnPlay    = new JButton("Play");
		btnStop    = new JButton("Stop");
		btnPause   = new JButton("Pause");
		
		btnConfirm.addActionListener(this);
		btnCancel .addActionListener(this);
		btnChoSong.addActionListener(this);
		btnChoPic .addActionListener(this);
		btnPlay   .addActionListener(this);
		btnStop   .addActionListener(this);
		btnPause  .addActionListener(this);

		Audio sampleAud = new Audio();
		
		JLabel lblSong = new JLabel("Selected File: Choose a File");
		JLabel lblPict = new JLabel();
		JLabel lblInfo = new JLabel("Song Length: " + sampleAud.getTime());
		
		JProgressBar songProgress = new JProgressBar();
		
		//----------------------Setting Parameters------------------------------
		
		pnlConfirm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlChoices.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlChoSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlFileNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlSongLen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlGetSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPlaSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlControl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		songProgress.setMaximum((int)sampleAud.getLength());
		songProgress.setSize((this.getWidth()/4)-10, 10);
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new GridLayout());
		pnlConfirm.setLayout(new FlowLayout());
		pnlChoSong.setLayout(new GridLayout(0, 1, 0, 0));
		pnlPlaSong.setLayout(new GridLayout(0, 1, 0, 0));
		pnlGetSong.setLayout(new GridLayout(0, 1, 0, 0));
		pnlSongLen.setLayout(new BorderLayout());
		
		//--------------------Adding Components----------------------------------

		add(pnlConfirm, BorderLayout.SOUTH);
		add(pnlChoices, BorderLayout.CENTER);
		
		pnlConfirm.add(btnCancel);
		pnlConfirm.add(btnConfirm);
		pnlChoices.add(pnlChoSong);
		pnlChoSong.add(pnlGetSong);
		pnlGetSong.add(pnlFileNom);
		pnlFileNom.add(lblSong);
		pnlGetSong.add(pnlSongBtn);
		pnlSongBtn.add(btnChoSong);
		pnlChoSong.add(pnlPlaSong);
		pnlPlaSong.add(pnlSongLen);
		pnlSongLen.add(songProgress, BorderLayout.SOUTH);
		pnlPlaSong.add(pnlControl);
		pnlControl.add(btnPlay);
		pnlControl.add(btnPause);
		pnlControl.add(btnStop);
		pnlGetSong.add(lblInfo);
		
		
		
		
		
		
		pnlChoices.add(pnlPicture);
		
		GameFrame.add(this);
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnCancel){
			
		}
		if(arg0.getSource() == btnConfirm){
			/*
			if(s != null){
				GameFrame.clear();
				GameFrame.state="Edit";
				new Edit(choose.getSelectedFile().getAbsoluteFile(),s);
			}
			*/
		}
		if(arg0.getSource() == btnChoSong){

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
			}
		}
	}

}
