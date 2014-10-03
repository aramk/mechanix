
package au.edu.unimelb.csse.smd.mechanix.client.level;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelThree_old extends Level {

	private Box box = new Box(100, 50, true);
	private Bin bin = new Bin(630, 310, true);
	private Fan fan1 = new Fan(100, 10, false);
	private Fan fan2 = new Fan(100, 40, false);

	public LevelThree_old() {
		// TODO Auto-generated constructor stub
		addObject(box);
		addObject(bin);
		addObject(fan1);
		addObject(fan2);

		fan1.setFixed(true);
		fan2.setFixed(true);

		setLevelInfo("Use two fans to move the box to the bin");

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
