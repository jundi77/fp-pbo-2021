import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Menu extends JPanel implements Runnable
{
	Renderer renderer = new Renderer();
	
	//dimensions
	public static final int menuWidth = 200;
	public static final int menuHeight = 150;
	
	//game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/ FPS;
	
	//gameStateManager
	private GameStateManager gsm;
	
	public Menu()
	{
		setSize(new Dimension(1280, 720));
		setFocusable(true);
		requestFocus();
		setVisible(true);
		this.add(renderer);
	}

	public void addNotify()
	{
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	//Sudah ada dikelas renderer
	
	private void init() 
	{
		running = true;
		gsm = new GameStateManager(renderer);
	}
	
	public void run()
	{
		init();
		
//		long start;
//		long elapsed;
//		long wait;
//		
//		//game loop
//		while(running)
//		{
//			start = System.nanoTime();			
//			elapsed = System.nanoTime() - start;
//			wait = targetTime - elapsed/1000000;
//			if(wait<0)wait = 5;
//			
//			try{
//				Thread.sleep(wait);
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
	}
}
