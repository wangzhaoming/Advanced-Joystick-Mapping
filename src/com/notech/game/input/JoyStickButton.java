package com.notech.game.input;

import com.notech.game.Instructions;
import com.notech.game.event.EventDispatcher;
import com.notech.game.event.JoyStickAdapter;
import com.notech.game.event.JoyStickEvent;
import com.notech.game.simulate.Button;
import com.notech.game.simulate.Device;

public class JoyStickButton extends EventDispatcher {

	private int buttonId;
	private int state = JoyStick.BUTTON_RELEASED;
	
	protected JoyStickButton(int buttonId) {
		this.buttonId = buttonId;
	}
	
	public void update(int value) {
		if (value == 1) {
			if (state == JoyStick.BUTTON_RELEASED) {
				state = JoyStick.BUTTON_PRESSED;
				processEvent(new JoyStickEvent(JoyStickEvent.BUTTON_PRESSED, buttonId));
			}
		} else {
			if (state == JoyStick.BUTTON_PRESSED) {
				state = JoyStick.BUTTON_RELEASED;
				
				processEvent(new JoyStickEvent(JoyStickEvent.BUTTON_CLICKED, buttonId));
				processEvent(new JoyStickEvent(JoyStickEvent.BUTTON_RELEASED, buttonId));
			}
		}
	}
	
	public void bind(int keycode) {
		Instructions press = new Instructions();
		press.add(Device.create(keycode), Button.BUTTON_PRESS);
		Instructions release = new Instructions();
		release.add(Device.create(keycode), Button.BUTTON_RELEASE);
		
		this.addJoyStickEventListener(new JoyStickAdapter() {
			@Override
			public void buttonPressed(JoyStickEvent e) {
				press.execute();
			}
			
			@Override
			public void buttonReleased(JoyStickEvent e) {
				release.execute();
			}
		});
	}
	
}
