package com.pbo.wws.io;

import java.io.IOException;

import com.pbo.wws.state.BattleState;
import com.pbo.wws.state.manager.GameStateManager;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

public class Speech {
	private	Configuration config;
	private LiveSpeechRecognizer listener;
	private boolean listening = false;
	private Runnable listenInBg;
	private Thread listenThread;
	private String target;

	public Speech() {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        configuration.setUseGrammar(false);

        LiveSpeechRecognizer recognizer;
		try {
			recognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

        listener = recognizer;
        this.listenInBg = new Runnable() {
			
			@Override
			public void run() {
				listener.startRecognition(false);
				SpeechResult result;
				while (listening && (result = listener.getResult()) != null) {
					for(WordResult word : result.getWords()) {
						System.out.println("[Speech] " + word.getWord().toString());
		                if (word.getWord().toString().equalsIgnoreCase(target)) {
		                	((BattleState) GameStateManager.getState(GameStateManager.BATTLESTATE)).confirmSpell();
		                	listener.stopRecognition();
		                	return;
		                }
		            }
				}
				listener.stopRecognition();
				
			}
		};
		this.listenThread = new Thread(listenInBg);
    }

	public void listen(String target) {
		listening = true;
		this.target = target;
		if (!listenThread.isAlive()) {
			listenThread.start();
		}
		
	}

	public synchronized void stopListen() {
		listening = false;
	}
}
