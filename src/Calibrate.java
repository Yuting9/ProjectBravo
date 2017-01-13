import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calibrate extends JPanel implements ActionListener, KeyListener{
	Timer time;
	int dif;
	float avg;
	int type;
	int buffer;
	boolean boop;
	Audio calAud;
	
	JPanel pnlButton;
	JPanel pnlScreen;
	JPanel pnlLabels;
	JPanel pnlBuffer;
	
	DrawPanel pnlDrawIt;
	
	JButton btnReturn;
	JButton btnConfirm;
	
	JLabel lblInfo;
	
	Calibrate(){
		time = new Timer(16, this);
		calAud = new Audio("src/assets/BEEP.wav");
		dif = 0;
		buffer = 0;
		type = 0;
		avg = 0;
		
		pnlButton = new JPanel();
		pnlScreen = new JPanel();
		pnlLabels = new JPanel();
		pnlBuffer = new JPanel();
		
		lblInfo = new JLabel(avg + " ticks off");
		
		pnlDrawIt = new DrawPanel();
		
		btnReturn  = new JButton("Discard Changes");
		btnConfirm = new JButton("Save Changes");
		
		setLayout(new BorderLayout());
		pnlScreen.setLayout(new BoxLayout(pnlScreen, BoxLayout.Y_AXIS));
		
		pnlButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		time.addActionListener(this);
		addKeyListener(this);
		
		add(pnlButton, BorderLayout.SOUTH);
		pnlButton.add(btnReturn);
		pnlButton.add(btnConfirm);
		add(pnlScreen, BorderLayout.CENTER);
		pnlScreen.add(pnlDrawIt);
		pnlScreen.add(pnlLabels);
		pnlLabels.add(lblInfo);
		pnlScreen.add(pnlBuffer);
		
		GameFrame.add(this);
		
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		time.start();
	}

	public void addNotify(){
		super.addNotify();
		requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == time){
			if(buffer > 20)
				boop = false;
			buffer++;
			if(buffer == 70){
				buffer = 0;
				boop = true;
				calAud.start();
			}
			this.repaint();
		}
		if(e.getSource() == btnConfirm){
			GameFrame.calibrate = avg;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(buffer > 35){
			dif += 0 - (70 - buffer);
		}
		else
			dif += buffer;
		type ++;
		avg = (float)dif/(float)type;
		lblInfo.setText("Average of " + avg + " ticks off");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class DrawPanel extends JPanel{
		
		DrawPanel(){
			this.repaint();
		}
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if(boop){
				g.setColor(Color.GREEN);
				g.fillRect(50, 50, this.getWidth()-100, this.getHeight()-100);
			}
			g.setColor(Color.BLACK);
			g.drawRect(50, 50, this.getWidth()-100, this.getHeight()-100);
		}
		
	}
}
