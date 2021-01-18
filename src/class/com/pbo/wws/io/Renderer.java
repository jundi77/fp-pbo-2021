package com.pbo.wws.io;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.pbo.wws.frame.Main;
import com.pbo.wws.Renderable;

@SuppressWarnings("serial")
public class Renderer extends JPanel implements ActionListener{

	private static ArrayList<Renderable> drawables;

	public Renderer() {
		setBackground(Color.DARK_GRAY);
		setSize(Main.getWidth(), Main.getHeight());
		if (Renderer.drawables == null) {
			Renderer.drawables = new ArrayList<Renderable>();
			this.setFocusable(true);
			this.setVisible(true);
		} else {
			System.out.println("[Renderer] Renderer telah diinstansiasi!");
		}
	}

	public Renderer(ArrayList<Renderable> drawables) {
		if (Renderer.drawables != null) {
			Renderer.drawables.clear();
			System.out.println("[Renderer] Renderer telah diinstansiasi");
		}

		Renderer.drawables = drawables;
		this.setFocusable(true);
		this.setVisible(true);
	}

	public void clear() {
		Renderer.drawables.clear();
	}

	public static void addDrawable(Renderable d) {
		if (Renderer.drawables != null) {
			Renderer.drawables.add(d);			
			return;
		}
		
		System.out.println("[Renderer] Renderer belum diinstansiasi!");
	}

	public static void addDrawable(int index, Renderable d) {
		if (Renderer.drawables != null) {
			Renderer.drawables.add(index, d);
			return;
		}
		
		System.out.println("[Renderer] Renderer belum diinstansiasi!");
	}

	public static void removeDrawable(Renderable d) {
		if (Renderer.drawables != null) {
			Renderer.drawables.remove(d);
		} else {			
			System.out.println("[Renderer] Renderer belum diinstansiasi!");
		}

	}

	public static void removeDrawable(int index) {
		if (Renderer.drawables != null) {
			Renderer.drawables.remove(index);
		} else {
			System.out.println("[Renderer] Renderer belum diinstansiasi!");			
		}
	}

	public static Renderable getDrawable(int index) {
		if (Renderer.drawables != null) {
			return Renderer.drawables.get(index);			
		}
		
		System.out.println("[Renderer] Renderer belum diinstansiasi!");
		return null;
	}

	public static boolean isListed(Renderable d) {
		if (Renderer.drawables != null) {
			return Renderer.drawables.contains(d);
		}

		System.out.println("[Renderer] Renderer belum diinstansiasi!");
		return false;
	}

	public static boolean isOnIndex(Renderable d, int index) {
		if (Renderer.drawables != null) {
			return Renderer.drawables.get(index) == d;
		}
		
		System.out.println("[Renderer] Renderer belum diinstansiasi!");
		return false;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for (Renderable d : drawables) {
			d.render(g);
		}
		g.setColor(Color.RED);
		g.drawLine(Main.getWidth() / 2, 0, Main.getWidth() / 2, Main.getHeight());
		g.drawLine(0, Main.getHeight() / 2, Main.getWidth(), Main.getHeight() / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
//		System.out.println(KeyMapper.getKeyStatus(KeyMapper.KEY_DOWN));
	}
}
