package com.pbo.wws.entity;

public interface CanAttack {
	public void attack(String attackName, CanAttack target);
	public void getDamage(int damage);
}
