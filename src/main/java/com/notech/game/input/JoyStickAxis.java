package com.notech.game.input;

import com.notech.game.Instructions;
import com.notech.game.event.EventDispatcher;
import com.notech.game.event.JoyStickAdapter;
import com.notech.game.event.JoyStickEvent;
import com.notech.game.simulate.Button;
import com.notech.game.simulate.Device;

public class JoyStickAxis extends EventDispatcher {
	
	private static final int AXIS_STATE_STARTED = 1;
	private static final int AXIS_STATE_STOPPED = 0;

	private static final float THRESHOLD = 0.5f;

	private int axisId;
	private int state = AXIS_STATE_STOPPED;

	protected JoyStickAxis(int axisId) {
		this.axisId = axisId;
	}

	/**
	 * for axis
	 * 
	 * @param x
	 * @param y
	 */
	public void update(float x, float y) {
		x = transform(x);
		y = transform(y);

		if (x == 0 && y == 0) {
			if (state == AXIS_STATE_STARTED) {
				state = AXIS_STATE_STOPPED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_STOPPED, axisId, 0, 0));
			}
		} else {
			if (state == AXIS_STATE_STOPPED) {
				state = AXIS_STATE_STARTED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_STARTED, axisId, x, y));
			} else {
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_MOVING, axisId, x, y));
			}
		}
	}
	
	/**
	 * for trigger
	 * 
	 * @param x
	 */
	public void update(float x) {
		x = transform(x);

		if (x == 0) {
			if (state == AXIS_STATE_STARTED) {
				state = AXIS_STATE_STOPPED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_STOPPED, axisId, 0));
			}
		} else {
			if (state == AXIS_STATE_STOPPED) {
				state = AXIS_STATE_STARTED;
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_STARTED, axisId, x));
			} else {
				processEvent(new JoyStickEvent(JoyStickEvent.AXIS_MOVING, axisId, x));
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
			public void axisStarted(JoyStickEvent e) {
				press.execute();
			}
			
			@Override
			public void axisStoped(JoyStickEvent e) {
				release.execute();
			}
		});
	}
}
