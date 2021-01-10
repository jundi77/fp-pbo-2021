package com.pbo.wws;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class GameOState extends GameState implements Renderable, Exitable{
	
	private int currentChoice = 0;
	private Image image;
	private Image[] imageUI = new Image[5];
	
	public GameOState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream("/ui/UserInterface/Kalah.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream("/ui/UserInterface/Tombol.png"));
			
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
	}
	private void selectChoice() {
		if(currentChoice == 0){
			setVisible(false);
			GameStateManager.setState(GameStateManager.PLAYSTATE);
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
	public void keyPressed(int k) {

		if(k == KeyEvent.VK_ENTER){
			selectChoice();
			}
		if(k == KeyEvent.VK_UP){
			System.out.println("Ke atas");
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			System.out.println("Ke bawah");
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		}
		
	}

	@Override
	public void keyReleased(int k) {
		
	}
	@Override
	public void render(Graphics g) {

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
