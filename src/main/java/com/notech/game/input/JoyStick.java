package com.notech.game.input;

import com.ivan.xinput.XInputAxes;
import com.ivan.xinput.XInputButtons;
import com.ivan.xinput.XInputComponents;
import com.ivan.xinput.XInputDevice14;
import com.ivan.xinput.exceptions.XInputNotLoadedException;
import com.notech.game.event.JoyStickEvent;

public class JoyStick {

	public static final int INTERVAL = 10;

	private JoyStickAxis leftAxis = new JoyStickAxis(JoyStickEvent.LEFT_AXIS);
	private JoyStickAxis rightAxis = new JoyStickAxis(JoyStickEvent.RIGHT_AXIS);
	private JoyStickAxis leftTrigger = new JoyStickAxis(JoyStickEvent.LEFT_TRIGGER);
	private JoyStickAxis rightTrigger = new JoyStickAxis(JoyStickEvent.RIGHT_TRIGGER);

	private JoyStickButton btnA = new JoyStickButton(JoyStickEvent.BUTTON_A);
	private JoyStickButton btnB = new JoyStickButton(JoyStickEvent.BUTTON_B);
	private JoyStickButton btnX = new JoyStickButton(JoyStickEvent.BUTTON_X);
	private JoyStickButton btnY = new JoyStickButton(JoyStickEvent.BUTTON_Y);
	private JoyStickButton btnBack = new JoyStickButton(JoyStickEvent.BUTTON_BACK);
	private JoyStickButton btnMenu = new JoyStickButton(JoyStickEvent.BUTTON_MENU);
	private JoyStickButton btnLeft = new JoyStickButton(JoyStickEvent.BUTTON_LEFT);
	private JoyStickButton btnRight = new JoyStickButton(JoyStickEvent.BUTTON_RIGHT);
	private JoyStickButton btnUp = new JoyStickButton(JoyStickEvent.BUTTON_UP);
	private JoyStickButton btnDown = new JoyStickButton(JoyStickEvent.BUTTON_DOWN);
	private JoyStickButton btnLs = new JoyStickButton(JoyStickEvent.BUTTON_LS);
	private JoyStickButton btnRs = new JoyStickButton(JoyStickEvent.BUTTON_RS);
	private JoyStickButton btnLb = new JoyStickButton(JoyStickEvent.BUTTON_LB);
	private JoyStickButton btnRb = new JoyStickButton(JoyStickEvent.BUTTON_RB);

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

				leftAxis.update(axes.lx, axes.ly);
				rightAxis.update(axes.rx, axes.ry);
				leftTrigger.update(axes.lt);
				rightTrigger.update(axes.rt);

				XInputButtons buttons = components.getButtons();

				btnA.update(buttons.a);
				btnB.update(buttons.b);
				btnX.update(buttons.x);
				btnY.update(buttons.y);
				btnBack.update(buttons.back);
				btnMenu.update(buttons.start);
				btnLeft.update(buttons.left);
				btnRight.update(buttons.right);
				btnUp.update(buttons.up);
				btnDown.update(buttons.down);
				btnLs.update(buttons.lThumb);
				btnRs.update(buttons.rThumb);
				btnLb.update(buttons.lShoulder);
				btnRb.update(buttons.rShoulder);
			}
			Thread.sleep(INTERVAL);
		}
	}

	public JoyStickAxis getLeftAxis() {
		return leftAxis;
	}

	public JoyStickAxis getRightAxis() {
		return rightAxis;
	}

	public JoyStickAxis getLeftTrigger() {
		return leftTrigger;
	}

	public JoyStickAxis getRightTrigger() {
		return rightTrigger;
	}

	public JoyStickButton getBtnA() {
		return btnA;
	}

	public JoyStickButton getBtnB() {
		return btnB;
	}

	public JoyStickButton getBtnX() {
		return btnX;
	}

	public JoyStickButton getBtnY() {
		return btnY;
	}

	public JoyStickButton getBtnBack() {
		return btnBack;
	}

	public JoyStickButton getBtnMenu() {
		return btnMenu;
	}

	public JoyStickButton getBtnLeft() {
		return btnLeft;
	}

	public JoyStickButton getBtnRight() {
		return btnRight;
	}

	public JoyStickButton getBtnUp() {
		return btnUp;
	}

	public JoyStickButton getBtnDown() {
		return btnDown;
	}

	public JoyStickButton getBtnLs() {
		return btnLs;
	}

	public JoyStickButton getBtnRs() {
		return btnRs;
	}

	public JoyStickButton getBtnLb() {
		return btnLb;
	}

	public JoyStickButton getBtnRb() {
		return btnRb;
	}
}
