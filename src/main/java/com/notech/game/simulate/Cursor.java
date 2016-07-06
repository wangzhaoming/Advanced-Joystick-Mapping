package com.notech.game.simulate;

import java.awt.MouseInfo;
import java.awt.Point;

public class Cursor extends Device {

	public static final int CURSOR_MOVE = 0;
	public static final int CURSOR_MOVE_TO_ABSOLUTE_POINT = 1;
	public static final int CURSOR_MOVE_RELATIVE_TO_POINT = 2;
	public static final int CURSOR_OCCUPY = 3;
	public static final int CURSOR_FREE = 4;
	
	private Object owner = null;
	
	protected Cursor() {
		
	}
	
	@Override
	public boolean trigger(int event, Object... parameters) {
		if (event == CURSOR_OCCUPY) {
			if (owner == null) {
				owner = parameters[0];
				return true;
			}
		} else if (event == CURSOR_FREE) {
			if (owner != null) {
				owner = null;
				return true;
			}
		} else {
			return move(event, parameters);
		}
		
		return false;
	}
	
	private boolean move(int event, Object... parameters) {
		if (owner != null && owner != parameters[0]) {
			return false;
		}
		
		if (parameters.length < 3) {
			return false;
		}
		
		Point point = MouseInfo.getPointerInfo().getLocation();
		int x = (int) point.getX();
		int y = (int) point.getY();
		
		if (event == CURSOR_MOVE) {
			int dx = (Integer) parameters[1];
			int dy = (Integer) parameters[2];
			
			x = x + dx;
			y = y + dy;
			
		} else if (event == CURSOR_MOVE_TO_ABSOLUTE_POINT) {
			x = (Integer) parameters[1];
			y = (Integer) parameters[2];
		} else if (event == CURSOR_MOVE_RELATIVE_TO_POINT) {
			x = (Integer) parameters[3] + (Integer) parameters[1];
			y = (Integer) parameters[4] + (Integer) parameters[2];
		} else {
			return false;
		}
		
		robot.mouseMove(x, y);
		return true;
	}
	
}
