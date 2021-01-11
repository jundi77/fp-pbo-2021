package com.pbo.wws.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyMapper extends KeyAdapter{

	public static final int KEY_UP = 0;
	public static final int KEY_DOWN = 1;
	public static final int KEY_LEFT = 2;
	public static final int KEY_RIGHT = 3;
	public static final int KEY_W = 4;
	public static final int KEY_A = 5;
	public static final int KEY_S = 6;
	public static final int KEY_D = 7;
	public static final int KEY_ENTER = 8;
	public static final int KEY_ESCAPE = 9;

	private static boolean[] keyPressedFlag = new boolean[10];
	private static boolean   enterConfirmed = false, escapeConfirmed = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			if (!keyPressedFlag[KEY_UP]) {
				keyPressedFlag[KEY_UP] = true;
				System.out.println("[KeyMapper] UP");				
			}
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			if (!keyPressedFlag[KEY_DOWN]) {
				keyPressedFlag[KEY_DOWN] = true;
				System.out.println("[KeyMapper] DOWN");				
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			if (!keyPressedFlag[KEY_LEFT]) {
				keyPressedFlag[KEY_LEFT] = true;
				System.out.println("[KeyMapper] LEFT");				
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			if (!keyPressedFlag[KEY_RIGHT]) {
				keyPressedFlag[KEY_RIGHT] = true;
				System.out.println("[KeyMapper] RIGHT");				
			}
			break;
		case KeyEvent.VK_W:
			if (!keyPressedFlag[KEY_W]) {
				keyPressedFlag[KEY_W] = true;
				System.out.println("[KeyMapper] W");				
			}
			break;
		case KeyEvent.VK_A:
			if (!keyPressedFlag[KEY_A]) {
				keyPressedFlag[KEY_A] = true;
				System.out.println("[KeyMapper] A");				
			}
			break;
		case KeyEvent.VK_S:
			if (!keyPressedFlag[KEY_S]) {				
				keyPressedFlag[KEY_S] = true;
				System.out.println("[KeyMapper] S");
			}
			break;
		case KeyEvent.VK_D:
			if (!keyPressedFlag[KEY_D]) {
				keyPressedFlag[KEY_D] = true;
				System.out.println("[KeyMapper] D");				
			}
			break;
		case KeyEvent.VK_ENTER:
			if (!keyPressedFlag[KEY_ENTER] && !enterConfirmed) {
				keyPressedFlag[KEY_ENTER] = true;
				System.out.println("[KeyMapper] ENTER");				
			}
			break;
		case KeyEvent.VK_ESCAPE:
			if (!keyPressedFlag[KEY_ESCAPE] && !escapeConfirmed) {
				keyPressedFlag[KEY_ESCAPE] = true;
				System.out.println("[KeyMapper] ESCAPE");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			keyPressedFlag[KEY_UP] = false;
			System.out.println("[KeyMapper] UP RELEASED");
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			keyPressedFlag[KEY_DOWN] = false;
			System.out.println("[KeyMapper] DOWN RELEASED");
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			keyPressedFlag[KEY_LEFT] = false;
			System.out.println("[KeyMapper] LEFT RELEASED");
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			keyPressedFlag[KEY_RIGHT] = false;
			System.out.println("[KeyMapper] RIGHT RELEASED");
			break;
		case KeyEvent.VK_W:
			keyPressedFlag[KEY_W] = false;
			System.out.println("[KeyMapper] W RELEASED");
			break;
		case KeyEvent.VK_A:
			keyPressedFlag[KEY_A] = false;
			System.out.println("[KeyMapper] A RELEASED");
			break;
		case KeyEvent.VK_S:
			keyPressedFlag[KEY_S] = false;
			System.out.println("[KeyMapper] S RELEASED");
			break;
		case KeyEvent.VK_D:
			keyPressedFlag[KEY_D] = false;
			System.out.println("[KeyMapper] D RELEASED");
			break;
		case KeyEvent.VK_ENTER:
			keyPressedFlag[KEY_ENTER] = false;
			enterConfirmed = false;
			System.out.println("[KeyMapper] ENTER RELEASED");
			break;
		case KeyEvent.VK_ESCAPE:
			keyPressedFlag[KEY_ESCAPE] = false;
			escapeConfirmed = false;
			System.out.println("[KeyMapper] ESCAPE RELEASED");
			break;
		default:
			break;
		}
	}
	
	public static boolean isPressed(int key) {
		if (key >= 0 && key < 10) {
			return KeyMapper.keyPressedFlag[key];
		}
		
		System.out.println("[KeyMapper] [KeyMapper] Key yang kamu cek tidak ada");
		return false;
	}
	
	public static void confirmEnter() {
		enterConfirmed = true;
	}
	
	public static void confirmEscape() {
		escapeConfirmed = true;
	}
}
