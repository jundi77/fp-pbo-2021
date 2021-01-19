package com.pbo.wws.io;

import java.awt.Graphics;

public interface Renderable {
	public void render(Graphics g);
	public void setVisible(boolean visible);
	public boolean getVisibility();
	
	public default int getX() {
		return 0;
	}
	
	public default int getY() {
		return 0;
	}
	
	public default void setX(int x) {
		return;
	}
	
	public default void setY(int y) {
		return;
	}
}
