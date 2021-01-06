package com.pbo.wws;

public interface Movable{
	public static final int DIRECTION_UP = 3;
	public static final int DIRECTION_DOWN = 0;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_RIGHT = 1;
	public void setMovingStatus(boolean move);
	public boolean getMovingStatus();
}
