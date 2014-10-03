package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Platform;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelTest extends Level {
	private Box box, box2;
	private Fan fan;

	@Override
	public boolean check() {
		return false;
	}

	@Override
	public void addObjectsToLevel() {
		setLevelInfo("Use the fan to move the box to the bin.");
		box = new Box(400, 300, true);
		box2 = new Box(200, 300, true);
		fan = new Fan(600, 300, true);
		addObject(box);
		addObject(box2);
		addObject(fan);
	}
}
