package com.pbo.wws;

import java.awt.Graphics;

public interface Renderable {
	public void render(Graphics g);
	public void setVisible(boolean visible);
	public boolean getVisibility();
}
