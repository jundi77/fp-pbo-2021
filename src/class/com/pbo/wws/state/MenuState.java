package com.pbo.wws.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;


public class MenuState extends GameState implements Exitable, MenuChoicable
{
	private Image image;
	private Image[] tombol = new Image[6];
	private int currentChoice = 0;
	
	public MenuState (GameStateManager gsm)
	{
		this.gsm = gsm;
		
		Renderer.addDrawable(this);
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Menu.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/UserInterface/Tombol.png"));
			
			int y=72;
			for(int i = 0; i < 6 ;i++)
			{
				tombol[i] = (Image) imageTombol.getSubimage(0, y, 100, 12);
				tombol[i] = tombol[i].getScaledInstance(300,36, Image.SCALE_DEFAULT);
				y+=12;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void moveChoice() {
		if (KeyMapper.isPressed(KeyMapper.KEY_ENTER)) {
			KeyMapper.confirmEnter();
			selectChoice();
		} else if (KeyMapper.isPressed(KeyMapper.KEY_UP)) {
			KeyMapper.confirmArrow();
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = tombol.length/ 2 - 1;
			}
			if (currentChoice == 1) {
				currentChoice = 0;
			}
		} else if (KeyMapper.isPressed(KeyMapper.KEY_DOWN)) {
			KeyMapper.confirmArrow();
			currentChoice++;
			if(currentChoice == tombol.length/ 2){
				currentChoice = 0;
			}
			if (currentChoice == 1) {
				currentChoice = 2;
			}
		}
	}
	
	public void selectChoice() {
		if(currentChoice == 0){
			setVisible(false);
			GameStateManager.setState(GameStateManager.PLAYSTATE);
		}
		if(currentChoice == 1){
			currentChoice = 2;
		}
		if(currentChoice == 2){
			quit();
		}
	}
	
	@Override
	public void render(Graphics g) {
		moveChoice();

		g.drawImage(image, 0, 0, 1280, 720, null);
		for(int options = 0; options < (tombol.length / 2); options++){
			if (options == 1) continue; // belum ada fitur load
			if(options == currentChoice){
				g.drawImage(tombol[2 * options + 1], 40, 530 + 50 * ((options == 2)? 1 : 0), null);
			} else {
				g.drawImage(tombol[2 * options ], 40, 530 + 50 * ((options == 2)? 1 : 0), null);				
			}
		}
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[MenuState] Pindah ke aku");
		}
		super.setVisible(visible);
	}

	@Override
	public void quit() {
		System.exit(0);
	}
}
