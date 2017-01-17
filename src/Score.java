import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class Score extends JPanel implements ActionListener
{
	int score;
	JLabel scoreLbl;
	JButton save;
	JButton quit;
	ArrayList<String[]> scores = new ArrayList<String[]>();
	
	public Score(String title,int num)
	{
		System.out.println("in score now");
		this.setLayout(null);
		scoreLbl=new JLabel("Your score is: "+Integer.toString(num));
		scoreLbl.setLocation(100, 100);
		save=new JButton("Save");
		quit = new JButton("Quit");
		save.setLocation(100, 450);
		quit.setLocation(500,450);
		score = num;
		save.addActionListener(this);
		quit.addActionListener(this);
		this.add(save);
		this.add(quit);
		this.add(scoreLbl);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}
	
	public void addScores(String title){
		BufferedReader input = null;
		try{
			input=new BufferedReader(new FileReader("src/Songs/"+title+"/"+title+",scores"));
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
