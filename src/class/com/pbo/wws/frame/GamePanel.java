package com.pbo.wws.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

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
	private static Font customFont;

	//gameStateManager
	private GameStateManager gsm;
	
	public GamePanel()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			GamePanel.customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream(Main.resourcePath + "/ui/font/PixelGameFont.ttf")).deriveFont(70f);
			ge.registerFont(GamePanel.customFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

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
	
	public static Font getCoolFont() {
		return GamePanel.customFont;
	}

	public static int getZoom() {
		return ZOOM;
	}
	
	public static void setZoom(int zoom) {
		ZOOM = zoom;
	}
}