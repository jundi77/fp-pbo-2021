package com.pbo.wws.entity;

import java.util.HashMap;

public abstract class MagicCharacter extends FightingCharacter {
	private int mp, fullMp;
	private HashMap<String, Integer[]> spells; // menyimpan nama spell, cost mp, dan damagenya

	public MagicCharacter(String name, String spriteImgFileName) throws CharacterException {
		super(name, spriteImgFileName);
		this.mp = this.fullMp = 0;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getFullMp() {
		return fullMp;
	}

	public void setFullMp(int fullMp) {
		this.fullMp = fullMp;
	}

	public HashMap<String, Integer[]> getSpells() {
		return spells;
	}

	public void setSpells(HashMap<String, Integer[]> spells) {
		this.spells = spells;
	}

	public abstract void doSpell(String spell, FightingCharacter opponent);

	// Custom exception untuk magic character
	@SuppressWarnings("serial")
	public class MagicCharacterException extends Exception {
		public MagicCharacterException(String message) {
			super(message);
		}
	}
}
