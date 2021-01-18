package com.pbo.wws.entity;

public interface Movable{
	public static final int DIRECTION_UP = 3;
	public static final int DIRECTION_DOWN = 0;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_RIGHT = 1;

	public void setMovingStatus(boolean move);
	public boolean getMovingStatus();
	
	public default int getDirection() {
		return DIRECTION_DOWN;
	}

	public default void setDirection(int direction) {
		return;
	}

	public default int getXSpeed() {
		return 0;
	}

	public default void setXSpeed(int speed) {
		return;
	}

	public default int getYSpeed() {
		return 0;
	}

	public default void setYSpeed(int speed) {
		return;
	}
}
