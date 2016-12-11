import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
	public static String state = "Menu";
	private static final long serialVersionUID = 1L;
	public static Container container;
	public static MainMenu menu = new MainMenu();
	public static Timer timer;
	public GameFrame()
	{
		this.setTitle("Rhythm Master");
		this.setSize(400,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.getContentPane().add(menu);
		timer=new Timer(600, this);
		timer.start();
	}
	
	public static void reset()
	{
		Main.frame.getContentPane().removeAll();
		Main.frame.getContentPane().add(menu);
		
	}
	
	public static void clear()
	{
		System.out.println("Cleared frame");
		Main.frame.getContentPane().removeAll();
	}
	
	public static void add(JPanel jpanel)
	{
		System.out.print(jpanel);
		System.out.println("added to frame");
		Main.frame.getContentPane().add(jpanel);
		Main.frame.repaint();
	}
	

	public void paint( Graphics g)
	{
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.repaint();
		
	}

}
