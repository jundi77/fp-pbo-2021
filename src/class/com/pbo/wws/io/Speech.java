package com.pbo.wws.io;

import java.io.IOException;

import com.pbo.wws.frame.Main;
import com.pbo.wws.state.BattleState;
import com.pbo.wws.state.manager.GameStateManager;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.Context;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.Microphone;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.WordResult;

public class Speech {
	private	Configuration config;
	private WWSLiveSpeechRecognizer listener;
	private boolean listening = false;
	private Runnable listenInBg;
	private Thread listenThread;
	private String target;

	public Speech() {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        
        // custom grammar menyesuaikan speel pemain
        configuration.setGrammarPath("resource:" + Main.resourcePath + "/grammars/");
        configuration.setGrammarName("playerSpells");
        configuration.setUseGrammar(true);
        System.out.println(configuration.getGrammarPath());

        this.config = configuration;

		try {
			listener = new WWSLiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

        this.listenInBg = new Runnable() {
			
			@Override
			public void run() {
				SpeechResult result;
				try {
					while (listening && (result = listener.getResult()) != null) {
						for(WordResult word : result.getWords()) {
							if (!listening) break;
							System.out.println("[Speech] " + word.getWord().toString());
							if (word.getWord().toString().equalsIgnoreCase(target)) {
								((BattleState) GameStateManager.getState(GameStateManager.BATTLESTATE)).confirmSpell();
								return;
							} else {
								((BattleState) GameStateManager.getState(GameStateManager.BATTLESTATE)).wrongSpell(word.getWord().toString());
							}
						}
						if (!listening) break;
					}					
				} catch (Exception e) {
					// is okay, string target terhubung ke kelas speech
				}
			}
		};

		listener.startRecognizer();
		this.listenThread = new Thread(listenInBg);
    }

	public synchronized void listen(String target) {
		listening = true;
		this.target = target;

		if (!listenThread.isAlive()) {
			listener.startMic();
			listenThread.start();
		}
		
	}

	public synchronized void stopListen() {
		listening = false;

		new Thread(new Runnable() {
			public void run() {
				Thread stopThread = listenThread;
				WWSLiveSpeechRecognizer stopListener = listener;

				stopThread.interrupt();
				stopListener.stopMic();
			}
		}).run();

		this.listenThread = new Thread(listenInBg);
	}
}
