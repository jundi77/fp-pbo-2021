package com.pbo.wws.io;

import java.io.IOException;

import edu.cmu.sphinx.api.AbstractSpeechRecognizer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Context;
import edu.cmu.sphinx.api.Microphone;
import edu.cmu.sphinx.frontend.util.StreamDataSource;

public class WWSLiveSpeechRecognizer extends AbstractSpeechRecognizer {

	private final WWSMicrophone microphone;
	private boolean recognizerAllocated = false;

	public WWSLiveSpeechRecognizer(Configuration configuration) throws IOException {
		super(configuration);
		this.microphone = new WWSMicrophone(16000, 16, true, false);
		context.setSpeechSource(microphone.getStream());
	}

	public Context getContext() {
		return this.context;
	}
	
	public synchronized void startRecognizer() {
		if (!recognizerAllocated ) {
			recognizer.allocate();
			recognizerAllocated = true;
		}
	}

	public void stopMic() {
		microphone.stopRecording();
	}
	
	public void startMic() {
		microphone.startRecording();
	}
	
	public synchronized void closeRecognizer() {
		if (recognizerAllocated) {
			recognizer.deallocate();
			recognizerAllocated = false;
		}
	}
}
