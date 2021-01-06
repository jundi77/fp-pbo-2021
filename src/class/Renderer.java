import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Renderer extends JPanel implements ActionListener{
	private static int FRAME_RATE = 30;
	private Timer timer;
	private ArrayList<Renderable> drawables;

	public Renderer() {
		this.timer = new Timer(1000 / FRAME_RATE, this);
		this.drawables = new ArrayList<Renderable>();

		timer.start();
		
		this.setFocusable(true);
		this.setVisible(true);
	}

	public Renderer(int frameRate) {
		Renderer.FRAME_RATE = frameRate;
		this.timer = new Timer(1000 / FRAME_RATE, this);
		this.drawables = new ArrayList<Renderable>();

		timer.start();
		
		this.setFocusable(true);
		this.setVisible(true);
	}

	public Renderer(int frameRate, ArrayList<Renderable> drawables) {
		Renderer.FRAME_RATE = frameRate;
		this.timer = new Timer(1000 / FRAME_RATE, this);
		this.drawables = drawables;

		timer.start();
		
		this.setFocusable(true);
		this.setVisible(true);
	}

	public static void setFrameRate(int frameRate) {
		Renderer.FRAME_RATE = frameRate;
	}

	public static int getFrameRate() {
		return Renderer.FRAME_RATE;
	}

	public void clear() {
		this.drawables.clear();
	}

	public void addDrawable(Renderable d) {
		this.drawables.add(d);
	}

	public void addDrawable(int index, Renderable d) {
		this.drawables.add(index, d);
	}

	public void removeDrawable(Renderable d) {
		this.drawables.remove(d);
	}

	public void removeDrawable(int index) {
		this.drawables.remove(index);
	}

	public Renderable getDrawable(int index) {
		return this.drawables.get(index);
	}

	public boolean isListed(Renderable d) {
		return this.drawables.contains(d);
	}

	public boolean isOnIndex(Renderable d, int index) {
		return this.drawables.get(index) == d;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 300, 300);
		for (Renderable d : drawables) {
			d.render(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
//		System.out.println(KeyMapper.getKeyStatus(KeyMapper.KEY_DOWN));
	}
}