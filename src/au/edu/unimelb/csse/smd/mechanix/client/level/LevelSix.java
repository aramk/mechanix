package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.ConveyorBelt;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelSix extends Level {

	private Box box = new Box(100, 50, true);
	private Bin bin = new Bin(300, 310, true);
	private Fan fan = new Fan(100, 10, false);
	private ConveyorBelt belt1 = new ConveyorBelt(100, 40, false);

	public LevelSix() {
		// TODO Auto-generated constructor stub
		addObject(box);
		addObject(bin);
		addObject(fan);
		addObject(belt1);

		//fan.setFixed(true);
		belt1.setFixed(true);

		setLevelInfo("Use two Conveyor belts to move the box to the bin");
	}

	@Override
	public boolean check() {
		return bin.isObjectInBin(box);
	}

	@Override
	public void addObjectsToLevel() {
		// TODO Auto-generated method stub
		
	}
}
