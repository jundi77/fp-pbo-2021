package com.pbo.wws;

public abstract class GameState
{
	protected GameStateManager gsm ;
	
	public abstract void init();
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
