package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class Fan extends SwitchMachine {

	private static final double RANGE = 500;

	public Fan(int startX, int startY, boolean isOnStage) {
		super(Resources.image.getFanLeft(), Resources.image.getFanRight(),
				startX, startY, isOnStage);
		setSolid(false);
	}
	
	/*public Fan(Fan fan) {
		super(fan);
	}
	
	public Object clone() {
		return new Fan(this);
	}*/

	@Override
	protected void moveObjects(ArrayList<GameObject> obs, int delta) {
		if (getDirection() == Physics.NO_DIRECTION) {
			return;
		}
		for (GameObject o : obs) {
			if (this == o || o.isFixed()) {
				// Ignore
				continue;
			}

			// System.out.println("");
			// System.out.println("object found " + o.getClass());

			// Determine if object is same elevation of fan
			boolean topCheck = getTop() >= o.getTop()
					&& getTop() <= o.getBottom();
			boolean bottomCheck = getBottom() >= o.getTop()
					&& getBottom() <= o.getBottom();

			if (topCheck || bottomCheck) {
				// System.out.println("object in position");

				// The further the object is from the fan, the lesser the effect
				double absDistFromFan = Math.abs(getMiddleX() - o.getMiddleX());
				// System.out.println("dist from fan " + absDistFromFan);

				double ratio = -Math.pow((1 / RANGE * absDistFromFan), 2) + 1;

				// System.out.println("ratio " + ratio);

				double addVelocity = Physics.vMedium(delta) * ratio;
				if (addVelocity < 0) {
					continue;
				}
				// System.out.println("addvel " + addVelocity);

				// Determine if object is in direction of fan
				if (getDirection() < 0 && getRight() >= o.getRight()) {
					o.addVelocityX(-addVelocity);
				} else if (getDirection() > 0 && getLeft() <= o.getLeft()) {
					o.addVelocityX(addVelocity);
				}
			}
		}
	}

}