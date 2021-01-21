package com.pbo.wws.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;

import com.pbo.wws.frame.GamePanel;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public class EndState extends GameState implements Exitable
{
	private Image image;
	private Image imageUI;
	
	public EndState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Pause.png"));
			imageUI = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/TheEnd.png")).getScaledInstance(300, 46, Image.SCALE_DEFAULT);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		Renderer.addDrawable(this);
	}

	@Override
	public void quit() {
		setVisible(false);
		((PlayState) gsm.getState(gsm.PLAYSTATE)).reset();
		GameStateManager.setState(GameStateManager.MENUSTATE);
	}
	
	@Override
	public void render(Graphics g) {
		if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			KeyMapper.confirmEnter();
			quit();
			}
		
		g.drawImage(image, 0, 0, 1280, 720, null);
		g.drawImage(imageUI,100,50,null);
		g.setColor(Color.white);
		g.setFont(GamePanel.getCoolFont().deriveFont(25f));
		g.drawString("GOOD JOB,", 100, Main.getHeight() - 130);
		g.drawString("ENTER TO CONTINUE", 100, Main.getHeight() - 100);
	}
	
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[EndState] Pindah ke aku");
		}
		super.setVisible(visible);
	}
}
