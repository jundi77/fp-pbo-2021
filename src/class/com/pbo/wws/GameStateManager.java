package com.pbo.wws;

import java.util.ArrayList;


public class GameStateManager
{
	
	private static ArrayList <GameState> gameStates;
	private static int currentState;
	
	//enum
	
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int BATTLESTATE = 3;
	public static final int GAMEOSTATE = 4;
	public static final int ENDSTATE = 5;
	
	public GameStateManager()
	{
		gameStates = new ArrayList <GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new BattleState(this));
		gameStates.add(new GameOState(this));
		gameStates.add(new EndState(this));
	}

	//terbuka untuk public(class lain)
	
	public static void setState(int state)
	{
		currentState = state;
		System.out.println("[GameStateManager] Pindah State");
		gameStates.get(currentState).init();
	}
	
	public void keyPressed(int k) 
	{
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) 
	{
		gameStates.get(currentState).keyReleased(k);
	}
	

}
