package com.pbo.wws;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel
{
	Renderer renderer = new Renderer();
	
	//dimensions
	public static final int menuWidth = 200;
	public static final int menuHeight = 150;
	
	//gameStateManager
	private GameStateManager gsm;
	
	public Menu()
	{
		setLayout(new GridLayout(1, 1, 0, 0));
		setFocusable(true);
		requestFocus();
		setVisible(true);
		this.add(renderer);

		Ticker.addActionListener(renderer);
		init();
	}

	//Sudah ada dikelas renderer
	
	private void init() 
	{
		gsm = new GameStateManager();
	}
}
