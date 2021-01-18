package com.pbo.wws;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;
import com.pbo.wws.state.manager.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	Renderer renderer = new Renderer();
	KeyMapper keyMapper = new KeyMapper();
	private static int ZOOM = 1;
	
	//gameStateManager
	private GameStateManager gsm;
	
	public GamePanel()
	{
		setSize(Main.getWidth(), Main.getHeight());
		setBackground(Color.DARK_GRAY);
		setLayout(new GridLayout(1, 1, 0, 0));
		setFocusable(true);
		requestFocus();

		addKeyListener(keyMapper);
		add(renderer);
		
		Ticker.start();
		Ticker.addActionListener(renderer);
		setVisible(true);
		init();
	}
	
	private void init() 
	{
		gsm = new GameStateManager();
	}
	
	public static int getZoom() {
		return ZOOM;
	}
	
	public static void setZoom(int zoom) {
		ZOOM = zoom;
	}
}