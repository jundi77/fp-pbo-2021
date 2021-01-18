package com.pbo.wws.entity;

import java.util.HashMap;

public abstract class MagicCharacter extends FightingCharacter {
	private int mp, fullMp;

	/**
	 * menyimpan nama spell, cost mp, dan damagenya.
	 * [0] = cost mp
	 * [1] = damage
	 */
	private HashMap<String, Integer[]> spells;

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

	public void doSpell(String spell, CanAttack opponent) {
		Integer[] spellDetails = this.spells.get(spell);
		if (spellDetails != null) {
			/**
			 * [0] = cost mp
			 * [1] = damage
			 */
			
			if (this.mp >= spellDetails[0]) {
				this.mp -= spellDetails[0];
				opponent.getDamage(spellDetails[1]);
			}
		}
	}

	// Custom exception untuk magic character
	@SuppressWarnings("serial")
	public class MagicCharacterException extends FightingCharacterException {
		public MagicCharacterException(String message) {
			super(message);
		}
	}
}
