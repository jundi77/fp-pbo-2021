package com.pbo.wws;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;


public class MenuState extends GameState implements Renderable
{
	private Image image;
	private Image[] tombol = new Image[6];
	private Graphics g;
	
	//rendereble, quittable, choicable
	
	private int currentChoice = 0;
	
	
	public MenuState (GameStateManager gsm)
	{
		this.gsm = gsm;
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream("/ui/UserInterface/Menu.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream("/ui/UserInterface/Tombol.png"));
			
			int y=60;
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
	
	//GameState
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	private void selectChoice() {
		if(currentChoice == 0){
			//startGame
		}
		if(currentChoice == 1){
			//loadGame(continue)
		}
		if(currentChoice == 2){
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			selectChoice();
		}
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = tombol.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			currentChoice--;
			if(currentChoice == tombol.length){
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyRealeased(int k) {
		// TODO Auto-generated method stub
		
	}
	
	//Rebderable
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 300, 300);
		if(image != null)System.out.println("image is not null");
		g.drawImage(image, 0, 0, 1280, 720, null);
		
		for(int options = 0; options < (tombol.length / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(tombol[2 * options + 1], 100, 675 + 50* options, null);
			}
			g.drawImage(tombol[2 * options], 100, 675 + 50* options, null);
		}
	}
	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
	}
	@Override
	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
