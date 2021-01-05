import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Menu extends JPanel implements Runnable
{
	//dimensions
	public static final int menuWidth = 200;
	public static final int menuHeight = 150;
	
	//game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000000/ FPS;
	
	//image
	private BufferedImage menuImage;
	private Graphics2D g;
	
	//gameStateManager
	private GameStateManager gsm;
	
	public Menu()
	{
		super();
		setPreferredSize(new Dimension(menuWidth*8,  menuHeight*6));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify()
	{
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	//SUdah ada dikelas renderer
	
	private void init() 
	{
		menuImage = new BufferedImage(menuWidth,menuHeight,BufferedImage.TYPE_INT_RGB);
		g =(Graphics2D) menuImage.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
		
	}
	
	public void run()
	{
		init();
		
		long start;
		long elapsed;
		long wait;
		
		//game loop
		while(running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed/1000000;
			
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//Sudah ada class renderer
	
	public void update(){
		gsm.update();
	}
	public void draw(){
		gsm.draw(g);
	}
	public void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(menuImage,0,0,1600 ,900,null);
		g2.dispose();
	}
	
}
