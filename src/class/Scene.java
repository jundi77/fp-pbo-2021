import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

public abstract class Scene {
	private JLayeredPane layers = new JLayeredPane();
	
	public Scene(Main window) {
		this.layers.setPreferredSize(new Dimension(window.getWidth(), window.getWindowHeight()));
	}
	
	private void addLayer(int layer, Component component) {
		layers.add(component, layer);
	}
}
