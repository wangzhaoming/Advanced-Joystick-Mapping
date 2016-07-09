package com.notech.game.input;

import java.util.HashMap;
import java.util.Map;

import com.ivan.xinput.XInputAxes;
import com.ivan.xinput.XInputButtons;
import com.ivan.xinput.XInputComponents;
import com.ivan.xinput.XInputDevice14;
import com.ivan.xinput.exceptions.XInputNotLoadedException;
import com.notech.game.event.EventDispatcher;
import com.notech.game.event.JoyStickEvent;

public class JoyStick {

	public static final int INTERVAL = 10;
	
	private Map<Integer, EventDispatcher> joyStickInputs = new HashMap<>();

	{
		joyStickInputs.put(JoyStickEvent.LEFT_AXIS, new JoyStickAxis(JoyStickEvent.LEFT_AXIS));
		joyStickInputs.put(JoyStickEvent.RIGHT_AXIS, new JoyStickAxis(JoyStickEvent.RIGHT_AXIS));
		joyStickInputs.put(JoyStickEvent.LEFT_TRIGGER, new JoyStickAxis(JoyStickEvent.LEFT_TRIGGER));
		joyStickInputs.put(JoyStickEvent.RIGHT_TRIGGER, new JoyStickAxis(JoyStickEvent.RIGHT_TRIGGER));

		joyStickInputs.put(JoyStickEvent.BUTTON_A, new JoyStickButton(JoyStickEvent.BUTTON_A));
		joyStickInputs.put(JoyStickEvent.BUTTON_B, new JoyStickButton(JoyStickEvent.BUTTON_B));
		joyStickInputs.put(JoyStickEvent.BUTTON_X, new JoyStickButton(JoyStickEvent.BUTTON_X));
		joyStickInputs.put(JoyStickEvent.BUTTON_Y, new JoyStickButton(JoyStickEvent.BUTTON_Y));
		joyStickInputs.put(JoyStickEvent.BUTTON_BACK, new JoyStickButton(JoyStickEvent.BUTTON_BACK));
		joyStickInputs.put(JoyStickEvent.BUTTON_MENU, new JoyStickButton(JoyStickEvent.BUTTON_MENU));
		joyStickInputs.put(JoyStickEvent.BUTTON_LEFT, new JoyStickButton(JoyStickEvent.BUTTON_LEFT));
		joyStickInputs.put(JoyStickEvent.BUTTON_RIGHT, new JoyStickButton(JoyStickEvent.BUTTON_RIGHT));
		joyStickInputs.put(JoyStickEvent.BUTTON_UP, new JoyStickButton(JoyStickEvent.BUTTON_UP));
		joyStickInputs.put(JoyStickEvent.BUTTON_DOWN, new JoyStickButton(JoyStickEvent.BUTTON_DOWN));
		joyStickInputs.put(JoyStickEvent.BUTTON_LS, new JoyStickButton(JoyStickEvent.BUTTON_LS));
		joyStickInputs.put(JoyStickEvent.BUTTON_RS, new JoyStickButton(JoyStickEvent.BUTTON_RS));
		joyStickInputs.put(JoyStickEvent.BUTTON_LB, new JoyStickButton(JoyStickEvent.BUTTON_LB));
		joyStickInputs.put(JoyStickEvent.BUTTON_RB, new JoyStickButton(JoyStickEvent.BUTTON_RB));
	}
	
	

	private XInputDevice14 device;

	static {
		if (!XInputDevice14.isAvailable()) {
			throw new IllegalStateException("XInput 1.4 is available on this platform.");
		}
	}

	public JoyStick() throws XInputNotLoadedException {
		device = XInputDevice14.getDeviceFor(0);
	}

	public void poll() throws InterruptedException {
		while (true) {
			if (device.poll()) {
				XInputComponents components = device.getComponents();

				XInputAxes axes = components.getAxes();

				getJoyStickAxis(JoyStickEvent.LEFT_AXIS).update(axes.lx, axes.ly);
				getJoyStickAxis(JoyStickEvent.RIGHT_AXIS).update(axes.rx, axes.ry);
				getJoyStickAxis(JoyStickEvent.LEFT_TRIGGER).update(axes.lt);
				getJoyStickAxis(JoyStickEvent.RIGHT_TRIGGER).update(axes.rt);

				XInputButtons buttons = components.getButtons();

				getJoyStickButton(JoyStickEvent.BUTTON_A).update(buttons.a);
				getJoyStickButton(JoyStickEvent.BUTTON_B).update(buttons.b);
				getJoyStickButton(JoyStickEvent.BUTTON_X).update(buttons.x);
				getJoyStickButton(JoyStickEvent.BUTTON_Y).update(buttons.y);
				getJoyStickButton(JoyStickEvent.BUTTON_BACK).update(buttons.back);
				getJoyStickButton(JoyStickEvent.BUTTON_MENU).update(buttons.start);
				getJoyStickButton(JoyStickEvent.BUTTON_LEFT).update(buttons.left);
				getJoyStickButton(JoyStickEvent.BUTTON_RIGHT).update(buttons.right);
				getJoyStickButton(JoyStickEvent.BUTTON_UP).update(buttons.up);
				getJoyStickButton(JoyStickEvent.BUTTON_DOWN).update(buttons.down);
				getJoyStickButton(JoyStickEvent.BUTTON_LS).update(buttons.lThumb);
				getJoyStickButton(JoyStickEvent.BUTTON_RS).update(buttons.rThumb);
				getJoyStickButton(JoyStickEvent.BUTTON_LB).update(buttons.lShoulder);
				getJoyStickButton(JoyStickEvent.BUTTON_RB).update(buttons.rShoulder);
			}
			Thread.sleep(INTERVAL);
		}
	}

	public JoyStickButton getJoyStickButton(int code) {
		return (JoyStickButton) joyStickInputs.get(code);
	}
	
	public JoyStickAxis getJoyStickAxis(int code) {
		return (JoyStickAxis) joyStickInputs.get(code);
	}
	
	public EventDispatcher getJoyStickInput(int code) {
		return joyStickInputs.get(code);
	}
}
