package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Platform;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelOne extends Level {
	private Box box;
	private Bin bin;
	private Fan fan;
	private Platform platform;

	@Override
	public boolean check() {
		return bin != null && bin.isObjectInBin(box);
	}

	@Override
	public void addObjectsToLevel() {
		setLevelInfo("Use the fan to move the box to the bin.");
		platform = new Platform(200, 250, 400, 30);
		box = new Box(400, 100, true);
		bin = new Bin(630, 310, true);
		fan = new Fan(0, 0, false);
		addObject(platform);
		addObject(box);
		addObject(bin);
		addObject(fan);
	}
}
