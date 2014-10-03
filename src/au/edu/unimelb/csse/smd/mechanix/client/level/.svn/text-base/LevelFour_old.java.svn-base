package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.ConveyorBelt;
import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Lift;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Physics;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelFour_old extends Level {

	private Box box = new Box(300, 20, true);
	private Bin bin = new Bin(630, 310, true);
	private ConveyorBelt belt = new ConveyorBelt(100, 10, false);
	private Lift lift = new Lift(100, 40, false);

	public LevelFour_old() {

		addObject(box);
		addObject(bin);
		addObject(lift);
		addObject(belt);
		// setting the direction of the conveyor belt
		belt.setDirection(Physics.DIR_RIGHT);
		setLevelInfo("Use conveyor belt and the lift to move the box to the bin.");

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
