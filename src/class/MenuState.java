import java.awt.Graphics2D;
import java.awt.event.KeyEvent;


public class MenuState extends GameState
{
	private Background bg;

	
	public MenuState (GameStateManager gsm)
	{
		this.gsm = gsm;
		try{
			bg = new Background("/ui/UserInterface/Menu.png",8);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		//draw bg
		bg.draw(g);
		
	}
	
	@Override
	public void keyPressed(int k) {
		
	}

	@Override
	public void keyRealeased(int k) {
		// TODO Auto-generated method stub
		
	}

	
}
