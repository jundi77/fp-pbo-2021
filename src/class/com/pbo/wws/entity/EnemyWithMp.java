package com.pbo.wws.entity;

public abstract class EnemyWithMp extends MagicCharacter {

	public EnemyWithMp(String name, 
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
	}

	// Custom exception untuk magic character
	@SuppressWarnings("serial")
	public class EnemyWithMpException extends Exception {
		public EnemyWithMpException(String message) {
			super(message);
		}
	}

	@Override
	public void doSpell(String spell, CanAttack opponent) {
		// TODO Auto-generated method stub
		
	}
}
