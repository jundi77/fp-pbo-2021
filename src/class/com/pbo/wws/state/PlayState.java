package com.pbo.wws.state;

import java.awt.Color;

import java.awt.Graphics;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.pbo.wws.Place;
import com.pbo.wws.Renderable;
import com.pbo.wws.io.KeyMapper;
import com.pbo.wws.io.Renderer;

public class PlayState extends GameState implements  Renderable
{
	Place p;

	public PlayState (GameStateManager gsm)
	{
		try {
			p = new Place("main");
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
		g.setColor(Color.BLACK);
		if (KeyMapper.isPressed(KeyMapper.KEY_W)) {
			y -= 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_S)) {
			y += 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_A)) {
			x -= 3;
		} else if (KeyMapper.isPressed(KeyMapper.KEY_D)) {
			x += 3;
		}
		g.drawRect(x, y, 300, 300);
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
