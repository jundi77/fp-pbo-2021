package com.pbo.wws.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.pbo.wws.Exitable;
import com.pbo.wws.MenuChoicable;
import com.pbo.wws.Renderable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;

public class BattleState extends GameState implements Renderable, Exitable, MenuChoicable
{
	private int currentChoice = 0;
	private Image image;
	private Image[] imageUI = new Image[4];
	private boolean turn = true;
	
	static Image[] karakterMP = new Image[5];
	
//	Gambar monster, tidak menggunakan array of Image 2 dimensi dikarnakan tiap pergerakan, jumlah frame bervariasi
	
	static Image[] monsterReady = new Image[5];
	static Image[] monsterMati = new Image[8];
	static Image[] monsterSerang = new Image[15];
	static Image[] monsterDamage = new Image[3];
	
//	ArrayList untuk monster, "HARAP PERBAIKI TIPE DATANYA"	
	
	private ArrayList <Integer> monster = new ArrayList<>();

	public BattleState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKombat.png"));
			
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/tombolKombat.png"));
			
			BufferedImage imageKMP = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKarakterMP.png"));
			
			//4 gerakan monster: menyerang, diserang, mati, ready
			BufferedImage imageMMati = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/entity/monster/monsterMati.png"));
			BufferedImage imageMReady = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/entity/monster/monsterReady.png"));
			BufferedImage imageMSerang = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/entity/monster/monsterSerang.png"));
			BufferedImage imageMDamage = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/entity/monster/monsterDamaged.png"));

			fetchMonster(imageMReady, monsterReady);
			fetchMonster(imageMMati, monsterMati);
			fetchMonster(imageMSerang, monsterSerang);
			fetchMonster(imageMDamage, monsterDamage);
			
			int y = 0;
			for(int i = 0; i < imageUI.length ;i++)
			{
				imageUI[i] = (Image) imageTombol.getSubimage(0, y, 60, 12);
				imageUI[i] = imageUI[i].getScaledInstance(180,36, Image.SCALE_DEFAULT);
				y+=12;
			}
			
			int x = 0;
			for(int i = 0; i < karakterMP.length ; i++ )
			{
				karakterMP[i] = (Image) imageKMP.getSubimage(x, 0, 32, 32);
				karakterMP[i] = karakterMP[i].getScaledInstance(125,125, Image.SCALE_DEFAULT);
				x +=32;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fetchMonster(BufferedImage imageMonster, Image[] elemenMonster)
	{
		int x = 0;
		for(int i = 0; i < elemenMonster.length; i++)
		{
			elemenMonster[i] = (Image) imageMonster.getSubimage(x, 0, 32, 32);
			elemenMonster[i] = elemenMonster[i].getScaledInstance(160,160, Image.SCALE_DEFAULT);
			x +=32;
		}
	}
	
	public boolean gameFinished(){
		if(monster.isEmpty() == true)
			return true;			
		else
			return false;
	}
	
	public void selectChoice() {
		if(currentChoice == 0){
			//serangan spesial
			//atribut karakter(MP) berkurang
		}
		if(currentChoice == 1){
			//serangan biasa
			//atribut karakter(MP) berkurang
		}
	}

	@Override
	public void moveChoice(int keyCode) {
		
		if(keyCode == KeyMapper.KEY_ENTER){
			selectChoice();
			}
		if(keyCode == KeyMapper.KEY_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		}
		if(keyCode == KeyMapper.KEY_DOWN){
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		}
	}
	

	@Override
	public void init() {
	
		setVisible(true);
		
		if(gameFinished()){
			this.monster.add(1);
		}
			
	}
	
	@Override
	public void render(Graphics g) {
		
		if(turn == true){
			if(KeyMapper.isPressed(KeyMapper.KEY_UP)){
				KeyMapper.confirmArrow();
				moveChoice(KeyMapper.KEY_UP);
			}if(KeyMapper.isPressed(KeyMapper.KEY_DOWN)){
				KeyMapper.confirmArrow();
				moveChoice(KeyMapper.KEY_DOWN);
			}if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
				//enter, render->moveChoice->selectChoice->karakter menyerang panggil methode serangan	
				KeyMapper.confirmEnter();
				moveChoice(KeyMapper.KEY_ENTER);
				turn = false; //biar ngelock key input di sini hingga gambar monster nyerang dah ke render
			}
		}
		else if(turn==false){
			//monster nyerang ->atribut karakter (HP) berkurang
			//menggunakan RNG dengan rentang tertentu
		}
		
		g.drawImage(image, 0, 0, 1280, 720, null);
		
		
		//jika darah dalam rentang nilai tertentu color = "new Color(255, (100 - 0), (100 - 0, (100 - 0))"
		
//		if(true){
//			g.setColor(new Color(255, 100, 100, 100));
//			g.fillRect(0, 0, 1280, 720);
//		}
		
		//draw monster diserang dan render monster nyerang karakter 
		
		for(int options = 0; options < (imageUI.length / 2); options++)
		{
			if(options == currentChoice)
			{
				g.drawImage(imageUI[options * 2 ], 60, 550 + 50 * options, null);
			}else{
				g.drawImage(imageUI[options * 2 + 1], 60, 550 + 50 * options, null);				
			}
		}
		
		g.drawImage(karakterMP[0], 40, 50, null);
		
		//jika monsterHP sudah habis render gambar monster mati dan langsung lempar ke quit()
		
//		if(true){
//			monster.remove(0);
//			setVisible(false);
//			GameStateManager.setState(GameStateManager.PLAYSTATE);
//		}
		
		//pindah ke pausestate
		
		if(KeyMapper.isPressed(KeyMapper.KEY_ESCAPE)){
			KeyMapper.confirmEscape();
			quit();
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
		GameStateManager.setState(GameStateManager.PAUSESTATE);
	}

}
