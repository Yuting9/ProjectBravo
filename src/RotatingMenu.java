import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class RotatingMenu extends JPanel implements MouseMotionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	public ArrayList<JButton> buttons = new ArrayList<JButton>();
	private int allx, ally;
	private Timer time;
	private int shift= 0;
	public RotatingMenu()
	{
		this.setLayout(null);
		addMouseMotionListener(this);
		if (GameFrame.state.equals("Game"))
		{
			this.revalidate();
<<<<<<< HEAD
			this.repaint();	
=======
			this.repaint();
>>>>>>> origin/master
		}
	}
	
	public void add_button(String title)
	{
		buttons.add(new JButton(title));
		buttons.get(buttons.size()-1).setSize(100,30);
		buttons.get(buttons.size()-1).setName(title);
		System.out.println(buttons.get(buttons.size()-1).getName());
		buttons.get(buttons.size()-1).setLocation(10, 40*buttons.size()+50+(int)(shift%10000));
		buttons.get(buttons.size()-1).addMouseListener(this);
		this.add(buttons.get(buttons.size()-1));
	}
	
	public void update()
	{
		for(int i=0;i<buttons.size();i++){
			buttons.get(i).setLocation(10, 40*i+50+(shift%10000));
			System.out.println(40*i+50+(int)(shift%10000));
			this.add(buttons.get(i));
		}
		this.revalidate();
		this.repaint();
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
			shift-=2;
			update();
		}
		else if(ally<=50)
		{
			System.out.println("scrolling up");
			shift+=2;
			update();
		}
		else
		{
			System.out.println("not");
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
