package com.pbo.wws.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;

import javax.imageio.ImageIO;

public abstract class Character implements Renderable, Movable {
	private int totalMovement = 1;
	protected static final String characterRootDir = "/entity";
	private boolean visible, moving;
	private String name;
	private BufferedImage sprite;
	private int x, y, 
				xSpeed, ySpeed, 
				width, height, 		 // width dan height di layar
				srcWidth, srcHeight, // width dan height untuk satu karakter di gambar sprite
				direction,
				movement;

	public Character(String name, String spriteImgFileName) throws CharacterException {
		this.x = this.y
			   = this.xSpeed
			   = this.ySpeed
			   = this.movement
			   = this.width
			   = this.height = 0;

		this.name = name;
		this.direction = DIRECTION_DOWN;

		// Baca file sumber untuk gambar sprite
		try {
			sprite = ImageIO.read(getClass()
					.getResourceAsStream(Main.resourcePath + characterRootDir + "/" + this.name + "/" + spriteImgFileName));
			System.out.println("[Character]: Sprite for " + this.name + " is loaded");
		} catch (Exception e) {
			throw new CharacterException("[Character]: Failed to load sprite for " + this.name);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, x + width, y + height
					, this.srcWidth * this.movement, this.srcHeight * this.direction				// di layar
					, this.srcWidth * (this.movement + 1), this.srcHeight * (this.direction + 1)	// di sumber gambar sprite
					, null, null);
	}

	@Override
	public void setMovingStatus(boolean move) {
		moving = move;
	}

	@Override
	public boolean getMovingStatus() {
		return moving;
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			Renderer.addDrawable(this);
		} else {
			Renderer.removeDrawable(this);
		}
		
		this.visible = visible;
	}

	@Override
	public boolean getVisibility() {
		return visible;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = (int) (x - this.width / 2);
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = (int) (y - this.height / 2);
	}

	@Override
	public int getXSpeed() {
		return xSpeed;
	}

	@Override
	public void setXSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	@Override
	public int getYSpeed() {
		return ySpeed;
	}

	@Override
	public void setYSpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getMovement() {
		return movement;
	}

	public void setMovement(int movement) {
		this.movement = movement % totalMovement;
	}

	public void incrementMovement() {
		this.movement = ++this.movement % totalMovement;
	}

	public int getTotalMovement() {
		return this.totalMovement;
	}

	public void setTotalMovement(int totalMovement) {
		this.totalMovement = totalMovement;
	}

	public int getSrcWidth() {
		return srcWidth;
	}

	public void setSrcWidth(int srcWidth) {
		this.srcWidth = srcWidth;
	}

	public int getSrcHeight() {
		return srcHeight;
	}

	public void setSrcHeight(int srcHeight) {
		this.srcHeight = srcHeight;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getDirection() {
		return this.direction;
	}

	@Override
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public String getName() {
		return this.name;
	}

	// Custom exception untuk karakter
	@SuppressWarnings("serial") 
	public 	class CharacterException extends Exception {
		public CharacterException(String message) {
			super(message);
		}
	}
}
