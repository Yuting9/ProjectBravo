import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.*;

public class Audio {
	public Clip clip;
	private long time;

	public Audio(String filename)
	{
		try
		{
		AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(new File ("/src/Songs/"+filename+"/"+filename+".wav"));
		clip = AudioSystem.getClip();
		clip.open(audioinputstream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		clip.start();
	}
	
	public void pause()
	{
		time = clip.getMicrosecondPosition();
		clip.stop();
	}
	
	public void resume()
	{
		clip.setMicrosecondPosition(time);
		clip.start();
	}
	
	public void stop()
	{
		clip.stop();
	}
			
}
