package com.pbo.wws.state;

import java.awt.Color;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.pbo.wws.Place;
import com.pbo.wws.entity.Character;
import com.pbo.wws.entity.Character.CharacterException;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.Player;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.manager.GameStateManager;

public class PlayState extends GameState implements  Renderable
{
	Place p;
	Player c;
	Enemy e;

	@SuppressWarnings("serial")
	public PlayState (GameStateManager gsm)
	{
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
			Renderer.addDrawable(p);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			e = new Enemy("monster", "monsterBerdiri.png", 120, 120, -120, -120);
//			e.setX(60);
//			e.setY(60);
			p.addEnemy(e, 79);
		} catch (CharacterException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}

//		e.setDirection(Character.DIRECTION_UP); // faulty
		Renderer.addDrawable(c);
		this.gsm = gsm;
	}
	
	@Override
	public void init() {

		System.out.println("[PlayState] State changed to me!");
		setVisible(true);
	}

	public int y = 100, x = 100;
	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void setVisible(boolean visible) {
		if(!visible) {
			Renderer.removeDrawable(this);			
		} else {
			Renderer.addDrawable(this);			
		}
	}

	@Override
	public boolean getVisibility() {
		return Renderer.isListed((Renderable) this);
	}

}
