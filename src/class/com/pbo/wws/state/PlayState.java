package com.pbo.wws.state;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.pbo.wws.entity.Character.CharacterException;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.Movable;
import com.pbo.wws.entity.Place;
import com.pbo.wws.entity.Player;
import com.pbo.wws.frame.GamePanel;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.io.Ticker;
import com.pbo.wws.state.manager.GameStateManager;

public class PlayState extends GameState implements Exitable
{
	private Place[] p = new Place [3];
	private Player c;
	private Enemy[] e;
	private final String[] L = {"Level1","Level2","Level3"};
	private int transitionDuration, currentTransitionDuration;
	
	static int currentLevel;

	@SuppressWarnings("serial")
	public PlayState (GameStateManager gsm)
	{
		this.gsm = gsm;
		this.transitionDuration = (int) (Ticker.getRatePerSecond() * 1.5);
		this.currentTransitionDuration = 1;

		this.reset();
		Renderer.addDrawable(this);
	}

	public void reset() {
		
		this.currentLevel = 0;
		
		try {
			c = new Player("main", "spriteUtama(32x32).png", 120, 120, 32, 32, Main.getWidth() / 2, Main.getHeight() / 2);
		} catch (CharacterException e1) {
			e1.printStackTrace();
		}
		
			reset(currentLevel);
			
	}

	public void reset(int currentLevel) {
//		TODO generate dan reset enemy perlevel.
		
		try {
			p[currentLevel] = new Place(L[currentLevel], new HashMap<Integer, Integer[]>(){{
				// arena
				put(0, new Integer[] { Place.WALL_UP, Place.WALL_LEFT });
				put(1, new Integer[] {Place.WALL_UP});
				put(2, new Integer[] {Place.WALL_UP, Place.WALL_RIGHT});
				put(12, new Integer[] {Place.WALL_LEFT});
				put(14, new Integer[] {Place.WALL_RIGHT});
				put(24, new Integer[] {Place.WALL_DOWN, Place.WALL_LEFT});
				put(25, new Integer[] {Place.WALL_DOWN});
				put(26, new Integer[] {Place.WALL_DOWN, Place.WALL_RIGHT});
				
				// tembok
				put(36, new Integer[] {Place.IS_WALL});
				put(37, new Integer[] {Place.IS_WALL});
				put(38, new Integer[] {Place.IS_WALL});
				put(60, new Integer[] {Place.IS_WALL});
				put(62, new Integer[] {Place.IS_WALL});
				put(44, new Integer[] {Place.IS_WALL});
				
				// batasan gang kecil
				put(23, new Integer[] {Place.WALL_LEFT, Place.WALL_RIGHT}); // gang ke bawah
				put(35, new Integer[] {Place.WALL_LEFT, Place.WALL_RIGHT});
				put(81, new Integer[] {Place.WALL_UP, Place.WALL_DOWN}); //  gang ke samping
				put(82, new Integer[] {Place.WALL_UP, Place.WALL_DOWN});
				
				// batasan tangga
				put(64, new Integer[] {Place.WALL_LEFT});
				put(65, new Integer[] {Place.WALL_RIGHT});
				
				// checkpoint ??
				put(66, new Integer[] {Place.IS_CHECKPOINT});
			}}, 82, 0, 0, c);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch (currentLevel){
		
		case 0 :
			try {
				e = generateEnemy();
				p[currentLevel].addEnemy(e[0], 65);
				p[currentLevel].addEnemy(e[1], 31);
				p[currentLevel].addEnemy(e[2], 218);
				p[currentLevel].addEnemy(e[3], 266);
				
				e[0].setDirection(Movable.DIRECTION_DOWN);
				e[1].setDirection(Movable.DIRECTION_RIGHT);
				e[2].setDirection(Movable.DIRECTION_UP);
				e[3].setDirection(Movable.DIRECTION_DOWN);
				
			} catch (CharacterException | IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			break;
			
		case 1 :
			
			try {
				e = generateEnemy();
				p[currentLevel].addEnemy(e[0], 173);
				p[currentLevel].addEnemy(e[1], 71);
				p[currentLevel].addEnemy(e[2], 183);
				p[currentLevel].addEnemy(e[3], 230);
				
				e[0].setDirection(Movable.DIRECTION_RIGHT);
				e[1].setDirection(Movable.DIRECTION_DOWN);
				e[2].setDirection(Movable.DIRECTION_RIGHT);
				e[3].setDirection(Movable.DIRECTION_UP);
				
			} catch (CharacterException | IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			break;
			
		case 2 :
			
			try {
				e = generateEnemy();
				p[currentLevel].addEnemy(e[0], 65);
				p[currentLevel].addEnemy(e[1], 51);
				p[currentLevel].addEnemy(e[2], 115);
				p[currentLevel].addEnemy(e[3], 203);
				
				e[0].setDirection(Movable.DIRECTION_RIGHT);
				e[1].setDirection(Movable.DIRECTION_DOWN);
				e[2].setDirection(Movable.DIRECTION_UP);
				e[3].setDirection(Movable.DIRECTION_DOWN);
				
			} catch (CharacterException | IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			break;
			
		default :
			break;
		}	
		
		this.currentTransitionDuration = 1;
	}
	
	private Enemy[] generateEnemy() throws CharacterException, IOException {
		Enemy[] baru = new Enemy[4];
		for (int i = 0; i < baru.length; i++) {
			baru[i] = new Enemy("monster", "monsterBerdiri.png", 120, 120, -120, -120);
		}
		
		return baru;
	}

	@Override
	public void render(Graphics g) {
		keyWatcher();
		
		if (currentTransitionDuration > 0 && currentTransitionDuration < transitionDuration) {
			g.setFont(GamePanel.getCoolFont());
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, Main.getWidth(), Main.getHeight());
			g.setColor(Color.WHITE);
			
			g.drawString("Level " + (this.currentLevel + 1), Main.getWidth() / 2 - 100, Main.getHeight() / 2 + 150);
			c.render(g);
			currentTransitionDuration++;
		} else {
			p[currentLevel].render(g);
			c.render(g);			
		}

	}

	public void keyWatcher() {
		if (KeyMapper.isPressed(KeyMapper.KEY_ESCAPE)) {
			KeyMapper.confirmEscape();
			quit();
		}
	}

	public void killedMonsterAt(Integer tile) {
		this.p[currentLevel].getEnemies().remove(tile);
		if (this.p[currentLevel].getEnemies().size() == 0) {
			
			if(currentLevel<L.length && currentLevel!= 2){
				resetPlay();
				this.currentLevel += 1;
				this.currentTransitionDuration = 1;
				reset(currentLevel);
				try {
					c.setHealth(c.getFullHealth());
					c.setMp(c.getFullMp());
				} catch (CharacterException e) {
					e.printStackTrace();
				}
				
			}else{
				((GameOState) GameStateManager.getState(GameStateManager.GAMEOSTATE)).setWin(true);
				GameStateManager.setState(GameStateManager.ENDSTATE);				
			}
			}
		else {
			GameStateManager.setState(GameStateManager.PLAYSTATE);	
		}
	}
	
	public void resetPlay(){
		p[currentLevel].resetPlace();
		this.currentTransitionDuration = 1;
	}
	
	
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			System.out.println("[PlayState] Pindah ke aku");
		}
		super.setVisible(visible);
	}

	@Override
	public void quit() {
		((PauseState) GameStateManager.getState(GameStateManager.PAUSESTATE)).setResumeTo(GameStateManager.PLAYSTATE);
		GameStateManager.setState(GameStateManager.PAUSESTATE);
	}

}
