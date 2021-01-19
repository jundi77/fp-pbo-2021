package com.pbo.wws.sound;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.pbo.wws.frame.Main;
import com.pbo.wws.state.manager.GameStateManager;

public class Sound 
{
	
	private static URL stateBgm;
	private String musicLocation;
	private int prevState;
	public static Clip clip; 
	//consturctor , mengisi  lokasi file music berupa string
	
	public Sound(String musicLocation)
	{
		this.musicLocation = musicLocation;
		stateBgm = this.getClass().getResource(musicLocation);	
	}

	public void play(int currentState)
	{
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(stateBgm);
			clip = AudioSystem.getClip();	
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		clip.stop();
	}
	
}
