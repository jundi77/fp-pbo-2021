package com.pbo.wws.entity;

public interface CanAttack {
	
	// TODO atur parameternya apa
	public void attack(String attackName, CanAttack target);
	public void getDamage(int damage);
}
