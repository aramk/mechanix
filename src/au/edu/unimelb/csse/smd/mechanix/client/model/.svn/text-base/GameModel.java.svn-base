package au.edu.unimelb.csse.smd.mechanix.client.model;

import au.edu.unimelb.csse.smd.mechanix.client.level.*;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * This class implements the game logic and in particular the management of the
 * game state.
 */
public class GameModel extends Observable {
	public static final String DEFAULT_NAME = "Player";

	// Storage of levels
	private ArrayList<Level> levels = new ArrayList<Level>();

	// Variables holding the game state
	private String nickname = DEFAULT_NAME;
	private int currentLevel = 0;
	private int highestLevel = 0;
	// XXX Changed
	private Vector<Integer> levelScores = new Vector<Integer>();
	private int highscore = 0;

	// XXX Changed
	private Vector<Boolean> badges = new Vector<Boolean>();
	// badges
	// "rescueSnow" and
	// "rescueDwarves"

	// Variables marking which part of the game state has changed since it was
	// persisted last time
	private boolean dirtyCurrentLevel;
	private boolean dirtyHighestLevel;
	private boolean dirtyHighscore;
	private boolean dirtyLevelScores;
	// XXX Changed
	//private boolean dirtyBadges = true;
	//private boolean dirtySecondBadgePoints = true;

	// XXX Changed
	public GameModel() {
		// levels.add(new LevelTest());
		levels.add(new LevelOne());
		levels.add(new LevelTwo());
		levels.add(new LevelThree());
		levels.add(new LevelFour());

		// Initialize variables
		fixLevelScores();
		badges.addAll(Arrays.asList(false, false));
	}

	private void fixLevelScores() {
		// System.out.println(levels.size() + " " + levelScores.size());
		if (levels.size() > levelScores.size()) {
			for (int i = levelScores.size(); i < levels.size(); i++) {
				levelScores.add(0);
			}
		}
		// System.out.println(levels.size() + " " + levelScores.size());
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}

	public Level getLevel(int index) {
		if (index >= 0 && index < levels.size()) {
			return levels.get(index);
		} else {
			return null;
		}
	}

	public Level getCurrentLevelObject() {
		return levels.get(currentLevel);
	}

	// //////////////////////////////////////////////
	// //////////////// GAME LOGIC //////////////////
	// //////////////////////////////////////////////

	// The following methods change the game state and after doing that,
	// call notifyObservers() to inform all observing objects of this change.

	/**
	 * This method is called when the user progresses to a new level, i.e. when
	 * the user unlocks this level.
	 */
	public boolean progressLevel() {
		if (currentLevel + 1 < getNumLevels()) {
			currentLevel++;
			dirtyCurrentLevel = true;

			if (currentLevel >= highestLevel) {
				// XXX Changed
				// System.out.println("current level is higher " + currentLevel
				// + " >= " + highestLevel);
				highestLevel++;
				dirtyHighestLevel = true;
			}
			// Inform all observers of this object that its state has changed.
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	/**
	 * This method is called when the user switches to the next level, which
	 * must have been unlocked previously already.
	 */
	public void incrementLevel() {
		if (currentLevel < highestLevel) {
			currentLevel++;
			dirtyCurrentLevel = true;
		}

		// Inform all observers of this object that its state has changed.
		setChanged();
		notifyObservers();
	}

	/**
	 * This method is called when the user switches to an earlier level.
	 */
	public void decrementLevel() {
		if (currentLevel > 0) {
			currentLevel--;
			dirtyCurrentLevel = true;
		}

		// Inform all observers of this object that its state has changed.
		setChanged();
		notifyObservers();
	}

	/**
	 * This method is called when the user earns a point in the current level.
	 */
	public void earnPoint() {
		// XXX Changed
		levelScores.set(currentLevel, levelScores.get(currentLevel) + 1);
		dirtyLevelScores = true;
		dirtyHighscore = true;

		// Inform all observers of this object that its state has changed.
		setChanged();
		notifyObservers();
	}

	/**
	 * After the game state has been made persistent, this method is called to
	 * indicated that no part of the game state is "dirty" anymore.
	 */
	public void clean() {
		dirtyCurrentLevel = false;
		dirtyHighestLevel = false;
		dirtyHighscore = false;
		dirtyLevelScores = false;
		// XXX Changed
		//dirtyBadges = false;
		//dirtySecondBadgePoints = false;
	}

	// //////////////////////////////////////////////
	// ////////////// SETTER METHODS ////////////////
	// //////////////////////////////////////////////

	public void setNickname(String nickname) {
		this.nickname = nickname;
		setChanged();
		notifyObservers();
	}

	public void setBadge(int i, boolean achieved) {

		if (getScore() == 30) {
			setBadge(3, true);
		} else if (getScore() == 20) {
		    setBadge(2, true);
		} else {
			 setBadge(1, true);
		}

		/*
		 * for (Level l : levels) { setBadge(1, true); }
		 */

		// badges.set(i, achieved);
		// dirtyBadges = true;

		// badges.set(i, achieved);
		// dirtyBadges = true;

		setChanged();
		notifyObservers();
	}

	public void setCurrentLevel(int level) {
		if (level >= 0 && level < levels.size()) {
			currentLevel = level;
			dirtyCurrentLevel = true;
			setChanged();
			notifyObservers();
		}
	}

	public void setHighestLevel(int level) {
		highestLevel = level;
		dirtyHighestLevel = true;
		setChanged();
		notifyObservers();
	}

	public void setHighscore(int score) {
		highscore = score;
		dirtyHighscore = true;
		setChanged();
		notifyObservers();
	}

	public void setLevelScores(Vector<Integer> scores) {
		levelScores = scores;
		fixLevelScores();
		dirtyLevelScores = true;
		setChanged();
		notifyObservers();
	}

	public void setLevelScore(int level, int score) {
		levelScores.set(level, score);
		dirtyLevelScores = true;
		if (getHighscore() > highscore) {
			dirtyHighscore = true;
		}
		setChanged();
		notifyObservers();
	}

	public void setScore(int score) {
		setLevelScore(currentLevel, score);
	}

	// //////////////////////////////////////////////
	// ////////////// GETTER METHODS ////////////////
	// //////////////////////////////////////////////

	public int getHighscore() {
		int sum = 0;
		for (int s : levelScores)
			sum += s;

		if (sum > highscore)
			return sum;
		else
			return highscore;
	}

	public String getNickname() {
		return nickname;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public int getHighestLevel() {
		return highestLevel;
	}

	public int getNumLevels() {
		return levels.size();
	}

	public Vector<Integer> getLevelScores() {
		return levelScores;
	}

	public boolean isDirtyCurrentLevel() {
		return dirtyCurrentLevel;
	}

	public boolean isDirtyHighestLevel() {
		return dirtyHighestLevel;
	}

	public boolean isDirtyHighscore() {
		return dirtyHighscore;
	}

	public boolean isDirtyLevelScores() {
		return dirtyLevelScores;
	}

	/*public boolean isDirtyBadges() {
		return dirtyBadges;
	}

	public boolean isDirtySecondBadgePoints() {
		return dirtySecondBadgePoints;
	}*/

	public Boolean getBadge(int badge) {
		return badges.get(badge);
	}

	public int getScore() {
		if (currentLevel >= 0 && currentLevel < levelScores.size()) {
			return levelScores.get(currentLevel);
		} else {
			return 0;
		}
	}

}
