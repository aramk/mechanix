package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class TestDuck extends GameObject {
	public TestDuck(int x, int y) {
		super(Resources.image.getDuck(), x, y);
	}

	public TestDuck(int x, int y, boolean isOnStage) {
		this(x, y);
		setOnStage(isOnStage);
	}

	@Override
	public void update(ArrayList<GameObject> obs, int delta) {
		this.setX(this.getX() + 100);
	}

}
