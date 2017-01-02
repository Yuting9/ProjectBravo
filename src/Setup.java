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
		
		btnConfirm = new JButton();
		btnCancel  = new JButton();
		
		btnCSong   = new JButton();
		btnCPic= new JButton();
		
		setLayout(new BorderLayout());
		pnlChoices.setLayout(new FlowLayout());
		add(pnlConfirm, BorderLayout.SOUTH);
		add(pnlChoices, BorderLayout.CENTER);
		pnlChoices.add(pnlChoSong);
		pnlChoices.add(pnlPicture);
		
		
		GameFrame.add(this);
		addMouseListener(this);
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
