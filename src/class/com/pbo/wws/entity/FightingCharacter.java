package com.pbo.wws.entity;

import java.util.HashMap;

import com.pbo.wws.entity.FightingCharacter.FightingCharacterException;

public abstract class FightingCharacter extends Character implements CanAttack {
	private HashMap<String, Integer> attacks; // menyimpan attack yang bisa dilakukan dan damagenya
	private int health, fullHealth,
				// tidak dipakai karena belum ada sistem level
				// ||
				// VV
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

	public void setHealth(int health) throws FightingCharacterException {
		if (health > fullHealth) {
			throw new FightingCharacterException("[FightingCharacter] health melebihi full health");
		}

		this.health = health;
	}

	public int getFullHealth() {
		return fullHealth;
	}

	public void setFullHealth(int fullHealth) throws FightingCharacterException {
		if (health > fullHealth) {
			throw new FightingCharacterException("[FightingCharacter] health melebihi full health");
		}

		this.fullHealth = fullHealth;
	}

	@Override
	public void attack(String attackName, CanAttack target) {
		Integer damage = this.attacks.get(attackName);
		if (damage != null) {
			target.getDamage(damage);
		}
	}

	@Override
	public void getDamage(int damage) {
		if (this.health > 0)  {
			this.health -= damage;
			this.health = (this.health < 0)? 0 : this.health;
		}
	}

	// Custom exception untuk fighting character
	@SuppressWarnings("serial")
	public class FightingCharacterException extends CharacterException {
		public FightingCharacterException(String message) {
			super(message);
		}
	}
}
