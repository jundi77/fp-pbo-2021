package com.pbo.wws.state;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.pbo.wws.entity.Character.CharacterException;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.FightingCharacter;
import com.pbo.wws.entity.FightingCharacter.FightingCharacterException;
import com.pbo.wws.entity.Player;
import com.pbo.wws.frame.GamePanel;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;
import com.pbo.wws.state.manager.GameStateManager;

public class BattleState extends GameState implements Exitable, MenuChoicable 
{
	private int currentChoice = 1,
				currentTurn = 0,
				fromTile, // 0 itu player, 1 itu enemy
				state = 0,
				selectedSpell = 0, // 0 non listening, 1 listening spell
				listenedWrongDuration,
				currentListenedWrongDuration;
	private Image[] image = new Image[3];
	private Image[] imageUI = new Image[4];
	private BufferedImage mpHud, spellHud, serangBatal, dialogMusuh;
	private Enemy enemy = null;
	private Player player = null;
	private ArrayList<String> playerSpell;
	private String listenedWrong;
	
	private int currentLevel;

	@SuppressWarnings("serial")
	public BattleState (GameStateManager gsm) 
	{
		this.gsm = gsm;
		this.listenedWrongDuration = Ticker.getRatePerSecond() * 3;
		this.currentListenedWrongDuration = 0;
		this.listenedWrong = null;
		this.currentLevel = 0;
		
		try{
			image[0] = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKombat1.png"));
			image[1] = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKombat2.png"));
			image[2] = (Image) ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiKombat3.png"));
			
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
			serangBatal = ImageIO.read(getClass()
					.getResourceAsStream(Main.resourcePath + "/ui/Kombat/uiSerangBatal.png"));
			dialogMusuh = ImageIO.read(getClass()
					.getResourceAsStream(Main.resourcePath + "/ui/Kombat/MonsterFail.png"));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		this.playerSpell = new ArrayList<String>() {{
			add("armor");
			add("burning");
			add("college");
			add("defense");
			add("element");
		}};

		Renderer.addDrawable(this);
	}

	public void selectChoice() {
		switch (this.currentChoice) {
		case 0: // sihir
			if (this.state == 0) {
				// masuk ke mode listen
				selectedSpell = (int) ((Math.random() * 10)  % this.playerSpell.size());
				while (player.getSpells().get(playerSpell.get(selectedSpell))[0] > player.getMp()) {
					selectedSpell = ++selectedSpell % playerSpell.size();
				}
				this.state = 1;
				GameStateManager.speech.listen(this.playerSpell.get(selectedSpell));
			} else if (this.state == 1) {
				// keluar mode listen
				System.out.println("[BS] batal spell");
				GameStateManager.speech.stopListen();
				this.state = 0;
			}
			break;
		// serang
		case 1:
			if (this.state == 0) {
				if (currentTurn == 0) {
					player.attack("default", enemy);
					enemy.playAnimation("damaged");
				}
				if (enemy.getHealth() <= 0) {
					enemy.playAnimation("mati");
				} else {			
					currentTurn = 1;
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void moveChoice() {
		if(KeyMapper.isPressed(KeyMapper.KEY_ENTER)){
			KeyMapper.confirmEnter();
			selectChoice();
		} else if(KeyMapper.isPressed(KeyMapper.KEY_UP) && this.state == 0){
			KeyMapper.confirmArrow();
			currentChoice--;
			if (player.getMp() <= 0) {
				currentChoice = 1;
			} else if(currentChoice == -1){
				currentChoice = imageUI.length/ 2 - 1;
			}
		} else if(KeyMapper.isPressed(KeyMapper.KEY_DOWN) && this.state == 0){
			KeyMapper.confirmArrow();
			currentChoice++;
			if (player.getMp() <= 0) {
				currentChoice = 1;
			} else if(currentChoice == imageUI.length/ 2){
				currentChoice = 0;
			}
		} else if (KeyMapper.isPressed(KeyMapper.KEY_ESCAPE)) {
			quit();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image[currentLevel], 0, 0, 1280, 720, null);
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
			if (enemy.getHealth() <= 0) {
				((PlayState) gsm.getState(gsm.PLAYSTATE)).killedMonsterAt(this.fromTile);
				if(PlayState.currentLevel != 2){
					GameStateManager.setState(GameStateManager.PLAYSTATE);
				}
			} else if (player.getHealth() <= 0) {
				((GameOState) GameStateManager.getState(GameStateManager.GAMEOSTATE)).setWin(false);
				try {
					enemy.setHealth(enemy.getFullHealth());
					enemy.resetEnemy();
					player.setHealth(player.getFullHealth());
					player.setMp(player.getFullMp());
				} catch (CharacterException e) {
					e.printStackTrace();
				}

				((PlayState) GameStateManager.getState(GameStateManager.PLAYSTATE)).resetPlay();
				
				GameStateManager.setState(GameStateManager.GAMEOSTATE);
				
			}
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
		} else if (state == 0){
			enemy.attack("default", player);
			enemy.playAnimation("serang");
			currentTurn = 0;
		}

		if (this.state == 0)
		for(int options = 0; options < (imageUI.length / 2); options++)
		{
			if (options == 1 && player.getMp() <= 0) continue;
			if(options == currentChoice) {
				g.drawImage(imageUI[options * 2 + 1], 60, 550 + 50 * options, null);
			} else {
				g.drawImage(imageUI[options * 2], 60, 550 + 50 * options, null);				
			}
		}
		
		else if (this.state == 1) {
			// spell atttack

			g.drawImage(serangBatal, 60, 550, 300, 592, 0, 0, 60, 21 / 2, null);
			g.drawImage(spellHud, Main.getWidth() / 2 - 160 * 2 - 80, 70,  Main.getWidth() / 2 + 160 * 2 - 80, 50 * 4 + 70, null, null);
			g.setColor(Color.black);
			g.setFont(GamePanel.getCoolFont());
			g.drawString(this.playerSpell.get(selectedSpell), Main.getWidth() / 2 - 90, Main.getHeight() / 2 - 130);
			
			if (listenedWrong != null) {
				g.drawImage(dialogMusuh, Main.getWidth() / 2 + 70, Main.getHeight() / 2 - 30, dialogMusuh.getWidth() * 3, dialogMusuh.getHeight() * 3, null);
				g.setFont(GamePanel.getCoolFont().deriveFont(50f));
				g.drawString(listenedWrong + "??", Main.getWidth() / 2 + 90, Main.getHeight() / 2 - 10);
				if (this.currentListenedWrongDuration <= this.listenedWrongDuration) {
					this.currentListenedWrongDuration++;
				} else {
					this.listenedWrong = null;
					this.currentListenedWrongDuration = 0;
				}
				
			}
		}

		// config musuh kritis
		if (enemy.getHealth() <= 25) {
			g.setXORMode(Color.black);
		}
		enemy.render(g);
		g.setPaintMode();

		if (listenedWrong != null) {
			g.drawImage(dialogMusuh, Main.getWidth() / 2 + 70, Main.getHeight() / 2 - 30, dialogMusuh.getWidth() * 3, dialogMusuh.getHeight() * 3, null);
			g.setFont(GamePanel.getCoolFont().deriveFont(50f));
			g.drawString(listenedWrong + "??", Main.getWidth() / 2 + 90, Main.getHeight() / 2 - 10);
			if (this.currentListenedWrongDuration <= this.listenedWrongDuration) {
				this.currentListenedWrongDuration++;
			} else {
				this.listenedWrong = null;
				this.currentListenedWrongDuration = 0;
			}
		}

		// overlay blood transparent
		g.setColor(
				new Color(255, 0, 0, (int) ((player.getFullHealth() - player.getHealth()) / (float) player.getFullHealth() * 100))
		);
		g.fillRect(0, 0, Main.getWidth(), Main.getHeight());
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[BattleState] Pindah ke aku");
			currentLevel = PlayState.currentLevel;
		}
		super.setVisible(visible);
	}

	public FightingCharacter getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy, int fromTile) {
		this.enemy = enemy;
		this.fromTile = fromTile;
	}

	public Player getPlayer() {
		return player;
	}

	@SuppressWarnings("serial")
	public void setPlayer(Player player) throws FightingCharacterException {
		this.player = player;

		player.setHealth(player.getFullHealth());
		player.setMp(player.getFullMp());
		this.playerSpell = new ArrayList<String>() {{
			add("armor");
			add("burning");
			add("college");
			add("defense");
			add("element");
		}};
	}

	public void confirmSpell() {
		System.out.println("[BS] Confirmed spell");
		GameStateManager.speech.stopListen();
		player.setMp(player.getMp() - player.getSpells().get(this.playerSpell.get(selectedSpell))[0]);

		if (player.getMp() <= 0) {
			this.currentChoice = 1;
		}

		try {
			enemy.setHealth(enemy.getHealth() - player.getSpells().get(this.playerSpell.get(selectedSpell))[1]);
			if (enemy.getHealth() <= 0) {
				enemy.playAnimation("mati");
			} else {
				enemy.playAnimation("damaged");				
			}
			this.currentTurn = 1;
		} catch (FightingCharacterException e) {
			e.printStackTrace();
		}
		this.state = 0;
	}

	@Override
	public void quit() {
		((PauseState) GameStateManager.getState(GameStateManager.PAUSESTATE)).setResumeTo(GameStateManager.BATTLESTATE);
		GameStateManager.setState(GameStateManager.PAUSESTATE);
	}

	public void wrongSpell(String listenedWrong) {
		this.listenedWrong = listenedWrong;
	}
}
