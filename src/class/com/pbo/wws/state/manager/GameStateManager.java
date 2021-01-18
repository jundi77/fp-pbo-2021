package com.pbo.wws.state.manager;

import java.io.IOException;
import java.util.ArrayList;

import com.pbo.wws.EndState;
import com.pbo.wws.entity.Enemy;
import com.pbo.wws.entity.Player;
import com.pbo.wws.entity.Character.CharacterException;
import com.pbo.wws.frame.Main;
import com.pbo.wws.io.Renderer;
import com.pbo.wws.state.BattleState;
import com.pbo.wws.state.GameOState;
import com.pbo.wws.state.GameState;
import com.pbo.wws.state.MenuState;
import com.pbo.wws.state.PauseState;
import com.pbo.wws.state.PlayState;


public class GameStateManager
{
	private static ArrayList <GameState> gameStates;
	private static int currentState;

	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int BATTLESTATE = 3;
	public static final int GAMEOSTATE = 4;
	public static final int ENDSTATE = 5;
	
	public GameStateManager()
	{
		currentState = MENUSTATE;
		gameStates = new ArrayList <GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new PauseState(this));
		gameStates.add(new BattleState(this));
		gameStates.add(new GameOState(this));
		gameStates.add(new EndState(this));

		try {
			Enemy e = new Enemy("monster", "monsterBerdiri.png", 120, 120, -120, -120);
//			e.setX(60);
//			e.setY(60);
			((BattleState)this.gameStates.get(BATTLESTATE)).setEnemy(e);
		} catch (CharacterException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
		
		try {
			Player c = new Player("main", "spriteUtama(32x32).png", 120, 120, 32, 32, Main.getWidth() / 2, Main.getHeight() / 2);
			((BattleState)this.gameStates.get(BATTLESTATE)).setPlayer(c);
		} catch (CharacterException e1) {
			e1.printStackTrace();
		}

		setState(MENUSTATE);
		Renderer.setRunning(true);
	}

	public static void setState(int state)
	{
		System.out.println("[GameStateManager] Pindah State");
		GameStateManager.gameStates.get(currentState).setVisible(false);
		currentState = state;
		gameStates.get(currentState).setVisible(true);
	}

	/**
	 * Supaya bisa config kelas lain sebelum diset
	 * @param state
	 * @return
	 */
	public static GameState getState(int state) {
		return GameStateManager.gameStates.get(state);
	}
}
