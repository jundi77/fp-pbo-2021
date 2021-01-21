package com.pbo.wws.io;

import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class WWSMicrophone {
	private final TargetDataLine line;
    private final InputStream inputStream;

    public WWSMicrophone(
            float sampleRate,
            int sampleSize,
            boolean signed,
            boolean bigEndian) {
        AudioFormat format =
            new AudioFormat(sampleRate, sampleSize, 1, signed, bigEndian);
        try {
            line = AudioSystem.getTargetDataLine(format);
        } catch (LineUnavailableException e) {
            throw new IllegalStateException(e);
        }
        inputStream = new AudioInputStream(line);
    }

    public void openConnection() {
    	try {
            line.open();
        } catch (LineUnavailableException e) {
            throw new IllegalStateException(e);
        }    	
    }

    public void startRecording() {
    	this.openConnection();
        line.start();
    }

    public void stopRecording() {
        line.stop();
        this.closeConnection();
    }

    public void closeConnection() {
        line.close();
    }

    public InputStream getStream() {
        return inputStream;
    }
}
