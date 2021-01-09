package com.pbo.wws;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BattleState extends GameState implements Renderable, Exitable 
{
	private int currentChoice = 0;
	private Image image;
	private Image[] imageUI = new Image[4];
	
	public BattleState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream("/ui/Kombat/uiKombat.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream("/ui/Kombat/tombolKombat.png"));
			
			int y=0;
			for(int i = 0; i < imageUI.length ;i++)
			{
				imageUI[i] = (Image) imageTombol.getSubimage(0, y, 60, 12);
				imageUI[i] = imageUI[i].getScaledInstance(180,36, Image.SCALE_DEFAULT);
				y+=12;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void selectChoice() {

		
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
		
		for(int options = 0; options < (imageUI.length / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(imageUI[options * 2 + 1], 60, 550 + 50 * options, null);
			}else{
				g.drawImage(imageUI[options * 2], 60, 550 + 50 * options, null);				
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

		
	}
}
