import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener{
	public static String state = "Menu";
	public static Container container;
	public static MainMenu menu = new MainMenu();
	public static Timer timer;
	public GameFrame(){
		this.setTitle("Rhythm Master");
		this.setSize(400,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().add(menu);
		menu.addKeyListener(menu);
		timer = new Timer(17,this);
		timer.start();
	}
	
	public static void reset(){
		Main.frame.getContentPane().removeAll();
		Main.frame.getContentPane().add(menu);
		
	}
	
	public static void clear(){
		System.out.println("Cleared frame");
		Main.frame.getContentPane().removeAll();
	}
	
	public static void add(JPanel jpanel){
		System.out.print(jpanel);
		System.out.println("added to frame");
		Main.frame.getContentPane().add(jpanel);
		Main.frame.repaint();
	}
	
	public void paint( Graphics g){
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (state.equals("RotatingMenu")){
			if(RotatingMenu.movement==2 && RotatingMenu.shift<0 ){
				RotatingMenu.shift+=7;	
				RotatingMenu.update();
				System.out.println(RotatingMenu.shift);
			}
			if(RotatingMenu.movement==-2 && RotatingMenu.shift>530+(40*(RotatingMenu.buttons.size()-1))*-1 ){
				RotatingMenu.shift-=7;
				RotatingMenu.update();
				System.out.println(RotatingMenu.shift);
				System.out.println(40*(RotatingMenu.buttons.size()-1));
				System.out.println();
			}
		}	
	}
}
