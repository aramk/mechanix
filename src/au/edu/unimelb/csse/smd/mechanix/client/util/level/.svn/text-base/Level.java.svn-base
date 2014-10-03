package au.edu.unimelb.csse.smd.mechanix.client.util.level;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;

public abstract class Level {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private String levelInfo = "";

	public void load() {
		objects.clear();
		addObjectsToLevel();
	}
	
	public abstract void addObjectsToLevel();
	
	public void addObject(GameObject s) {
		if (s != null) {
			objects.add(s);
		}
	}

	public void removeObject(GameObject s) {
		if (s != null) {
			objects.remove(s);
		}
	}

	public ArrayList<GameObject> getObjects() {
		// Should not alter the objects in a level outside this package
		return objects;
	}
	
	public ArrayList<GameObject> getObjectsCopy() {
		// Use this to get a copy of the objects in the level
		ArrayList<GameObject> copy = new ArrayList<GameObject>();
		for (GameObject o : objects) {
			GameObject clone = (GameObject) o.clone();
			copy.add(clone);
		}
		return copy;
	}

	/**
	 * Determines if the level has been completed.
	 * 
	 * @return true if the level has been completed, else false.
	 */
	public abstract boolean check();

	public void setLevelInfo(String levelInfo) {
		this.levelInfo = levelInfo;
	}

	public String getLevelInfo() {
		return levelInfo;
	}

}
