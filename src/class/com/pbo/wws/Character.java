package com.pbo.wws;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Character implements Renderable, Movable{
	private static final String characterRootDir = "src/assets/entity";
	private static final int TOTAL_MOVEMENT = 5;
	private boolean visible;
	private String name;
	private BufferedImage sprite;
	private int x, y, xSpeed, ySpeed, width, height, zoom, direction, movement;
	private Main window;
	
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
//		this.width = (int) (width * Main.getZoom());
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
//		this.height = (int) (height * Main.getZoom());
	}

	public Character(Main window) {
		this.movement = 0;
		this.direction = DIRECTION_DOWN;
		this.xSpeed = this.ySpeed = 32;
		this.window = window;
		
		try {
			sprite = ImageIO.read(new File(characterRootDir + "/main/spriteUtama(32x32).png"));
			System.out.println("[Character]: Sprite is loaded");
			this.setHeight(120);
			this.setWidth(120);
//			this.setX(window.getWindowWidth() / 2);
//			this.setY(window.getWindowHeight() / 2);
			System.out.println(this.x);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[Character]: Failed to load sprite");
		}
	}

	public void KeyMovementWatcher() {
		// TODO diperbaiki
//		if (KeyMapper.getKeyStatus(KeyMapper.KEY_W)) {	
//			this.direction = DIRECTION_UP;
//			this.y -= this.ySpeed;
//			this.movement = ++this.movement % TOTAL_MOVEMENT;
//		} else if (KeyMapper.getKeyStatus(KeyMapper.KEY_S)) {
//			this.direction = DIRECTION_DOWN;
//			this.y += this.ySpeed;
//			this.movement = ++this.movement % TOTAL_MOVEMENT;
//		}  else if (KeyMapper.getKeyStatus(KeyMapper.KEY_A)) {
//			this.direction = DIRECTION_LEFT;
//			this.x -= this.xSpeed;
//			this.movement = ++this.movement % TOTAL_MOVEMENT;
//		}  else if (KeyMapper.getKeyStatus(KeyMapper.KEY_D)) {
//			this.direction = DIRECTION_RIGHT;
//			this.x += this.ySpeed;
//			this.movement = ++this.movement % TOTAL_MOVEMENT;
//		}
//
//		System.out.println("[Character]: x=" + this.x + " y=" + this.y);
	}

	@Override
	public void render(Graphics g) {
		this.x += xSpeed;
		// draw background
		// draw buttonnya
		g.drawImage(sprite, x, y, x + width, y + height, 32 * this.movement, 32 * this.direction, 32 * this.movement + 32, 32 * this.direction + 32, null, null);
	}


	@Override
	public void setMovingStatus(boolean move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getMovingStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getVisibility() {
		// TODO Auto-generated method stub
		return false;
	}
}
