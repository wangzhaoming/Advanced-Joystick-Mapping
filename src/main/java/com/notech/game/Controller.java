package com.notech.game;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.notech.game.event.JoyStickAdapter;
import com.notech.game.event.JoyStickEvent;
import com.notech.game.event.JoyStickListener;
import com.notech.game.input.JoyStick;
import com.notech.game.simulate.Button;
import com.notech.game.simulate.Cursor;
import com.notech.game.simulate.Device;

public class Controller {

	private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private static final int OFFSET = 100;
	private static final int CURSOR_SPEED = 5;
	private static final int CORRECTION_H = -40;
	private static final int CORRECTION_H_ATTACK = -100;

	public static void main(String[] args) throws InterruptedException, AWTException {
		(new Controller()).run();
	}

	private static final int MODE_FREE_CURSOR = 0;
	private static final int MODE_IN_GAME = 1;

	private JoyStick joyStick;
	private int mode = MODE_FREE_CURSOR;

	private void run() throws AWTException, InterruptedException {
		joyStick = new JoyStick();
		Device.initialize();

		addListeners();

		joyStick.poll();
	}

	private void addListeners() {
		joyStick.getLeftAxe().bind(KeyEvent.VK_W);

		joyStick.getLeftAxe().addJoyStickEventListener(new JoyStickAdapter() {
			@Override
			public void axeMoving(JoyStickEvent e) {
				Instructions moveCursor = new Instructions();

				int dx = (int) (e.getAxeValueX() * OFFSET);
				int dy = (int) (-e.getAxeValueY() * OFFSET);

				moveCursor.add(Device.create(Device.CURSOR), Cursor.CURSOR_MOVE_RELATIVE_TO_POINT, e.getSourceAxe(), dx, dy,
						SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + CORRECTION_H).execute();
			}
		});

		Instructions pressShift = new Instructions();
		pressShift.add(Device.create(KeyEvent.VK_SHIFT), Button.BUTTON_PRESS);
		Instructions releaseShift = new Instructions();
		releaseShift.add(Device.create(KeyEvent.VK_SHIFT), Button.BUTTON_RELEASE);
		
		Instructions pressMouseLeft = new Instructions();
		pressMouseLeft.add(Device.create(InputEvent.BUTTON1_DOWN_MASK), Button.BUTTON_PRESS);
		Instructions releaseMouseLeft = new Instructions();
		releaseMouseLeft.add(Device.create(InputEvent.BUTTON1_DOWN_MASK), Button.BUTTON_RELEASE);

		joyStick.getRightAxe().addJoyStickEventListener(new JoyStickAdapter() {
			@Override
			public void axeMoving(JoyStickEvent e) {
				if (mode == MODE_FREE_CURSOR) {
					Instructions moveCursor = new Instructions();

					int dx = (int) (e.getAxeValueX() * CURSOR_SPEED);
					int dy = (int) (-e.getAxeValueY() * CURSOR_SPEED);

					moveCursor.add(Device.create(Device.CURSOR), Cursor.CURSOR_MOVE, e.getSourceAxe(), dx, dy).execute();
				} else {
					Instructions moveCursor = new Instructions();

					int dx = (int) (e.getAxeValueX() * OFFSET);
					int dy = (int) (-e.getAxeValueY() * OFFSET);

					moveCursor.add(Device.create(Device.CURSOR), Cursor.CURSOR_MOVE_RELATIVE_TO_POINT, e.getSourceAxe(), dx, dy,
							SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2 + CORRECTION_H_ATTACK).execute();
				}
			}

			@Override
			public void axeStarted(JoyStickEvent e) {
				Instructions occupyCursor = new Instructions();
				occupyCursor.add(Device.create(Device.CURSOR), Cursor.CURSOR_OCCUPY, e.getSourceAxe()).execute();
				
				if (mode == MODE_IN_GAME) {
					pressShift.execute();
				}
				axeMoving(e);
				if (mode == MODE_IN_GAME) {
					pressMouseLeft.execute();
				}
			}

			@Override
			public void axeStoped(JoyStickEvent e) {
				Instructions freeCursor = new Instructions();
				freeCursor.add(Device.create(Device.CURSOR), Cursor.CURSOR_FREE).execute();
				releaseShift.execute();
				releaseMouseLeft.execute();
			}
		});
		
		joyStick.getBtnBack().addJoyStickEventListener(new JoyStickAdapter() {
			@Override
			public void buttonClicked(JoyStickEvent e) {
				if (mode == MODE_IN_GAME) {
					mode = MODE_FREE_CURSOR;
				} else {
					mode = MODE_IN_GAME;
				}
			}
		});
		
		Instructions suspendMouseLeft = new Instructions();
		suspendMouseLeft.add(Device.create(InputEvent.BUTTON1_DOWN_MASK), Button.BUTTON_SUSPEND);
		Instructions resumeMouseLeft = new Instructions();
		resumeMouseLeft.add(Device.create(InputEvent.BUTTON1_DOWN_MASK), Button.BUTTON_RESUME);
		
		JoyStickListener suspendMouseLeftHandler = new JoyStickAdapter() {
			@Override
			public void axeStarted(JoyStickEvent e) {
				suspendMouseLeft.execute();
			}

			@Override
			public void axeStoped(JoyStickEvent e) {
				resumeMouseLeft.execute();
			}
			
			@Override
			public void buttonPressed(JoyStickEvent e) {
				suspendMouseLeft.execute();
			}

			@Override
			public void buttonReleased(JoyStickEvent e) {
				resumeMouseLeft.execute();
			}
		};
		
		joyStick.getBtnB().addJoyStickEventListener(suspendMouseLeftHandler);
		joyStick.getLeftTrigger().addJoyStickEventListener(suspendMouseLeftHandler);
		joyStick.getRightTrigger().addJoyStickEventListener(suspendMouseLeftHandler);
		joyStick.getBtnLb().addJoyStickEventListener(suspendMouseLeftHandler);
		joyStick.getBtnRb().addJoyStickEventListener(suspendMouseLeftHandler);
		
		joyStick.getBtnA().bind(InputEvent.BUTTON1_DOWN_MASK);
		joyStick.getBtnB().bind(InputEvent.BUTTON3_DOWN_MASK);
		joyStick.getLeftTrigger().bind(KeyEvent.VK_1);
		joyStick.getRightTrigger().bind(KeyEvent.VK_2);
		joyStick.getBtnLb().bind(KeyEvent.VK_3);
		joyStick.getBtnRb().bind(KeyEvent.VK_4);
		
		joyStick.getBtnUp().bind(KeyEvent.VK_SPACE);
		joyStick.getBtnDown().bind(KeyEvent.VK_T);
		joyStick.getBtnMenu().bind(KeyEvent.VK_ESCAPE);
		joyStick.getBtnX().bind(KeyEvent.VK_ENTER);
		joyStick.getBtnY().bind(KeyEvent.VK_TAB);
	}

}