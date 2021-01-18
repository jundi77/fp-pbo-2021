package com.pbo.wws.frame;

import java.awt.Color;

import javax.swing.JFrame;

import com.pbo.wws.GamePanel;

public class Main {
	public static final String resourcePath = "/res/pbo/wws";
	private static int width = 1280, height = 720;
	private static final JFrame window = new JFrame("Wizard Who Speaks");
	
	public static void main(String[] args) 
	{
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setSize(width, height);
		window.setVisible(true);
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setWidthHeight(double zoom) {
		width  *= zoom;
		height *= zoom;
		
		window.setSize(width, height);
	}
}
