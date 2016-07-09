package com.notech.game.groovy

import com.notech.game.Instructions
import com.notech.game.event.JoyStickAdapter
import com.notech.game.event.JoyStickEvent
import com.notech.game.event.JoyStickListener
import com.notech.game.input.JoyStick
import com.notech.game.simulate.Button
import com.notech.game.simulate.Device
import com.notech.game.simulate.Cursor


class ControllerBuilder {

	static final int CURSOR_SPEED = 10;
	static final int OFFSET = 100;

	static JoyStick joyStick = new JoyStick()

	private ControllerBuilder() {
	}

	def static buttonPressed(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void buttonPressed(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static buttonReleased(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void buttonReleased(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static buttonClicked(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void buttonClicked(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static axisStarted(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void axisStarted(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static axisStoped(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void axisStoped(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static axisMoving(int joyStickCode, closure) {
		def dispatcher = joyStick.getJoyStickInput(joyStickCode)
		dispatcher.addJoyStickEventListener(new JoyStickAdapter() {
					@Override
					public void axisMoving(JoyStickEvent e) {
						closure(e)
					}
				})
	}

	def static press(int keycode) {
		Device.create(keycode).trigger(Button.BUTTON_PRESS)
	}

	def static click(int keycode) {
		Device.create(keycode).trigger(Button.BUTTON_CLICK)
	}

	def static release(int keycode) {
		Device.create(keycode).trigger(Button.BUTTON_RELEASE)
	}

	def static suspend(int keycode) {
		Device.create(keycode).trigger(Button.BUTTON_SUSPEND)
	}

	def static resume(int keycode) {
		Device.create(keycode).trigger(Button.BUTTON_RESUME)
	}

	def static cursorMove(JoyStickEvent e) {
		int src = e.getSourceButton() == Integer.MIN_VALUE ? e.getSourceAxis() : e.getSourceButton()
		Device.create(Device.CURSOR).trigger(Cursor.CURSOR_MOVE, src, (int) (e.getX() * CURSOR_SPEED), (int) (-e.getY() * CURSOR_SPEED))
	}

	def static cursorMoveAbsolute(JoyStickEvent e, x, y) {
		int src = e.getSourceButton() == Integer.MIN_VALUE ? e.getSourceAxis() : e.getSourceButton()
		Device.create(Device.CURSOR).trigger(Cursor.CURSOR_MOVE_TO_ABSOLUTE_POINT, src, x, y)
	}

	def static cursorMoveRelative(JoyStickEvent e, x, y) {
		int src = e.getSourceButton() == Integer.MIN_VALUE ? e.getSourceAxis() : e.getSourceButton()

		int dx = (int) (e.getX() * OFFSET);
		int dy = (int) (-e.getY() * OFFSET);

		Device.create(Device.CURSOR).trigger(Cursor.CURSOR_MOVE_RELATIVE_TO_POINT, src, dx, dy, x, y)
	}

	def static cursorOccupy(JoyStickEvent e) {
		int src = e.getSourceButton() == Integer.MIN_VALUE ? e.getSourceAxis() : e.getSourceButton()
		Device.create(Device.CURSOR).trigger(Cursor.CURSOR_OCCUPY, src)
	}

	def static cursorFree(JoyStickEvent e) {
		Device.create(Device.CURSOR).trigger(Cursor.CURSOR_FREE)
	}
}
