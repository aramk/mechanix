package au.edu.unimelb.csse.smd.mechanix.client.stage;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class Box extends GameObject implements Cloneable {

	public Box(int x, int y) {
		super(Resources.image.getBox(), x, y);
		setLocked(true);
	}

	public Box(int x, int y, boolean isOnStage) {
		this(x, y);
		setOnStage(isOnStage);
	}
	
	public Box(Box box) {
		super(box);
	}
	
	public Object clone() {
		return new Box(this);
	}

}
