import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Stack;


public class GameStateManager
{
	
	private static ArrayList <GameState> gameStates;
	private static int currentState;
	
	//enum
	
	public static final int MENUSTATE = 0;
	public static final int GAMESTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int BATTLESTATE = 3;
	public static final int GAMEOSTATE = 4;
	public static final int ENDSTATE = 5;
	
	public GameStateManager()
	{
		gameStates = new ArrayList <GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
	}
	
	public GameStateManager(Renderer renderer)
	{
		this();
		renderer.addDrawable(gameStates.get(0));
	}
	
	//terbuka untuk public(class lain)
	
	public static void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
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
