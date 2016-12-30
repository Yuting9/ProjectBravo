import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Audio {
<<<<<<< HEAD
	public Clip clip;

	public Audio(String path)
	{
		try
		{
		AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(new File (path));
		clip = AudioSystem.getClip();
		clip.open(audioinputstream);
=======
	private Clip clip;
	AudioInputStream audin;
	
	public Audio(String filename){
		try{
			audin = AudioSystem.getAudioInputStream(new File ("src/Songs/"+filename+"/"+filename+".wav"));
			clip = AudioSystem.getClip();
			clip.open(audin);
		}
		catch(Exception e){
			e.printStackTrace();
>>>>>>> origin/master
		}
	}
	
	public Audio(File file){
		try{
			audin = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audin);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void start(){
		clip.setMicrosecondPosition(0);
		clip.start();
		
	}
	
	public void resume(){
		clip.start();
	}
	
	public void stop(){
		clip.stop();
	}
	
	public boolean checkEnd(){
		return clip.getMicrosecondLength()==clip.getMicrosecondPosition();
	}
	
	public int percentDone(){
		return (int)(((double)clip.getMicrosecondPosition())/clip.getMicrosecondLength()*100);
	}
	
	public void remove(){
		try {
			clip.stop();
			clip.close();
			audin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
}
