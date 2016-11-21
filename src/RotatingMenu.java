import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class RotatingMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> buttons = new ArrayList<JButton>(); 
	private ButtonListener listener = new ButtonListener();
	
	public RotatingMenu()
	{
		this.setLayout(null);
		if (GameFrame.state.equals("Game"))
		{
			System.out.println("excetution");
			for(int i = 0; i<10; i++)
			{
				buttons.add(new JButton("Button "+Integer.toString(i)));
				buttons.get(buttons.size()-1).setSize(50,30);
				buttons.get(buttons.size()-1).setLocation(0, 40*buttons.size());
				buttons.get(buttons.size()-1).addMouseListener(listener);
				this.add(buttons.get(buttons.size()-1));
			}
		}
	}
	
	public void add_option(String title)
	{
		
		
	}
	
	
	private class ButtonListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			if (GameFrame.state.equals("Game"))
			{
				
			}
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			
		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{
			System.out.println("Show clipart for: " +e.getSource());
		}
	}

}
