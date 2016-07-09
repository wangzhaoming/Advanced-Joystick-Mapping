package com.notech.game.groovy

import static com.notech.game.groovy.ControllerBuilder.*

import java.awt.event.InputEvent

import com.notech.game.event.JoyStickEvent
import com.notech.game.simulate.Device

class Test {

	def static main(args) {
		Device.initialize()

		buttonPressed(JoyStickEvent.BUTTON_A) {
			click(InputEvent.BUTTON1_DOWN_MASK)
		}
		axisMoving(JoyStickEvent.RIGHT_AXIS) {
			cursorMove(it)
		}

		getJoyStick().poll()
	}
}
