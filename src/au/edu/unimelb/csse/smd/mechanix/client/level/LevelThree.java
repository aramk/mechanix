package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.ConveyorBelt;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Platform;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelThree extends Level {
	private Box box;
	private Bin bin;
	private Fan fan;
	private ConveyorBelt belt;
	private Platform platform;

	@Override
	public boolean check() {
		return bin != null && bin.isObjectInBin(box);
	}

	@Override
	public void addObjectsToLevel() {
		setLevelInfo("Use the fans, platforms and conveyor belt.");
		platform = new Platform(200, 250, 400, 30, false);
		platform.setLocked(false);
		box = new Box(150, 20, true);
		bin = new Bin(660, 310, true);
		fan = new Fan(0, 0, false);
		belt = new ConveyorBelt(0, 0, false);
		addObject(platform);
		addObject(box);
		addObject(bin);
		addObject(fan);
		addObject(belt);
	}
}
