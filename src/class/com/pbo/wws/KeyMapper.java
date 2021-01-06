package com.pbo.wws;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.InputMap;

public class KeyMapper implements KeyListener{

	public static final int KEY_UP = KeyEvent.VK_UP;
	public static final int KEY_DOWN = KeyEvent.VK_DOWN;
	public static final int KEY_LEFT = KeyEvent.VK_LEFT;
	public static final int KEY_RIGHT = KeyEvent.VK_RIGHT;
	public static final int KEY_W = KeyEvent.VK_W;
	public static final int KEY_A = KeyEvent.VK_A;
	public static final int KEY_S = KeyEvent.VK_S;
	public static final int KEY_D = KeyEvent.VK_D;
	public static final int KEY_ENTER = KeyEvent.VK_ENTER;
	public static final int KEY_ESCAPE = KeyEvent.VK_ESCAPE;

	private static Stack<KeyWatcher> WASD_listener;
	private static Stack<KeyWatcher> ARROW_listener;
	private static Stack<KeyWatcher> ENTER_listener;
	private static Stack<KeyWatcher> ESC_listener;

	KeyMapper() {
		WASD_listener = new Stack<KeyWatcher>();
		ARROW_listener = new Stack<KeyWatcher>();
		ENTER_listener = new Stack<KeyWatcher>();
		ESC_listener = new Stack<KeyWatcher>();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//		case KeyEvent.VK_KP_UP:
//			keyPressedMap.put(KEY_UP, true);
//			System.out.println("UP");
//			break;
//		case KeyEvent.VK_DOWN:
//		case KeyEvent.VK_KP_DOWN:
//			keyPressedMap.put(KEY_DOWN, true);
//			System.out.println("DOWN");
//			break;
//		case KeyEvent.VK_LEFT:
//		case KeyEvent.VK_KP_LEFT:
//			keyPressedMap.put(KEY_LEFT, true);
//			System.out.println("left");
//			break;
//		case KeyEvent.VK_RIGHT:
//		case KeyEvent.VK_KP_RIGHT:
//			keyPressedMap.put(KEY_RIGHT, true);
//			System.out.println("right");
//			break;
//		case KeyEvent.VK_W:
//			keyPressedMap.put(KEY_W, true);
//			System.out.println("w");
//			break;
//		case KeyEvent.VK_A:
//			keyPressedMap.put(KEY_A, true);
//			System.out.println("a");
//			break;
//		case KeyEvent.VK_S:
//			keyPressedMap.put(KEY_S, true);
//			System.out.println("s");
//			break;
//		case KeyEvent.VK_D:
//			keyPressedMap.put(KEY_D, true);
//			System.out.println("d");
//			break;
//		case KeyEvent.VK_ENTER:
//			keyPressedMap.put(KEY_ENTER, true);
//			break;
//		case KeyEvent.VK_ESCAPE:
//			keyPressedMap.put(KEY_ESCAPE, true);
//			System.out.println("escape");
//			break;
//		default:
//			break;
//		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//		case KeyEvent.VK_KP_UP:
//			keyPressedMap.put(KEY_UP, false);
//			System.out.println("UP");
//			break;
//		case KeyEvent.VK_DOWN:
//		case KeyEvent.VK_KP_DOWN:
//			keyPressedMap.put(KEY_DOWN, false);
//			System.out.println("DOWN");
//			break;
//		case KeyEvent.VK_LEFT:
//		case KeyEvent.VK_KP_LEFT:
//			keyPressedMap.put(KEY_LEFT, false);
//			System.out.println("left");
//			break;
//		case KeyEvent.VK_RIGHT:
//		case KeyEvent.VK_KP_RIGHT:
//			keyPressedMap.put(KEY_RIGHT, false);
//			System.out.println("right");
//			break;
//		case KeyEvent.VK_W:
//			keyPressedMap.put(KEY_W, false);
//			System.out.println("w");
//			break;
//		case KeyEvent.VK_A:
//			keyPressedMap.put(KEY_A, false);
//			System.out.println("a");
//			break;
//		case KeyEvent.VK_S:
//			keyPressedMap.put(KEY_S, false);
//			System.out.println("s");
//			break;
//		case KeyEvent.VK_D:
//			keyPressedMap.put(KEY_D, false);
//			System.out.println("d");
//			break;
//		case KeyEvent.VK_ENTER:
//			keyPressedMap.put(KEY_ENTER, false);
//			break;
//		case KeyEvent.VK_ESCAPE:
//			keyPressedMap.put(KEY_ESCAPE, false);
//			System.out.println("escape");
//			break;
//		default:
//			break;
//		}
	}
}
