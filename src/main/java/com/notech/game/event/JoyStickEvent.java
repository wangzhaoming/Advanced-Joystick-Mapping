package com.notech.game.event;

public class JoyStickEvent {
	public static final int BUTTON_PRESSED = 0;
	public static final int BUTTON_RELEASED = 1;
	public static final int BUTTON_CLICKED = 2;
	public static final int AXE_STARTED = 3;
	public static final int AXE_STOPPED = 4;
	public static final int AXE_MOVING = 5;
	
	public static final int LEFT_AXE_H = 0;
	public static final int LEFT_AXE_V = 1;
	public static final int RIGHT_AXE_H = 2;
	public static final int RIGHT_AXE_V = 3;
	public static final int LEFT_TRIGGER = 4; // -1 ~ 1
	public static final int RIGHT_TRIGGER = 5; // -1 ~ 1
	public static final int LEFT_AXE = 6;
	public static final int RIGHT_AXE = 7;
	
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
	private int sourceAxe = Integer.MIN_VALUE;
	private float axeValueX = Float.MIN_VALUE;
	private float axeValueY = Float.MIN_VALUE;
	
	// 按键
	public JoyStickEvent(int eventType, int srcButton) {
		this.eventType = eventType;
		this.sourceButton = srcButton;
	}
	
	// 摇杆/扳机
	public JoyStickEvent(int eventType, int srcAxe, float axeX, float axeY) {
		this.eventType = eventType;
		this.sourceAxe = srcAxe;
		this.axeValueX = axeX;
		this.axeValueY = axeY;
	}
	
	public int getEventType() {
		return eventType;
	}
	public int getSourceButton() {
		return sourceButton;
	}
	public int getSourceAxe() {
		return sourceAxe;
	}
	public float getAxeValueX() {
		return axeValueX;
	}
	public float getAxeValueY() {
		return axeValueY;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	public void setSourceButton(int sourceButton) {
		this.sourceButton = sourceButton;
	}
	public void setSourceAxe(int sourceAxe) {
		this.sourceAxe = sourceAxe;
	}
	public void setAxeValueX(float axeValueX) {
		this.axeValueX = axeValueX;
	}
	public void setAxeValueY(float axeValueY) {
		this.axeValueY = axeValueY;
	}
	
}
