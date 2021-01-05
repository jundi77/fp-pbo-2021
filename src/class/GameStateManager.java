import java.util.ArrayList;


public class GameStateManager{
	
	private ArrayList <GameState> gameStates;
	private int currentState;
	
	//enum
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int GAMEOSTATE = 3;
	
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		
		currentState= MENUSTATE;
		gameStates.add(new MenuState(this));
		
	}
	
	
	//terbuka untuk public(class lain)
	
	public void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update()
	{
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) 
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) 
	{
		gameStates.get(currentState).keyRealeased(k);
	}
	
}
