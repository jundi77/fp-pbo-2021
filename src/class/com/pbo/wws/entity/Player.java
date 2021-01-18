package com.pbo.wws.entity;

import java.awt.Color;
import java.awt.Graphics;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;

public class Player extends MagicCharacter{

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
		
		this.setTotalMovement(5);

		
	}

	public void keyMovementWatcher() {
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
		}

		System.out.println("[Player]: x=" + this.getX() + " y=" + this.getY());
	}
	@Override
	public void render(Graphics g) {
		keyMovementWatcher();
		
		// gambar bound
		g.setColor(Color.RED);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		// gambar sprite
		g.drawImage(this.getSprite(),
					this.getX(), this.getY(),
					this.getX() + this.getWidth(), this.getY() + this.getHeight(), 
					this.getSrcWidth() * this.getMovement(), this.getSrcHeight() * this.getDirection(), 
					this.getSrcWidth() * (this.getMovement() + 1), this.getSrcHeight() * (this.getDirection() + 1),
					null, null);
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getDamage() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doSpell(String spell, FightingCharacter opponent) {
		// TODO Auto-generated method stub
		
	}
}
