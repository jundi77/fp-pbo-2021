package com.pbo.wws.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.pbo.wws.Exitable;
import com.pbo.wws.MenuChoicable;
import com.pbo.wws.Renderable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;

public class PauseState extends GameState implements Renderable, Exitable, MenuChoicable
{
	
	private int currentChoice = 0;
	private Image image;
	private Image[] imageUI = new Image[5];
	
	public PauseState(GameStateManager gsm){
		
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Pause.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Tombol.png"));
			
			imageUI[4] = (Image)imageTombol.getSubimage(0, 0, 100, 12).getScaledInstance(300, 36, Image.SCALE_DEFAULT);
			
			int y=12;
			for(int i = 0; i < imageUI.length ;i++)
			{
				imageUI[i] = (Image) imageTombol.getSubimage(0, y, 100, 12);
				imageUI[i] = imageUI[i].getScaledInstance(300,36, Image.SCALE_DEFAULT);
				y+=12;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void moveChoice(int keyCode) {
		
		if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			selectChoice();
			}
		if(KeyMapper.isPressed(KeyMapper.KEY_UP)){
			System.out.println("Ke atas");
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		}
		if(KeyMapper.isPressed(KeyMapper.KEY_DOWN)){
			System.out.println("Ke bawah");
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		}
		
	}
	
	public void selectChoice() {
		if(currentChoice == 0){
			setVisible(false);
			//if(array monster kosong)
			GameStateManager.setState(GameStateManager.PLAYSTATE);
			//else if (jika array monster ada)
			GameStateManager.setState(GameStateManager.BATTLESTATE);
		}
		if(currentChoice == 1){
			quit();
		}
	}
	
	@Override
	public void init() {
		
		setVisible(true);
	}

	@Override
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
		g.drawImage(imageUI[4],100,50,null);
		for(int options = 0; options < ((imageUI.length - 1) / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(imageUI[options * 2 + 1], 100, 300 + 50 * options, null);
			}else{
				g.drawImage(imageUI[options * 2], 100, 300 + 50 * options, null);				
			}
		}
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

	@Override
	public void quit() {

		setVisible(false);
		GameStateManager.setState(GameStateManager.MENUSTATE);
	}



}
