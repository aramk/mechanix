package au.edu.unimelb.csse.smd.mechanix.client.level;

import com.google.gwt.user.client.Timer;

import au.edu.unimelb.csse.smd.mechanix.client.stage.Bin;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Box;
import au.edu.unimelb.csse.smd.mechanix.client.stage.ConveyorBelt;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Fan;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Physics;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Platform;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;

public class LevelFour extends Level {
	private Box box;
	private Bin bin;
	private Fan fan, fan2;
	private ConveyorBelt belt;
	private Platform platform, platform2;
	private Timer timer;

	@Override
	public boolean check() {
		return bin != null && bin.isObjectInBin(box);
	}

	@Override
	public void addObjectsToLevel() {
		setLevelInfo("Use the fans and oscillating conveyor belt.");
		platform = new Platform(0, 136, 600, 15, true);
		platform2 = new Platform(360, 340, 20, 80, true);
		//platform.setLocked(false);
		box = new Box(150, 20, true);
		bin = new Bin(660, 250, true);
		bin.setFixed(false);
		
		fan = new Fan(0, 0, false);
		fan2 = new Fan(0, 0, false);
		fan.setPixelSize(73, 100);
		fan2.setPixelSize(73, 100);
		fan.setSolid(false);
		fan2.setSolid(false);
		
		belt = new ConveyorBelt(395, 380, true);
		belt.setLocked(true);
		belt.setSpeed(Physics.SPEED_MEDIUM);
		belt.setAcceleration(Physics.ACCEL_HIGH);

		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer() {
			@Override
			public void run() {
				belt.switchDirection();
			}
		};
		timer.scheduleRepeating(2500);
		timer.run();
		
		addObject(platform);
		addObject(platform2);		
		addObject(box);
		addObject(bin);
		addObject(fan);
		addObject(fan2);
		//addObject(fan3);
		addObject(belt);
	}
}
