package com.pbo.wws.state;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.pbo.wws.Exitable;
import com.pbo.wws.MenuChoicable;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.FightingCharacter;
import com.pbo.wws.entity.FightingCharacter.FightingCharacterException;
import com.pbo.wws.entity.Player;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public class BattleState extends GameState implements Exitable, MenuChoicable 
{
	private int currentChoice = 1,
				currentTurn = 0; // 0 itu player, 1 itu enemy
	private Image image;
	private Image[] imageUI = new Image[4];
	private BufferedImage mpHud, spellHud;
	private Enemy enemy = null;
	private Player player = null;

	public BattleState (GameStateManager gsm) 
	{
		this.gsm = gsm;

		try{
			image = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKombat.png"));
			BufferedImage imageTombol = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/tombolKombat.png"));
			
			int y=0;
			for(int i = 0; i < imageUI.length ;i++)
			{
				imageUI[i] = (Image) imageTombol.getSubimage(0, y, 60, 12);
				imageUI[i] = imageUI[i].getScaledInstance(180,36, Image.SCALE_DEFAULT);
				y+=12;
			}
			
			mpHud = ImageIO.read(getClass()
					.getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKarakterMP.png"));
			spellHud = ImageIO.read(getClass()
					.getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiAttackSpecial.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Renderer.addDrawable(this);
	}

	public void selectChoice() {
		switch (this.currentChoice) {
		case 0: // sihir
//			if ()
			break;
		// serang
		case 1:
			if (currentTurn == 0) {
				player.attack("default", enemy);
				enemy.playAnimation("damaged");
			}
			break;
		default:
			break;
		}
		
		if (enemy.getHealth() <= 0) {
			enemy.playAnimation("mati");
		} else {			
			currentTurn = 1;
		}
	}

	@Override
	public void moveChoice() {
		if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			KeyMapper.confirmEnter();
			selectChoice();
		} else if(KeyMapper.isPressed(KeyMapper.KEY_UP)){
			KeyMapper.confirmArrow();
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		} else if(KeyMapper.isPressed(KeyMapper.KEY_DOWN)){
			KeyMapper.confirmArrow();
			currentChoice++;
			if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		} else if (KeyMapper.isPressed(KeyMapper.KEY_ESCAPE)) {
			quit();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, 0, 0, 1280, 720, null);
		enemy.setX(Main.getWidth() / 2);
		enemy.setY(Main.getHeight() / 2 + 100);
		enemy.setWidth(250);
		enemy.setHeight(250);

		// overlay mp player
		if (player.getMp() > 0) {
			int ratioMp = (int) ((player.getFullMp() - player.getMp()) / (float) player.getFullMp() * 5);
			g.drawImage(mpHud,
					Main.getWidth() / 2 - 40, 0,
					Main.getWidth() / 2 + 40, 80,
					ratioMp * 32, 0,
					(ratioMp + 1) * 32, 32,
					null
			);			
		}

		if (!enemy.isAnimating()) {
			enemy.playAnimation("ready");
		} else if (enemy.isAnimating() && !enemy.getCurrentAnimationState().equalsIgnoreCase("ready")){
			// config musuh kritis
			if (enemy.getHealth() <= 25) {
				g.setXORMode(Color.black);
			}
			enemy.render(g);
			g.setPaintMode();

			// overlay blood transparent
			g.setColor(
					new Color(255, 0, 0, (int) ((player.getFullHealth() - player.getHealth()) / (float) player.getFullHealth() * 100))
			);
			g.fillRect(0, 0, Main.getWidth(), Main.getHeight());

			// tidak perlu tampilkan pilihan menu ataupun menerima input
			return;
		}

		if (currentTurn == 0) {
			moveChoice();			
		} else {
			enemy.attack("default", player);
			enemy.playAnimation("serang");
			currentTurn = 0;
		}

		for(int options = 0; options < (imageUI.length / 2); options++)
		{
			if(options == currentChoice) {
				g.drawImage(imageUI[options * 2 + 1], 60, 550 + 50 * options, null);
			} else {
				g.drawImage(imageUI[options * 2], 60, 550 + 50 * options, null);				
			}
		}

		// config musuh kritis
		if (enemy.getHealth() <= 25) {
			g.setXORMode(Color.black);
		}
		enemy.render(g);
		g.setPaintMode();
		
		// overlay blood transparent
		g.setColor(
				new Color(255, 0, 0, (int) ((player.getFullHealth() - player.getHealth()) / (float) player.getFullHealth() * 100))
		);
		g.fillRect(0, 0, Main.getWidth(), Main.getHeight());
		
		// special attack
//		g.drawImage(spellHud, Main.getWidth() / 2 - 160 * 2 - 80, 70,  Main.getWidth() / 2 + 160 * 2 - 80, 50 * 4 + 70, null, null);
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[BattleState] Pindah ke aku");
		}
		super.setVisible(visible);
	}

	public FightingCharacter getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void quit() {
		// TODO arahkan ke pause
	}
}
