package com.pbo.wws;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState implements  Renderable
{
	
	public PlayState (GameStateManager gsm)
	{
		
		this.gsm = gsm;
		
		try{
			
		}catch(Exception e){
			
		}
		
	}
	
	@Override
	public void init() {

		System.out.println("state changed!");
		setVisible(true);
	}

	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_ESCAPE){
			setVisible(false);
			GameStateManager.setState(GameStateManager.PAUSESTATE);
			}
	}

	@Override
	public void keyReleased(int k) {

		
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.drawRect(100, 100, 300, 300);
		
	}

	@Override
	public void setVisible(boolean visible) {
		if(visible == false)
			Renderer.removeDrawable(this);
		else
			Renderer.addDrawable(this);
	}

	@Override
	public boolean getVisibility() {

		return false;
	}

}
