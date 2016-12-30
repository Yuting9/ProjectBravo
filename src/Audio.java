import java.applet.AudioClip;
import java.io.File;

import javax.sound.sampled.*;

public class Audio {
	public Clip clip;

	public Audio(String path)
	{
		try
		{
		AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(new File (path));
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
		clip.setMicrosecondPosition(0);
		clip.start();
		
	}
	

	
	public void resume()
	{
		clip.start();
	}
	
	public void stop()
	{
		clip.stop();
	}
			
}
