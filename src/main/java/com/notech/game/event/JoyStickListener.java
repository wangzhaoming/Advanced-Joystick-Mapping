package com.notech.game.event;

public interface JoyStickListener {
	void buttonPressed(JoyStickEvent e);
	void buttonReleased(JoyStickEvent e);
	void buttonClicked(JoyStickEvent e);
	
	void axisStarted(JoyStickEvent e);
	void axisStoped(JoyStickEvent e);
	void axisMoving(JoyStickEvent e);
}
