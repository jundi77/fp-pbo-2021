package com.pbo.wws;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pbo.wws.entity.Movable;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderable;
import com.pbo.wws.io.Renderer;

import javax.imageio.ImageIO;

public class Character implements Renderable, Movable{
	private static final String characterRootDir = "src/assets/entity";
	private static final int TOTAL_MOVEMENT = 5;
	private boolean visible, moving;
	private String name;
	private BufferedImage sprite;
	private int x, y, xSpeed, ySpeed, width, height, zoom, direction, movement;
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = (int) (x - this.width / 2);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = (int) (y - this.height / 2);
	}

	public int getxSpeed() {
		return xSpeed;
	}

	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}

	public int getySpeed() {
		return ySpeed;
	}

	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = (int) (width * GamePanel.getZoom());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = (int) (height * GamePanel.getZoom());
	}

	public Character() {
		this.movement = 0;
		this.direction = DIRECTION_DOWN;
		this.xSpeed = this.ySpeed = 32;
		
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream(Main.resourcePath + "/entity/main/spriteUtama(32x32).png"));
			System.out.println("[Character]: Sprite is loaded");
			this.setHeight(120);
			this.setWidth(120);
			this.setX(Main.getWidth() / 2);
			this.setY(Main.getHeight() / 2);
			System.out.println(this.x);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[Character]: Failed to load sprite");
		}
	}

	public void keyMovementWatcher() {
		// TODO diperbaiki
		if (KeyMapper.isPressed(KeyMapper.KEY_W)) {	
			this.direction = DIRECTION_UP;
			this.y -= this.ySpeed;
			this.movement = ++this.movement % TOTAL_MOVEMENT;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_S)) {
			this.direction = DIRECTION_DOWN;
			this.y += this.ySpeed;
			this.movement = ++this.movement % TOTAL_MOVEMENT;
		}  else if (KeyMapper.isPressed(KeyMapper.KEY_A)) {
			this.direction = DIRECTION_LEFT;
			this.x -= this.xSpeed;
			this.movement = ++this.movement % TOTAL_MOVEMENT;
		}  else if (KeyMapper.isPressed(KeyMapper.KEY_D)) {
			this.direction = DIRECTION_RIGHT;
			this.x += this.ySpeed;
			this.movement = ++this.movement % TOTAL_MOVEMENT;
		}

		System.out.println("[Character]: x=" + this.x + " y=" + this.y);
	}

	@Override
	public void render(Graphics g) {
//		this.x += xSpeed;
		// draw background
		// draw buttonnya
		keyMovementWatcher();
		g.drawImage(sprite, x, y, x + width, y + height, 32 * this.movement, 32 * this.direction, 32 * this.movement + 32, 32 * this.direction + 32, null, null);
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
	}

	@Override
	public boolean getVisibility() {
		return visible;
	}
}
