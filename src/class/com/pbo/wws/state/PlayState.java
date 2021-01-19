package com.pbo.wws.state;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.pbo.wws.Exitable;
import com.pbo.wws.Place;
import com.pbo.wws.entity.Character.CharacterException;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.Player;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public class PlayState extends GameState implements Exitable
{
	private Place p;
	private Player c;
	private Enemy e;

	@SuppressWarnings("serial")
	public PlayState (GameStateManager gsm)
	{
		this.gsm = gsm;
		this.reset();
//		TODO e.setDirection(Character.DIRECTION_UP); // Perbaiki direction monster
		Renderer.addDrawable(this);
	}

	public void reset() {
		try {
			c = new Player("main", "spriteUtama(32x32).png", 120, 120, 32, 32, Main.getWidth() / 2, Main.getHeight() / 2);
		} catch (CharacterException e1) {
			e1.printStackTrace();
		}

		try {
			p = new Place("main", new HashMap<Integer, Integer[]>(){{
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

		try {
			e = new Enemy("monster", "monsterBerdiri.png", 120, 120, -120, -120);
			
			// TODO tambah enemy yang lain
			p.addEnemy(e, 99); // TODO tambah direction lihat ke mana
		} catch (CharacterException | IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void render(Graphics g) {
		keyWatcher();
		p.render(g);
		c.render(g);
	}

	public void keyWatcher() {
		if (KeyMapper.isPressed(KeyMapper.KEY_ESCAPE)) {
			KeyMapper.confirmEscape();
			quit();
		}
	}

	public void killedMonsterAt(Integer tile) {
		this.p.getEnemies().remove(tile);
		if (this.p.getEnemies().size() == 0) {
			((GameOState) GameStateManager.getState(GameStateManager.GAMEOSTATE)).setWin(true);
			GameStateManager.setState(GameStateManager.GAMEOSTATE);
		} else {
			GameStateManager.setState(GameStateManager.PLAYSTATE);
		}
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
		((PauseState) GameStateManager.getState(GameStateManager.PAUSESTATE)).setResumeTo(GameStateManager.BATTLESTATE);
		GameStateManager.setState(GameStateManager.PAUSESTATE);
	}

}
