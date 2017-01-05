import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Audio {
	private Clip clip;
	private long len = 0;
	private AudioInputStream audin;
	public boolean empty;
	
	public Audio(){
		System.out.println("Empty Audio Created");
		empty = true;
	}
	
	public Audio(String filename){
		try{
			audin = AudioSystem.getAudioInputStream(new File (filename));
			clip = AudioSystem.getClip();
			clip.open(audin);
			len = clip.getMicrosecondLength();
			empty = false;
		}
		catch(Exception e){
			System.out.println("STRING went wrong");
			e.printStackTrace();
		}
	}
	
	public Audio(File file){
		try{
			audin = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audin);
			len = clip.getMicrosecondLength();
			empty = false;
		}catch(Exception e){
			System.out.println("FILE went wrong");
			e.printStackTrace();
		}
	}
	
	public void start(){
		if(!empty){
			clip.setMicrosecondPosition(0);
			clip.start();
		}
	}
	
	public void resume(){
		if(!empty)
			clip.start();
	}
	
	public void pause(){
		if(!empty)
			clip.stop();
	}
	
	public void stop(){
		if(!empty){
			clip.stop();
			clip.setMicrosecondPosition(0);
		}
	}
	
	public boolean checkEnd(){
		return len==clip.getMicrosecondPosition();
	}
	
	public long getCurrent(){
		return clip.getMicrosecondPosition();
	}
	
	public int percentDone(){
		return (int)(((double)clip.getMicrosecondPosition())/len*100);
	}
	
	public long getLength(){
		return len;
	}
	
	public String getTime(){
		return (len/1000000)/60 + ":" + String.format("%02d", (len/1000000)%60);
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
