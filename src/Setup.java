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
	private JButton btnPlayPause;
	private JButton btnStop;
	
	private JLabel lblSong;
	private JLabel lblName;
	private JLabel lblPict;
	private JLabel lblInfo;
	
	private File song;
	
	private Audio audTest;
	
	private DrawPanel songProgress;
	
	private Timer timer;
	
	private String chosenName;
	
	private JFileChooser choose;
	
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
		
		btnConfirm   = new JButton("Confirm");
		btnCancel    = new JButton("Cancel");
		btnChoSong   = new JButton("Choose Song");
		btnChoPic    = new JButton("Choose Thumbnail");
		btnPlayPause = new JButton("Play");
		btnStop      = new JButton("Stop");
		
		choose 	     = new JFileChooser();
		
		btnConfirm  .addActionListener(this);
		btnCancel   .addActionListener(this);
		btnChoSong  .addActionListener(this);
		btnChoPic   .addActionListener(this);
		btnPlayPause.addActionListener(this);
		btnStop   	.addActionListener(this);
		
		audTest = new Audio();
		
		lblSong = new JLabel("Selected File: ");
		lblName = new JLabel("Choose a File");
		lblPict = new JLabel();
		lblInfo = new JLabel("Song Length: " + audTest.getTime());
		
		songProgress = new DrawPanel();
		
		timer = new Timer(16, this);
		
		//----------------------Setting Parameters------------------------------
		
		
		pnlConfirm.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlChoices.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlChoSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlFileNom.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlSongLen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
		pnlFileNom.add(lblName);
		pnlGetSong.add(pnlSongBtn);
		pnlSongBtn.add(btnChoSong);
		pnlChoSong.add(pnlPlaSong);
		pnlPlaSong.add(pnlSongLen);
		pnlSongLen.add(lblInfo, BorderLayout.CENTER);
		pnlSongLen.add(songProgress, BorderLayout.SOUTH);
		pnlPlaSong.add(pnlControl);
		pnlControl.add(btnPlayPause);
		pnlControl.add(btnStop);
		
		pnlChoices.add(pnlPicture);
		
		GameFrame.add(this);
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		timer.start();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == timer){
			lblInfo.setText("Song Length: " + audTest.getTime());
			if(!audTest.empty){
				System.out.println(audTest.getCurrentTime() + " " + audTest.percentDone());
				songProgress.repaint();
			}
			this.revalidate();
			this.repaint();
		}
		if(arg0.getSource() == btnCancel){
			
		}
		if(arg0.getSource() == btnConfirm){
			if(chosenName != null){
				GameFrame.clear();
				GameFrame.state="Edit";
				new Edit(choose.getSelectedFile().getAbsoluteFile(),chosenName);
			}
		}
		if(arg0.getActionCommand() != null){
			if(arg0.getActionCommand().equals("Play")){
				if(!audTest.empty){
					btnPlayPause.setText("Pause");
					System.out.println("Play");
					audTest.resume();
					repaint();
				}
			}
			if(arg0.getActionCommand().equals("Pause")){
				btnPlayPause.setText("Play");
				System.out.println("Pause");
				audTest.pause();
				repaint();
			}
		}
		if(arg0.getSource() == btnChoSong){
			JOptionPane.showMessageDialog(this, "Please Choose a song (Must be in WAV format)");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV File", "wav");
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
				lblName.setText(choose.getSelectedFile().getName());
				audTest = new Audio(choose.getSelectedFile());
			}
		}
		if(arg0.getSource() == btnStop){
			System.out.println("Stop");
			audTest.stop();
			repaint();
		}
	}
	
	class DrawPanel extends JPanel{
		
		public DrawPanel(){
			this.repaint();
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			int w = this.getWidth(), h = this.getHeight();
			if(!audTest.empty){
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, (int)((w/100.0)*audTest.percentDone()), h);	
			}
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, w, h);
		}
	}
}
