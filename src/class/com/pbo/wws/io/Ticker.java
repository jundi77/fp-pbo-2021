package com.pbo.wws.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Ticker {
	private static int RATE_PER_SECOND = 60;
	private static Timer timer;
	private static ArrayList<ActionListener> pushActionList;
	
	public static void start() {
		if (Ticker.timer == null) {
			newTimer();
		}

		if (!isTicking()) {
			Ticker.timer.start();			
		}
		
		System.out.println("[Ticker] Ticker sudah berjalan!");
	}
	
	public static void start(int ratePerSecond) {
		Ticker.RATE_PER_SECOND = ratePerSecond;

		if (Ticker.timer == null) {
			newTimer();
		}

		if (!isTicking()) {
			Ticker.timer.start();			
		}
		
		System.out.println("[Ticker] Ticker sudah berjalan!");
	}

	public static void stop() {
		if (Ticker.timer != null) {
			if (isTicking()) {
				Ticker.timer.stop();		
			}
			System.out.println("[Ticker] Ticker sudah berhenti!");
		}
		
		System.out.println("[Ticker] Ticker belum dimulai!");
	}

	@SuppressWarnings("serial")
	private static void newTimer() {
		Ticker.timer = new Timer(1000 / RATE_PER_SECOND, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (ActionListener actionListener : pushActionList) {
					actionListener.actionPerformed(e);
				}
			}
		});
	}

	private static void newPushActionList() {
		Ticker.pushActionList = new ArrayList<ActionListener>();
	}

	public static boolean isTicking() {
		return Ticker.timer.isRunning();
	}

	public static void addActionListener(ActionListener actionListener) {
		if (Ticker.pushActionList == null) {
			newPushActionList();
		}

		Ticker.pushActionList.add(actionListener);
	}
	
	public static void removeActionListener(ActionListener actionListener) {
		if (Ticker.pushActionList != null) {
			Ticker.pushActionList.remove(actionListener);
		}
		
	}

	public static void setRatePerSecond(int ratePerSecond) {
		Ticker.RATE_PER_SECOND = ratePerSecond;
	}
}
