package com.notech.game.event;

public class JoyStickEvent {
	// Events
	public static final int BUTTON_PRESSED = 0;
	public static final int BUTTON_RELEASED = 1;
	public static final int BUTTON_CLICKED = 2;
	public static final int AXIS_STARTED = 3;
	public static final int AXIS_STOPPED = 4;
	public static final int AXIS_MOVING = 5;

	public static final int LEFT_TRIGGER = 0;
	public static final int RIGHT_TRIGGER = 1;
	public static final int LEFT_AXIS = 2;
	public static final int RIGHT_AXIS = 3;

	public static final int BUTTON_A = 0;
	public static final int BUTTON_B = 1;
	public static final int BUTTON_X = 2;
	public static final int BUTTON_Y = 3;
	public static final int BUTTON_LEFT = 13;
	public static final int BUTTON_RIGHT = 11;
	public static final int BUTTON_UP = 10;
	public static final int BUTTON_DOWN = 12;
	public static final int BUTTON_LB = 4;
	public static final int BUTTON_RB = 5;
	public static final int BUTTON_BACK = 6;
	public static final int BUTTON_MENU = 7;
	public static final int BUTTON_LS = 8;
	public static final int BUTTON_RS = 9;

	private int eventType = Integer.MIN_VALUE;
	private int sourceButton = Integer.MIN_VALUE;
	private int sourceAxis = Integer.MIN_VALUE;
	private float x = Float.MIN_VALUE;
	private float y = Float.MIN_VALUE;

	// 按键
	public JoyStickEvent(int eventType, int srcButton) {
		this.eventType = eventType;
		this.sourceButton = srcButton;
	}

	// 摇杆/扳机
	public JoyStickEvent(int eventType, int srcAxis, float x, float y) {
		this.eventType = eventType;
		this.sourceAxis = srcAxis;
		this.x = x;
		this.y = y;
	}

	public JoyStickEvent(int eventType, int srcAxis, float x) {
		this(eventType, srcAxis, x, 0);
	}

	public int getEventType() {
		return eventType;
	}

	public int getSourceButton() {
		return sourceButton;
	}

	public int getSourceAxis() {
		return sourceAxis;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

}
