package com.pbo.wws;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) 
	{
		new Main();
//		JFrame window = new JFrame("Wizard Who Speaks");
//		window.setContentPane(new GamePanel());
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setResizable(false);
//		window.pack();
//		window.setSize(new Dimension(1280, 720));
//		window.setVisible(true);
		
	}

	public Main() {
		java.net.URL a = getClass().getResource("/res/pbo/wws/ui/Kombat/tombolKombat.png");
		System.out.println(a);
	}
}
