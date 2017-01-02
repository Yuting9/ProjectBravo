import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Setup extends JPanel implements ActionListener, MouseListener{
	
	JPanel pnlConfirm;
	JPanel pnlChoices;
	JPanel pnlChoSong;
	JPanel pnlPicture;
	
	JButton btnConfirm;
	JButton btnCancel;
	JButton btnCSong;
	JButton btnCPic;
	
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
		btnCPic= new JButton();
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new BoxLayout(pnlChoices, 0));
		pnlConfirm.setLayout(new FlowLayout());

		pnlConfirm.add(btnCancel);
		pnlConfirm.add(btnConfirm);
		add(pnlConfirm, BorderLayout.SOUTH);

		pnlChoices.add(pnlChoSong);
		pnlChoices.add(pnlPicture);
		add(pnlChoices, BorderLayout.CENTER);
		
		GameFrame.add(this);
		addMouseListener(this);
		

		this.setVisible(true);
		
		this.revalidate();
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
