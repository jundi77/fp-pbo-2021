package com.pbo.wws;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

import com.pbo.wws.frame.Main;

public abstract class Scene {
	private JLayeredPane layers = new JLayeredPane();
	
	public Scene(Main window) {
		// TODO
//		this.layers.setPreferredSize(new Dimension(window.getWidth(), window.getWindowHeight()));
	}
	
	private void addLayer(int layer, Component component) {
		// TODO
//		layers.add(component, layer);
	}
}
