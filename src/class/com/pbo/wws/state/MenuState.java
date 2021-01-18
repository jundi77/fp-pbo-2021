package com.pbo.wws.state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.pbo.wws.Exitable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;


public class MenuState extends GameState implements Renderable, Exitable
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
	
	
	private void selectChoice() {
		if(currentChoice == 0){
			setVisible(false);
			GameStateManager.setState(GameStateManager.PLAYSTATE);
		}
		if(currentChoice == 1){
			//loadGame(continue)
		}
		if(currentChoice == 2){
			quit();
		}
	}
	
	//GameState
	
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
				currentChoice = tombol.length/ 2 - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			System.out.println("Ke bawah");
			currentChoice++;
			if(currentChoice == tombol.length/ 2){
				currentChoice = 0;
			}
		}
		
		//Dev Key To BattleState
		
		if(k == KeyEvent.VK_B){
			setVisible(false);
			GameStateManager.setState(GameStateManager.BATTLESTATE);
		}
		
		//Dev Key To GameOState
		if(k == KeyEvent.VK_G){
			setVisible(false);
			GameStateManager.setState(GameStateManager.GAMEOSTATE);
		}
		
		//Dev Key To EndState
		if(k == KeyEvent.VK_E){
			setVisible(false);
			GameStateManager.setState(GameStateManager.ENDSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		
	}
	
	@Override
	public void render(Graphics g) {

		g.drawImage(image, 0, 0, 1280, 720, null);
		
		for(int options = 0; options < (tombol.length / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(tombol[2 * options + 1], 100, 300 + 50 * options, null);
			}else{
				g.drawImage(tombol[2 * options ], 100, 300 + 50 * options, null);				
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

		System.exit(0);
	}
	
}
