package com.notech.game.input;

import com.notech.game.Instructions;
import com.notech.game.event.EventDispatcher;
import com.notech.game.event.JoyStickAdapter;
import com.notech.game.event.JoyStickEvent;
import com.notech.game.simulate.Button;
import com.notech.game.simulate.Device;

public class JoyStickAxe extends EventDispatcher {

	private static final float THRESHOLD = 0.1f;

	private int axeId;
	private int state = JoyStick.AXE_STOPPED;

	protected JoyStickAxe(int axeId) {
		this.axeId = axeId;
	}

	public void update(float axeValueX, float axeValueY) {
		axeValueX = transform(axeValueX);
		axeValueY = transform(axeValueY);

		if (axeValueX == 0 && axeValueY == 0) {
			if (state == JoyStick.AXE_STARTED) {
				state = JoyStick.AXE_STOPPED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXE_STOPPED, axeId, 0, 0));
			}
		} else {
			if (state == JoyStick.AXE_STOPPED) {
				state = JoyStick.AXE_STARTED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXE_STARTED, axeId, axeValueX, axeValueY));
			} else {
				processEvent(new JoyStickEvent(JoyStickEvent.AXE_MOVING, axeId, axeValueX, axeValueY));
			}
		}
	}

	private static float transform(float x) {
		return Math.abs(x) > THRESHOLD ? x : 0;
	}
	
	public void bind(int keycode) {
		Instructions press = new Instructions();
		press.add(Device.create(keycode), Button.BUTTON_PRESS);
		Instructions release = new Instructions();
		release.add(Device.create(keycode), Button.BUTTON_RELEASE);
		
		this.addJoyStickEventListener(new JoyStickAdapter() {
			@Override
			public void axeStarted(JoyStickEvent e) {
				press.execute();
			}
			
			@Override
			public void axeStoped(JoyStickEvent e) {
				release.execute();
			}
		});
	}
}
