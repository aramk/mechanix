package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class Bin extends GameObject {

	public Bin(int x, int y) {
		super(Resources.image.getBin(), x, y);
	}

	public Bin(int x, int y, boolean isOnStage) {
		this(x, y);
		setOnStage(isOnStage);
		setLocked(true);
		setFixed(true);
	}
	
	public boolean isObjectInBin(GameObject o) {
		boolean leftCheck = o.getLeft() >= getLeft() && o.getLeft() <= getRight();
		boolean rightCheck = o.getRight() >= getLeft() && o.getRight() <= getRight();
		if (o.getBottom() == getTop() && (leftCheck || rightCheck)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Bin(Bin bin) {
		super(bin);
	}
	
	public Object clone() {
		return new Bin(this);
	}

}
