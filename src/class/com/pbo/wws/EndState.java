package com.pbo.wws;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

public class EndState extends GameState implements Renderable, Exitable
{
	private Image image;
	private Image imageUI;
	
	public EndState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream("res/ui/UserInterface/Pause.png"));
			imageUI = (Image) ImageIO.read(getClass().getResourceAsStream("res/ui/UserInterface/TheEnd.png")).getScaledInstance(300, 46, Image.SCALE_DEFAULT);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init() {

		setVisible(true);
	}

	@Override
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_ENTER){
			quit();
			}
	}

	@Override
	public void keyReleased(int k) {

		
	}

	@Override
	public void quit() {

		setVisible(false);
		GameStateManager.setState(GameStateManager.MENUSTATE);
	}
	
	@Override
	public void render(Graphics g) {

		g.drawImage(image, 0, 0, 1280, 720, null);
		g.drawImage(imageUI,100,50,null);
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
