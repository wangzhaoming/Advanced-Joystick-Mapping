package com.notech.game.event;

public interface JoyStickListener {
	void buttonPressed(JoyStickEvent e);
	void buttonReleased(JoyStickEvent e);
	void buttonClicked(JoyStickEvent e);
	
	void axeStarted(JoyStickEvent e);
	void axeStoped(JoyStickEvent e);
	void axeMoving(JoyStickEvent e);
}
