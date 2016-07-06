package com.notech.game.simulate;

public class Button extends Device {

	public static final int BUTTON_PRESS = 0;
	public static final int BUTTON_RELEASE = 1;
	public static final int BUTTON_CLICK = 2;
	public static final int BUTTON_SUSPEND = 3;
	public static final int BUTTON_RESUME = 4;

	private static final int BUTTON_STATE_PRESSED = 1;
	private static final int BUTTON_STATE_RELEASED = 0;
	private static final int BUTTON_STATE_SUSPENDED = 1 << 10;

	private int state = BUTTON_STATE_RELEASED;

	private int keycode = 0;

	protected Button(int keycode) {
		this.keycode = keycode;
	}

	@Override
	public boolean trigger(int event, Object... parameters) {
		switch (event) {
		case BUTTON_CLICK:
			return click();
		case BUTTON_PRESS:
			return press();
		case BUTTON_RELEASE:
			return release();
		case BUTTON_SUSPEND:
			return suspend();
		case BUTTON_RESUME:
			return resume();
		}

		return false;
	}

	private boolean suspend() {
		if ((state & BUTTON_STATE_SUSPENDED) == BUTTON_STATE_SUSPENDED) {
			return false;
		}

		if (state == BUTTON_STATE_PRESSED) {
			release0();
		}
		state = state | BUTTON_STATE_SUSPENDED;

		return true;
	}

	private boolean resume() {
		if ((state & BUTTON_STATE_SUSPENDED) != BUTTON_STATE_SUSPENDED) {
			return false;
		}

		state = state & ~BUTTON_STATE_SUSPENDED;

		if (state == BUTTON_STATE_PRESSED) {
			press0();
		}
		return true;
	}

	private boolean press() {
		// 挂起状态只修改state，不触发实际操作
		if ((state & BUTTON_STATE_SUSPENDED) == BUTTON_STATE_SUSPENDED) {
			state = BUTTON_STATE_SUSPENDED | BUTTON_STATE_PRESSED;
			return false;
		}

		if (state == BUTTON_STATE_PRESSED) {
			return false;
		}

		state = BUTTON_STATE_PRESSED;
		press0();
		return true;
	}

	private void press0() {
		if (keycode >= 1024) {
			robot.mousePress(keycode);
		} else {
			robot.keyPress(keycode);
		}
	}

	private boolean release() {
		if ((state & BUTTON_STATE_SUSPENDED) == BUTTON_STATE_SUSPENDED) {
			state = BUTTON_STATE_SUSPENDED;
			return false;
		}

		if (state == BUTTON_STATE_RELEASED) {
			return false;
		}

		state = BUTTON_STATE_RELEASED;
		release0();
		return true;
	}

	private void release0() {
		if (keycode >= 1024) {
			robot.mouseRelease(keycode);
		} else {
			robot.keyRelease(keycode);
		}
	}

	private boolean click() {
		if (press()) {
			return release();
		}
		return false;
	}

}
