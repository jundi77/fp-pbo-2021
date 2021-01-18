package com.pbo.wws.entity;

import java.util.HashMap;

public abstract class FightingCharacter extends Character implements CanAttack {
	private HashMap<String, Integer> attacks; // menyimpan attack yang bisa dilakukan dan damagenya
	private int health, fullHealth,
				// VV tidak dipakai karena belum ada sistem level
				damageBonus, exp,
				levelStatus;

	public FightingCharacter(String name, String spriteImgFileName) throws CharacterException {
		super(name, spriteImgFileName);
		this.health = this.fullHealth
					= this.damageBonus
					= this.exp
					= this.levelStatus
					= 0;
	}

	public HashMap<String, Integer> getAttacks() {
		return attacks;
	}

	public void setAttacks(HashMap<String, Integer> attacks) {
		this.attacks = attacks;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getFullHealth() {
		return fullHealth;
	}

	public void setFullHealth(int fullHealth) {
		this.fullHealth = fullHealth;
	}

	public int getDamageBonus() {
		return damageBonus;
	}

	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevelStatus() {
		return levelStatus;
	}

	public void setLevelStatus(int levelStatus) {
		this.levelStatus = levelStatus;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDamage() {
		// TODO Auto-generated method stub
		
	}

	// Custom exception untuk fighting character
	@SuppressWarnings("serial")
	public class FightingCharacterException extends Exception {
		public FightingCharacterException(String message) {
			super(message);
		}
	}
}
