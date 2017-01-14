import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calibrate extends JPanel implements ActionListener, KeyListener{
	Timer time;
	 static float avg;
	int type;
	int buffer;
	int count;
	int[] calibration = new int[10];
	boolean boop;
	boolean keyPress;
	Audio calAud;
	
	JPanel pnlButton;
	JPanel pnlScreen;
	JPanel pnlLabels;
	JPanel pnlBuffer;
	
	DrawPanel pnlDrawIt;
	
	JButton btnReturn;
	JButton btnConfirm;
	
	JLabel lblInfo;
	JLabel lblInst;
	
	Calibrate(){
		time = new Timer(16, this);
		calAud = new Audio("src/assets/BEEP.wav");
		buffer = 0;
		type = 0;
		keyPress = true;
		avg = 0;
		
		pnlButton = new JPanel();
		pnlScreen = new JPanel();
		pnlLabels = new JPanel();
		pnlBuffer = new JPanel();
		
		lblInfo = new JLabel("Average of " + avg + " ticks off");
		lblInst = new JLabel("<html><body style='width: 400px'> When you hear the beat and/or see the green light flash, please press any key. The calibration system will take" + 
							" in account your most recent 10 calibrations and provide an average for you. The calibration will calibrate from" +
							" the closest beat, be that the beat that just passed or the beat that is coming, so missing a beat will not" + 
							" adversely affect your calibration number. </html>");
		
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
		pnlBuffer.add(lblInst);
		
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
		if(keyPress){
			if(buffer > 35){
				calibration[count] = 0 - (70 - buffer);
			}
			else
				calibration[count] = buffer;
			if(count < 9){
				count++;
			}
			else{
				count = 0;
			}
			if(type < 10)
				type ++;
			avg = sum(calibration)/(float)type;
			lblInfo.setText("Average of " + avg + " ticks off");
			keyPress = false;
		}
	}
	
	public float sum(int[] arr){
		int x = 0;
		for(int i : arr){
			x+=i;
		}
		return x;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPress = true;
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
