package com.notech.game.input;

import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickAxes;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;
import static org.lwjgl.glfw.GLFW.glfwInit;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.notech.game.event.JoyStickEvent;

public class JoyStick {
	
	public static final int BUTTON_PRESSED = 1;
	public static final int BUTTON_RELEASED = 0;
	public static final int AXE_STARTED = 1;
	public static final int AXE_STOPPED = 0;
	
	public static final int INTERVAL = 10;

	private JoyStickAxe leftAxe = new JoyStickAxe(JoyStickEvent.LEFT_AXE);
	private JoyStickAxe rightAxe = new JoyStickAxe(JoyStickEvent.RIGHT_AXE);
	private JoyStickAxe leftTrigger = new JoyStickAxe(JoyStickEvent.LEFT_TRIGGER);
	private JoyStickAxe rightTrigger = new JoyStickAxe(JoyStickEvent.RIGHT_TRIGGER);
	
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
	
	
	static {
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
	}

	public void poll() throws InterruptedException {
		while (true) {
			FloatBuffer buffer1 = glfwGetJoystickAxes(GLFW_JOYSTICK_1);
			leftAxe.update(buffer1.get(JoyStickEvent.LEFT_AXE_H), buffer1.get(JoyStickEvent.LEFT_AXE_V));
			rightAxe.update(buffer1.get(JoyStickEvent.RIGHT_AXE_H), buffer1.get(JoyStickEvent.RIGHT_AXE_V));
			leftTrigger.update((buffer1.get(JoyStickEvent.LEFT_TRIGGER) + 1) / 2, 0);
			rightTrigger.update((buffer1.get(JoyStickEvent.RIGHT_TRIGGER) + 1) / 2, 0);
			
			ByteBuffer buffer2 = glfwGetJoystickButtons(GLFW_JOYSTICK_1);
			btnA.update(buffer2.get(JoyStickEvent.BUTTON_A));
			btnB.update(buffer2.get(JoyStickEvent.BUTTON_B));
			btnX.update(buffer2.get(JoyStickEvent.BUTTON_X));
			btnY.update(buffer2.get(JoyStickEvent.BUTTON_Y));
			btnBack.update(buffer2.get(JoyStickEvent.BUTTON_BACK));
			btnMenu.update(buffer2.get(JoyStickEvent.BUTTON_MENU));
			btnLeft.update(buffer2.get(JoyStickEvent.BUTTON_LEFT));
			btnRight.update(buffer2.get(JoyStickEvent.BUTTON_RIGHT));
			btnUp.update(buffer2.get(JoyStickEvent.BUTTON_UP));
			btnDown.update(buffer2.get(JoyStickEvent.BUTTON_DOWN));
			btnLs.update(buffer2.get(JoyStickEvent.BUTTON_LS));
			btnRs.update(buffer2.get(JoyStickEvent.BUTTON_RS));
			btnLb.update(buffer2.get(JoyStickEvent.BUTTON_LB));
			btnRb.update(buffer2.get(JoyStickEvent.BUTTON_RB));
			
			Thread.sleep(INTERVAL);
		}
	}

	public JoyStickAxe getLeftAxe() {
		return leftAxe;
	}


	public JoyStickAxe getRightAxe() {
		return rightAxe;
	}


	public JoyStickAxe getLeftTrigger() {
		return leftTrigger;
	}


	public JoyStickAxe getRightTrigger() {
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
