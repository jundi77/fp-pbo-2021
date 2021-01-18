package com.pbo.wws.entity;

public interface Animatable {

	public default void beforeAnimating(String state) {
		return;
	};
	public default void whenAnimating(String state) {
		return;
	};
	public default void afterAnimating(String state) {
		return;
	};
}
