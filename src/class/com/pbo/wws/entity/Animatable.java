package com.pbo.wws.entity;

public interface Animatable {
	public void playAnimation(String state);
	public boolean isAnimating();
	public String getCurrentAnimationState();

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
