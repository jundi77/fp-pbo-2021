package com.pbo.wws.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.pbo.wws.Exitable;
import com.pbo.wws.MenuChoicable;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public class GameOState extends GameState implements Exitable, MenuChoicable
{
	
	private int currentChoice = 0;
	private Image image;
	private Image[] imageUI = new Image[5];
	
	public GameOState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Kalah.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Tombol.png"));
			
			imageUI[4] = (Image)imageTombol.getSubimage(0, 144, 100, 12).getScaledInstance(300, 36, Image.SCALE_DEFAULT);
			
			int y=156;
			for(int i = 0; i < imageUI.length-1 ;i++)
			{
				imageUI[i] = (Image) imageTombol.getSubimage(0, y, 100, 12);
				imageUI[i] = imageUI[i].getScaledInstance(300,36, Image.SCALE_DEFAULT);
				y+=12;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Renderer.addDrawable(this);
	}
	
	@Override
	public void moveChoice(int keyCode) 
	{
		if(KeyMapper.KEY_ENTER == keyCode){
			System.out.println("ENTER");
			selectChoice();
			}
		if(KeyMapper.KEY_UP == keyCode){
			KeyMapper.confirmArrow();
			System.out.println("UP");
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		}
		if(KeyMapper.KEY_DOWN == keyCode){
			KeyMapper.confirmArrow();
			System.out.println("DOWN");
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		}
	}
	
	public void selectChoice() {
		if(currentChoice == 0){
			setVisible(false);
			GameStateManager.setState(GameStateManager.PLAYSTATE);
		}
		if(currentChoice == 1){
			quit();
		}
	}

	public void render(Graphics g) {
		
		if(KeyMapper.isPressed(KeyMapper.KEY_UP)){
			KeyMapper.confirmArrow();
			moveChoice(KeyMapper.KEY_UP);
		}
		else if(KeyMapper.isPressed(KeyMapper.KEY_DOWN)){
			KeyMapper.confirmArrow();
			moveChoice(KeyMapper.KEY_DOWN);
		}
		else if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			KeyMapper.confirmEnter();
			moveChoice(KeyMapper.KEY_ENTER);
		}

		g.drawImage(image, 0, 0, 1280, 720, null);
		g.drawImage(imageUI[4],50,50,null);
		for(int options = 0; options < ((imageUI.length - 1) / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(imageUI[options * 2 + 1], 50, 125 + 50 * options, null);
			}else{
				g.drawImage(imageUI[options * 2], 50, 125 + 50 * options, null);				
			}
		}
	}
	
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[GameOState] Pindah ke aku");
		}
		super.setVisible(visible);
	}

	@Override
	public void quit() {
		setVisible(false);
		GameStateManager.setState(GameStateManager.MENUSTATE);
	}


}
