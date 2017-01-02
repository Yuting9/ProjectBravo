import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Setup extends JPanel implements ActionListener{
	
	JPanel pnlConfirm;
	JPanel pnlChoices;
	JPanel pnlChoSong;
	JPanel pnlPicture;
	
	JButton btnConfirm;
	JButton btnCancel;
	JButton btnCSong;
	JButton btnCPic;
	
	JLabel lblSong;
	JLabel lblPict;
	JLabel lblInfo;
	
	File song;
	
	Audio audTest;
	
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
		
	}

}
