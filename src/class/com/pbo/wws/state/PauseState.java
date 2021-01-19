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

public class PauseState extends GameState implements Exitable, MenuChoicable
{
	
	private int currentChoice = 0, resumeTo = GameStateManager.PLAYSTATE;
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
		
		Renderer.addDrawable(this);
	}

	public int getResumeTo() {
		return resumeTo;
	}

	public void setResumeTo(int resumeTo) {
		this.resumeTo = resumeTo;
	}

	@Override
	public void moveChoice() {
		if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			KeyMapper.confirmEnter();
			selectChoice();
			}
		if(KeyMapper.isPressed(KeyMapper.KEY_UP)){
			KeyMapper.confirmArrow();
			System.out.println("Ke atas");
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		}
		if(KeyMapper.isPressed(KeyMapper.KEY_DOWN)){
			KeyMapper.confirmArrow();
			System.out.println("Ke bawah");
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		}
	}

	public void selectChoice() {
		switch (currentChoice) {
		case 0:
			GameStateManager.setState(this.resumeTo);
			break;
		case 1:
			quit();
			break;
		default:
			break;
		}
	}

	@Override
	public void render(Graphics g) {
		moveChoice();
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
		if (visible) {
			System.out.println("[PauseState] Pindah ke aku");
		}
		super.setVisible(visible);
	}

	@Override
	public void quit() {
		((PlayState) gsm.getState(gsm.PLAYSTATE)).reset();
		GameStateManager.setState(GameStateManager.MENUSTATE);
	}
}
