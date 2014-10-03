package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import com.google.gwt.resources.client.ImageResource;

public abstract class DynamicMachine extends GameObject implements Cloneable {

	// Some machines will have constant acceleration and use speed instead
	private double speed = 0;
	private double acceleration = 0;

	// Base Contructor
	public DynamicMachine(ImageResource img, int startX, int startY,
			boolean isOnStage) {
		super(img, startX, startY, isOnStage);
	}

	// Copy Constructor
	public DynamicMachine(DynamicMachine dynamicMachine) {
		super(dynamicMachine);
		setSpeed(dynamicMachine.getSpeed());
		setAcceleration(dynamicMachine.getAcceleration());
	}

	@Override
	public void update(ArrayList<GameObject> obs, int delta) {
		super.update(obs, delta);
		moveObjects(obs, delta);
	}

	// Make movement to every related object
	abstract protected void moveObjects(ArrayList<GameObject> obs, int delta);

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getAcceleration() {
		return acceleration;
	}

}
