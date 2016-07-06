package com.notech.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.notech.game.simulate.Device;



public class Instructions {
	private List<Instruction> instructions = new ArrayList<Instruction>();
	
	public Instructions add(Device device, Object...parameters) {
		Instruction instruction = new Instruction();
		instruction.setDevice(device);
		instruction.setParameters(parameters);
		
		this.instructions.add(instruction);
		return this;
	}
	
	public void execute() {
		for (Instruction instruction : instructions) {
			Device device = instruction.getDevice();
			Object[] params = instruction.getParameters();
			int event = (Integer) params[0];
			
			if (params.length > 1) {
				params = Arrays.copyOfRange(params, 1, params.length);
			} else {
				params = new Object[0];
			}
			
			device.trigger(event, params);
		}
	}
	
	class Instruction {
		private Device device;
		
		private Object[] parameters;

		public Device getDevice() {
			return device;
		}

		public void setDevice(Device device) {
			this.device = device;
		}

		public Object[] getParameters() {
			return parameters;
		}

		public void setParameters(Object[] parameters) {
			this.parameters = parameters;
		}
	}
}
