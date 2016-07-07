package com.notech.game.event;

import java.util.ArrayList;
import java.util.List;

public abstract class EventDispatcher {
	private List<JoyStickListener> joyStickListeners = new ArrayList<JoyStickListener>();

	public void addJoyStickEventListener(JoyStickListener listener) {
		this.joyStickListeners.add(listener);
	}

	public void processEvent(JoyStickEvent e) {
		if (joyStickListeners.isEmpty()) {
			return;
		}

		for (JoyStickListener joyStickListener : joyStickListeners) {
			switch (e.getEventType()) {
			case JoyStickEvent.BUTTON_CLICKED:
				joyStickListener.buttonClicked(e);
				break;
			case JoyStickEvent.BUTTON_PRESSED:
				joyStickListener.buttonPressed(e);
				break;
			case JoyStickEvent.BUTTON_RELEASED:
				joyStickListener.buttonReleased(e);
				break;
			case JoyStickEvent.AXIS_STARTED:
				joyStickListener.axisStarted(e);
				break;
			case JoyStickEvent.AXIS_STOPPED:
				joyStickListener.axisStoped(e);
				break;
			case JoyStickEvent.AXIS_MOVING:
				joyStickListener.axisMoving(e);
				break;
			}
		}
	}
}
