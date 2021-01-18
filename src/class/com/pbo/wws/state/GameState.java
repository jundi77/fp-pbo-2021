package com.pbo.wws.state;

import com.pbo.wws.state.manager.GameStateManager;

public abstract class GameState
{
	protected GameStateManager gsm;
	
	public abstract void init();
}
