/**
 * The main menu, leading to all other panels
 * 
 * @author Yuting, Ganashsai
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenu extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener, GlobalVar{ 
    /**
     * 
     */
    
    
    JButton[] btnArray = new JButton[4];
    JLabel[] lblArray = new JLabel[4];
    JLabel gameTitle = new JLabel();
    JPanel btnPanel = new JPanel();
    Icon arrow = null;
    Icon title = null;
    
    public MainMenu(){
    	System.out.println("Getting Arrow");
    	System.out.println("title before: "+title);
        try{
        	title = new ImageIcon("assets/title.jpg");
            arrow = new ImageIcon("assets/Arrow_Right.png");
        }catch(Exception e){
        	//System.out.println("check");
            e.printStackTrace();
        }
        
        System.out.println("title before: "+title);
        
        this.setLayout(new BorderLayout());
        for(int i = 0; i<4; i++){
            lblArray[i] = new JLabel("");
        }
        gameTitle.setIcon(title);
        gameTitle.setLocation(300,300);
        btnArray[0] = new JButton("Player Mode");
        btnArray[1] = new JButton("Creator Mode");
        btnArray[2] = new JButton("Options");
        btnArray[3] = new JButton("EXIT");
        btnPanel.setLayout(null);
        this.add(btnPanel, BorderLayout.CENTER);
        btnPanel.addMouseMotionListener(this);
        addKeyListener(this);
        btnPanel.setBackground(Color.WHITE);
        for(int i=0; i<4; i++){
            btnArray[i].setLocation(225, 300+(i*55));
            lblArray[i].setLocation(175, 300+(i*55));
            btnArray[i].setSize(150, 50);
            lblArray[i].setSize(50,50);
            btnArray[i].addActionListener(this);
            btnArray[i].addMouseListener(this);
            btnPanel.add(lblArray[i]);
            btnPanel.add(btnArray[i]);
        }
        this.repaint();
        
        
    }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	System.out.println("paintIcon");
    	title.paintIcon(this, g, 30, 30);
    }
    
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource() == btnArray[0]){
            //btnArray[0].setText("Loading...");
            //btnArray[0].repaint();
            this.repaint();
            frame.clear();
            frame.state="Game";
            //System.out.println("Game Setup");
            Game.GameSetup();
        }
        else if(arg0.getSource() == btnArray[1]){
            this.repaint();
            frame.clear();
            frame.state="Setup";
            System.out.println("Edit Setup");
            new Setup();
        }
        if(arg0.getSource() == btnArray[2]){
            frame.clear();
            frame.state="Calibrate";
            new Instructions();
        }
        else if(arg0.getSource() == btnArray[3]){
            frame.dispose();
        }
        this.revalidate();
        this.repaint();
    }
    
    private void setIcons(int n){
        for(int i = 0; i<4; i++){
            if(i == n){
                lblArray[i].setIcon(arrow);
            }
            else{
                lblArray[i].setIcon(null);
            }
            lblArray[i].repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        if(arg0.getY() >= 465){
            setIcons(3);
        }
        else if(arg0.getY() >= 408){
            setIcons(2);
        }
        else if(arg0.getY() >= 352){
            setIcons(1);
        }
        else{
            setIcons(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() == btnArray[0]){
            setIcons(0);
        }
        else if(e.getSource() == btnArray[1]){
            setIcons(1);
        }
        else if(e.getSource() == btnArray[2]){
            setIcons(2);
        }
        else if(e.getSource() == btnArray[3]){
            setIcons(3);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {   }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // This part works now! yay
        //System.out.println(arg0.getKeyCode() + "check");
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
