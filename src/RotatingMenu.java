import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class RotatingMenu extends JPanel implements MouseMotionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	public static ArrayList<JButton> buttons = new ArrayList<JButton>();
	public int allx,ally;
	public static int movement;

	public static int shift= 0;
	public RotatingMenu()
	{
		GameFrame.state= "RotatingMenu";
		this.setLayout(null);
		addMouseMotionListener(this);
		
		if (GameFrame.state.equals("Game"))
		{
			this.revalidate();
			this.repaint();	
		}
		
	}
	
	public void add_button(String title)
	{
		int temp_x=40*buttons.size()+(int)(shift%10000);
		buttons.add(new JButton(title));
		buttons.get(buttons.size()-1).setSize(100,30);
		buttons.get(buttons.size()-1).setName(title);
		System.out.println(buttons.get(buttons.size()-1).getName());
		buttons.get(buttons.size()-1).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);

		buttons.get(buttons.size()-1).addMouseListener(this);
		this.add(buttons.get(buttons.size()-1));
	}
	
	public static void update()
	{
		int temp_x;
		for(int i=0;i<buttons.size();i++){
			temp_x=40*i+(int)(shift%10000);
			buttons.get(i).setLocation((int)(-4*(temp_x*temp_x-570*temp_x)/10000), temp_x);
			System.out.println((int)(-4*(temp_x*temp_x-570*temp_x)/1000));
			System.out.println(temp_x);
			System.out.println();
			Game.gameMenu.add(buttons.get(i));
		}
		Game.gameMenu.revalidate();
		Game.gameMenu.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		allx = e.getX();
		ally = e.getY();
		if(ally>=550)
		{
			System.out.println("scrolling down");
			movement=-2;
		}
		else if(ally<=50)
		{
			System.out.println("scrolling up");
			movement=2;
		}
		else
		{
			movement=0;
			//System.out.println("not");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Show clipart for: " +e.getSource());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
