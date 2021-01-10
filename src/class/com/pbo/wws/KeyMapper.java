package com.pbo.wws;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.InputMap;

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

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
			keyPressedFlag[KEY_UP] = true;
			System.out.println("UP");
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			keyPressedFlag[KEY_DOWN] = true;
			System.out.println("DOWN");
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			keyPressedFlag[KEY_LEFT] = true;
			System.out.println("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			keyPressedFlag[KEY_RIGHT] = true;
			System.out.println("RIGHT");
			break;
		case KeyEvent.VK_W:
			keyPressedFlag[KEY_W] = true;
			System.out.println("W");
			break;
		case KeyEvent.VK_A:
			keyPressedFlag[KEY_A] = true;
			System.out.println("A");
			break;
		case KeyEvent.VK_S:
			keyPressedFlag[KEY_S] = true;
			System.out.println("S");
			break;
		case KeyEvent.VK_D:
			keyPressedFlag[KEY_D] = true;
			System.out.println("D");
			break;
		case KeyEvent.VK_ENTER:
			keyPressedFlag[KEY_ENTER] = true;
			System.out.println("ENTER");
			break;
		case KeyEvent.VK_ESCAPE:
			keyPressedFlag[KEY_ESCAPE] = true;
			System.out.println("ESCAPE");
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
			System.out.println("UP RELEASED");
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
			keyPressedFlag[KEY_DOWN] = false;
			System.out.println("DOWN RELEASED");
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
			keyPressedFlag[KEY_LEFT] = false;
			System.out.println("LEFT RELEASED");
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
			keyPressedFlag[KEY_RIGHT] = false;
			System.out.println("RIGHT RELEASED");
			break;
		case KeyEvent.VK_W:
			keyPressedFlag[KEY_W] = false;
			System.out.println("W RELEASED");
			break;
		case KeyEvent.VK_A:
			keyPressedFlag[KEY_A] = false;
			System.out.println("A RELEASED");
			break;
		case KeyEvent.VK_S:
			keyPressedFlag[KEY_S] = false;
			System.out.println("S RELEASED");
			break;
		case KeyEvent.VK_D:
			keyPressedFlag[KEY_D] = false;
			System.out.println("D RELEASED");
			break;
		case KeyEvent.VK_ENTER:
			keyPressedFlag[KEY_ENTER] = false;
			System.out.println("ENTER RELEASED");
			break;
		case KeyEvent.VK_ESCAPE:
			keyPressedFlag[KEY_ESCAPE] = false;
			System.out.println("ESCAPE RELEASED");
			break;
		default:
			break;
		}
	}
	
	public static boolean isPressed(int key) {
		if (key >= 0 && key < 10) {
			return KeyMapper.keyPressedFlag[key];
		}
		
		System.out.println("[KeyMapper] Key yang kamu cek tidak ada");
		return false;
	}
}
