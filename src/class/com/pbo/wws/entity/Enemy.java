package com.pbo.wws.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Ticker;

public class Enemy extends FightingCharacter implements CanAttack, Animatable {

	private HashMap<String, BufferedImage> animationSprite;
	private HashMap<BufferedImage, Integer> animationFrameCount;
	private BufferedImage currentAnimationSprite;
	private int currentAnimationFrame;
	private String currentAnimationState;

	@SuppressWarnings("serial")
	public Enemy(String name,
				 String spriteImgFileName,
				 int height, int width,
				 int x, int y
				 ) throws CharacterException, IOException {
		super(name, spriteImgFileName);

		this.setHeight(height);
		this.setWidth(width);
		
		this.setSrcHeight(32);
		this.setSrcWidth(32);

		this.setX(x);
		this.setY(y);
		
		this.setTotalMovement(1);
		
		this.animationSprite = new HashMap<String, BufferedImage>() {{
			put("ready", ImageIO.read(getClass().getResourceAsStream(
					Main.resourcePath + characterRootDir + "/" + getName() + "/monsterReady.png"
			)));
			put("serang", ImageIO.read(getClass().getResourceAsStream(
					Main.resourcePath + characterRootDir + "/" + getName() + "/monsterSerang.png"
			)));
			put("damaged", ImageIO.read(getClass().getResourceAsStream(
					Main.resourcePath + characterRootDir + "/" + getName() + "/monsterDamaged.png"
			)));
			put("mati", ImageIO.read(getClass().getResourceAsStream(
					Main.resourcePath + characterRootDir + "/" + getName() + "/monsterMati.png"
			)));
		}};
		this.animationFrameCount = new HashMap<BufferedImage, Integer>() {{
			put(animationSprite.get("ready"), 3 * Ticker.getRatePerSecond() / 5);
			put(animationSprite.get("serang"), 8 * Ticker.getRatePerSecond() / 5);
			put(animationSprite.get("damaged"), 3 * Ticker.getRatePerSecond() / 5);
			put(animationSprite.get("mati"), 8 * Ticker.getRatePerSecond() / 5);
		}};
		
		this.currentAnimationSprite = null;
		
		this.setAttacks(new HashMap<String, Integer>() {{
			put("default", 25);
		}});
		
		this.setFullHealth(100);
		this.setHealth(100);
	}

	@Override
	public void render(Graphics g) {
		if (this.isAnimating()) {
			g.drawImage(this.currentAnimationSprite,

					// di layar
					this.getX(), this.getY(),
					this.getX() + this.getWidth(), this.getY() + this.getHeight(),
					
					// di sumber gambar
					this.getSrcWidth() * (this.currentAnimationFrame / (Ticker.getRatePerSecond() / 5)), 0,
					this.getSrcWidth() * ((this.currentAnimationFrame) / (Ticker.getRatePerSecond() / 5) + 1), this.getSrcHeight(),
					null, null);
			whenAnimating(currentAnimationState);
			if (++this.currentAnimationFrame >= this.animationFrameCount.get(this.currentAnimationSprite)) {
				this.currentAnimationState = null;
				this.currentAnimationSprite = null;
				this.currentAnimationFrame = 0;
				afterAnimating(currentAnimationState);
			}
		} else {
			g.drawImage(this.getSprite(),
					// di layar
					this.getX(), this.getY(),
					this.getX() + this.getWidth(), this.getY() + this.getHeight(),
					
					// di sumber gambar
					this.getSrcWidth() * this.getMovement(),
					this.getSrcHeight() * this.getDirection(),
					this.getSrcWidth()* (this.getMovement() + 1),
					this.getSrcHeight() * (this.getDirection() + 1)
					, null, null);			
		}
	}

//	@Override
//	public void render(Graphics g) {
//		g.drawImage(sprite, x, y, x + width, y + height
//					, this.srcWidth * this.movement, this.srcHeight * this.direction				// di layar
//					, this.srcWidth * (this.movement + 1), this.srcHeight * (this.direction + 1)	// di sumber gambar sprite
//					, null, null);
//	}

	@Override
	public void playAnimation(String state) {
		this.beforeAnimating(state);
		this.currentAnimationState = state;
		this.currentAnimationSprite = this.animationSprite.get(state);
		this.currentAnimationFrame = 0;
	}

	@Override
	public boolean isAnimating() {
		return this.currentAnimationState != null;
	}

	@Override
	public String getCurrentAnimationState() {
		return this.currentAnimationState;
	}
	
	// Custom exception untuk magic character
	@SuppressWarnings("serial")
	public class EnemyException extends Exception {
		public EnemyException(String message) {
			super(message);
		}
	}

}
