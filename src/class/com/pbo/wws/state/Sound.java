package com.pbo.wws.state;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import com.pbo.wws.frame.Main;

public class Sound 
{
	public Sound(){
		
	}
	public static Clip clip;
	
	public void load(String musicLocation)
	{		
		
		URL Url = this.getClass().getResource(musicLocation);
		
		try 
		{
			System.out.println(Url);
			
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(Url);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void stop()
	{
		clip.stop();
	}
	
	
}
