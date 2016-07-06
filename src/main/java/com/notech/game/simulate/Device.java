package com.notech.game.simulate;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

public abstract class Device {
	
	public static final int CURSOR = Integer.MAX_VALUE;
	
	protected static Robot robot;
	private static Map<Integer, Device> devices;
	
	public static void initialize() throws AWTException {
		robot = new Robot();
		devices = new HashMap<Integer, Device>();
	}
	
	public abstract boolean trigger(int event, Object... parameters);
	
	public static synchronized Device create(int keycode) {
		Device device = devices.get(keycode);
		
		if (device == null) {
			if (keycode == CURSOR) {
				device = new Cursor();
			} else {
				device = new Button(keycode);
			}
			devices.put(keycode, device);
		}
		
		return device;
	}
}
