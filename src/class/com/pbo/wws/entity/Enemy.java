package com.pbo.wws.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.pbo.wws.frame.Main;

public class Enemy extends FightingCharacter implements CanAttack, Animatable {

	private HashMap<String, BufferedImage> animationSprite;
	private String currentAnimationSprite;

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
		
		this.currentAnimationSprite = "ready";
	}

	// Custom exception untuk magic character
	@SuppressWarnings("serial")
	public class EnemyException extends Exception {
		public EnemyException(String message) {
			super(message);
		}
	}

	@Override
	public void setCurrentAnimationSprite(String state) {
		this.currentAnimationSprite = state;
	}

	@Override
	public String getCurrentAnimationSprite() {
		return this.currentAnimationSprite;
	}

	@Override
	public void animateSprite() {
		// TODO Auto-generated method stub
		
	}
}
