import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Score extends JPanel implements ActionListener, GlobalVar
{
    private int score;
    private JLabel scoreLbl;
    private File scoreFile;
    private JLabel[] nums = new JLabel[10];
    private JButton save;
    private JButton quit;
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private JPanel btnPnl, namePnl, numPnl;
    public Score(String title,int num)
    {
    	addScores(title);
    	//namePnl.setLayout(new BoxLayout());
    	for(int i=0;i<10;i++)
    	{
    		if(i<scores.size()){
    			nums[i] = new JLabel(Integer.toString(scores.get(i)));
    		}
    		else
    		{
    			nums[i] = new JLabel("");
    		}
    		
    	}
    	
        frame.add(this);
        this.setLayout(new BorderLayout());
        btnPnl=new JPanel();
        btnPnl.setLayout(new FlowLayout());
        scoreLbl=new JLabel("\t\tYour score is: "+Integer.toString(num));
        //scoreLbl.setLocation(100, 100);
        scoreLbl.setFont(new Font("Calibri",0,24));
        save=new JButton("Save Score");
        quit = new JButton("Return to Menu");
        save.setPreferredSize(new Dimension(200,50));
        quit.setPreferredSize(new Dimension(200,50));
        score = num;
        save.addActionListener(this);
        quit.addActionListener(this);
        btnPnl.add(save);
        btnPnl.add(quit);
        this.add(scoreLbl,BorderLayout.NORTH);
        this.add(btnPnl, BorderLayout.SOUTH);
        this.revalidate();
        this.repaint();
        
    }
    
    public void addScores(String title){
        BufferedReader input = null;
        try{
        	scoreFile = new File("src/Songs/"+title+"/"+title+".scores");
            input = new BufferedReader(new FileReader("src/Songs/"+title+"/"+title+".scores"));
        }catch(Exception e){
            e.printStackTrace();
        }
        String line = null;
        int data;
        try
        {
            while((line=input.readLine()) != null)
            {
                data = Integer.parseInt(line);
                scores.add(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int binSearch(int start,int end)
    {
        if(start-end==0 && score>scores.get(start)){
            return start+1;
        }
        else if(start-end==0 && score<=scores.get(start)){
            return start-1;
        }
        
        int mid = (end-start)/2;
        if(score>scores.get(mid))
            return binSearch(mid+1,end);
        else
            return binSearch(start,mid);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        if(arg0.getSource() == save){
        	try {
            	scoreFile.delete();
            	scoreFile.createNewFile();
				PrintWriter fout = new PrintWriter(scoreFile);
				for(int i : scores){
					fout.println(i);
				}
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			frame.clear();
			frame.state="MainMenu";
			frame.reset();
			frame.add(new MainMenu());
        }
        if(arg0.getSource() == quit){
			frame.clear();
			frame.state="MainMenu";
			frame.reset();
			frame.add(new MainMenu());
        }
    }
}
