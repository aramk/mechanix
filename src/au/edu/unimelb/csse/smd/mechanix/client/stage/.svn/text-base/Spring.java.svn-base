package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class Spring extends DynamicMachine {

	public Spring(int startX, int startY, boolean isOnStage) {
		super(Resources.image.getSpring(), startX, startY, isOnStage);
		// TODO Auto-generated constructor stub
		setFixed(true);
	}

	@Override
	protected void moveObjects(ArrayList<GameObject> stageObs, int delta) {
		// // TODO Auto-generated method stub
		for (int i = 0; i < stageObs.size(); i++) {
			GameObject o = stageObs.get(i);
			if (this == o || o.isFixed()) {
				return;
			}
			boolean leftCheck = o.getLeft() >= getLeft() && o.getLeft() <= getRight();
			boolean rightCheck = o.getRight() >= getLeft() && o.getRight() <= getRight();
			if (o.getBottom() == getTop() && (leftCheck || rightCheck)) {
				o.addVelocityY(-Physics.vMedium(1000));
			}
		}
	}
	
	public Spring(Spring spring) {
		super(spring);
	}
	
	public Object clone() {
		return new Spring(this);
	}
}
