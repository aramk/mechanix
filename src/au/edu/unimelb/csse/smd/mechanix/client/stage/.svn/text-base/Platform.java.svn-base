package au.edu.unimelb.csse.smd.mechanix.client.stage;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

public class Platform extends GameObject {

	public Platform(int x, int y, int width, int height) {
		super(Resources.image.getBlank(), x, y);
		setPixelSize(width, height);
		setLocked(true);
		setFixed(true);
		setOnStage(true);
		addStyleName("gwt-Image-Repeat");
	}
	
	public Platform(int x, int y, int width, int height, boolean isOnStage) {
		this(x, y, width, height);
		setOnStage(isOnStage);
	}
	
	public Platform(Platform platform) {
		super(platform);
	}
	
	public Object clone() {
		return new Platform(this);
	}

}
