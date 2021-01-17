package com.pbo.wws;

import java.awt.GridLayout;
import javax.swing.JPanel;

import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;
import com.pbo.wws.state.GameStateManager;

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
		setFocusable(true);
		requestFocus();

		setLayout(new GridLayout(1, 1, 0, 0));
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