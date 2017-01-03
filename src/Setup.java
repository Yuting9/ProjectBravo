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
	private JButton btnCSong;                    
	private JButton btnCPic;                     
	                                             
	private JLabel lblSong;                      
	private JLabel lblPict;                      
	private JLabel lblInfo;                      
	                                             
	private File song;                           
	                                             
	private Audio audTest;                       
	                                             
	public Setup(){
		pnlConfirm = new JPanel();
		pnlChoices = new JPanel();
		pnlChoSong = new JPanel();
		pnlPicture = new JPanel();
		
		pnlChoSong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		btnConfirm = new JButton("Confirm");
		btnCancel  = new JButton("Cancel");
		btnCSong   = new JButton();
		btnCPic    = new JButton();

		Audio sampleAud = new Audio();
		
		JLabel lblSong = new JLabel("Selected File: Choose a File");
		JLabel lblPict = new JLabel();
		JLabel lblInfo = new JLabel("Song Length: " + sampleAud.getTime());
		
		
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new BoxLayout(pnlChoices, 0));
		pnlConfirm.setLayout(new FlowLayout());

		pnlConfirm.add(btnCancel);
		pnlConfirm.add(btnConfirm);
		add(pnlConfirm, BorderLayout.SOUTH);

		pnlChoices.add(pnlChoSong);
		pnlChoSong.add(lblSong);
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
