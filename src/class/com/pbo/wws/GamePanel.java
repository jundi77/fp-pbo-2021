package com.pbo.wws;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements KeyListener
{
	Renderer renderer = new Renderer();
	KeyMapper keymapper = new KeyMapper();
	
	//dimensions
	public static final int menuWidth = 200;
	public static final int menuHeight = 150;
	
	//gameStateManager
	private GameStateManager gsm;
	
	public GamePanel()
	{
		setLayout(new GridLayout(1, 1, 0, 0));
		setFocusable(true);
		requestFocus();
		setVisible(true);
		this.add(renderer);
		Ticker.start();
		Ticker.addActionListener(renderer);
		init();
		addKeyListener(this);
	}

	//Sudah ada dikelas renderer
	
	private void init() 
	{
		gsm = new GameStateManager();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		gsm.keyPressed(k.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		gsm.keyReleased(k.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}