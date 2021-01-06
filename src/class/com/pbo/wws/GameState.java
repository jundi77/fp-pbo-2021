package com.pbo.wws;

public abstract class GameState implements Renderable
{
	protected GameStateManager gsm ;
	
	public abstract void init();
	public abstract void keyPressed(int k);
	public abstract void keyRealeased(int k);
}
