import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Score extends JPanel implements ActionListener, GlobalVar
{
    private int score;
    private JLabel scoreLbl;
    private JLabel[] names = new JLabel[10];
    private JLabel[] nums = new JLabel[10];
    private JButton save;
    private JButton quit;
    private ArrayList<String[]> scores = new ArrayList<String[]>();
    private JPanel btnPnl,namePnl, numPnl;
    public Score(String title,int num)
    {
    	addScores(title);
    	//namePnl.setLayout(new BoxLayout());
    	for(int i=0;i<10;i++)
    	{
    		if(i<scores.size()){
    			names[i] = new JLabel(scores.get(i)[0]);
    			nums[i] = new JLabel(scores.get(i)[1]);
    		}
    		else
    		{
    			names[i] = new JLabel("");
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
            input=new BufferedReader(new FileReader("src/Songs/"+title+"/"+title+".scores"));
        }catch(Exception e){
            e.printStackTrace();
        }
        String line = null;
        String[] data;
        try
        {
            while((line=input.readLine()) != null)
            {
                data = line.split("\\s");
                scores.add(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int binSearch(int start,int end)
    {
        if(start-end==0 && score>Integer.parseInt(scores.get(start)[1])){
            return start+1;
        }
        else if(start-end==0 && score<=Integer.parseInt(scores.get(start)[1])){
            return start-1;
        }
        
        int mid = (end-start)/2;
        if(score>Integer.parseInt(scores.get(mid)[1]))
            return binSearch(mid+1,end);
        else
            return binSearch(start,mid);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        
    }
}
