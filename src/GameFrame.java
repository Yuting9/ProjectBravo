import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
	public static String state = "Menu";
	private static final long serialVersionUID = 1L;
	public static Container container;
	public static MainMenu menu = new MainMenu();
	
	public GameFrame()
	{
		this.setTitle("Test");
		this.setSize(400,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().add(menu);
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
		System.out.print(">re");
		Main.frame.repaint();
	}
	

	public void paint( Graphics g)
	{
		System.out.println("painting");
		super.paint(g);
	}


}
