package com.pbo.wws.state;

import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public abstract class GameState implements Renderable
{
	protected GameStateManager gsm;
	private boolean visible;

	@Override
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public boolean getVisibility() {
		return this.visible;
	}
}
