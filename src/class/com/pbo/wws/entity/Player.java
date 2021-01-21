package com.pbo.wws.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Ticker;

public class Player extends MagicCharacter {

	@SuppressWarnings("serial")
	public Player(String name,
				  String spriteImgFileName,
				  int height, int width,
				  int srcHeight, int srcWidth,
				  int x, int y
				  ) throws CharacterException {
		super(name, spriteImgFileName);
		
		this.setHeight(height);
		this.setWidth(width);
		
		this.setSrcHeight(srcHeight);
		this.setSrcWidth(srcWidth);

		this.setX(x);
		this.setY(y);
		
		this.setTotalMovement(5 * Ticker.getRatePerSecond() / 12);

		this.setAttacks(new HashMap<String, Integer>() {{
			put("default", 15);
		}});
		
		/**
		 * [0] = cost mp
		 * [1] = damage
		 */
		this.setSpells(new HashMap<String, Integer[]>(){{
			put("armor",   new Integer[] {25, 10});
			put("burning", new Integer[] {25, 30});
			put("college", new Integer[] {25, 25});
			put("defense", new Integer[] {0, 0});
			put("element", new Integer[] {75, 75});
		}});
		
		this.setFullHealth(150);
		this.setHealth(150);
		this.setFullMp(100);
		this.setMp(100);
	}

	public boolean keyMovementWatcher() {
		if (KeyMapper.isPressed(KeyMapper.KEY_W)) {
			this.setDirection(DIRECTION_UP);
			this.incrementMovement();
		} else if (KeyMapper.isPressed(KeyMapper.KEY_S)) {
			this.setDirection(DIRECTION_DOWN);
			this.incrementMovement();
		}  else if (KeyMapper.isPressed(KeyMapper.KEY_A)) {
			this.setDirection(DIRECTION_LEFT);
			this.incrementMovement();
		}  else if (KeyMapper.isPressed(KeyMapper.KEY_D)) {
			this.setDirection(DIRECTION_RIGHT);
			this.incrementMovement();
		} else {
			return false;
		}

		return true;
	}

	@Override
	public void render(Graphics g) {
		if(!keyMovementWatcher()) {
			this.setMovement((this.getTotalMovement() / 5) * 2);
		};
		
		//System.out.println(this.getMovement());
		// gambar sprite
		g.drawImage(this.getSprite(),
					this.getX(), this.getY(),
					this.getX() + this.getWidth(), this.getY() + this.getHeight(), 
					this.getSrcWidth() * (this.getMovement() / (Ticker.getRatePerSecond() / 12)), this.getSrcHeight() * this.getDirection(), 
					this.getSrcWidth() * (this.getMovement() / (Ticker.getRatePerSecond() / 12) + 1), this.getSrcHeight() * (this.getDirection() + 1),
					null, null);
	}
}
