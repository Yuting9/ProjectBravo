/**
 * Tells the user how to play the game  
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Instructions extends JPanel implements ActionListener, GlobalVar{
    
    JButton back = new JButton("Back");
    JButton btnCalibrate = new JButton("Calibrate");
    JPanel btnPnl = new JPanel();
    JPanel imgPnl = new JPanel();
    
    public Instructions(){
        this.setLayout(new BorderLayout());
        
        frame.add(this);
        
        frame.state = "Options";
        
        back.addActionListener(this);
        btnCalibrate.addActionListener(this);
        
        imgPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(btnPnl, BorderLayout.SOUTH);
        this.add(imgPnl, BorderLayout.CENTER);
        imgPnl.add(btnCalibrate);
        btnPnl.add(back);
        
        
        this.setVisible(true);
        
        this.revalidate();
        this.repaint();
        System.out.println("In options mode");
    }
    
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == back){
            frame.reset();
            frame.add(new MainMenu());
            System.out.println("Going Back");
        }
        if(arg0.getSource() == btnCalibrate){
            frame.clear();
            new Calibrate();
            System.out.println("Calibrate");
        }
    }
}
