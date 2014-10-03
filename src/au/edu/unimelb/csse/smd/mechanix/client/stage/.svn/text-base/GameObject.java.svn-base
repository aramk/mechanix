package au.edu.unimelb.csse.smd.mechanix.client.stage;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;
import au.edu.unimelb.csse.smd.mechanix.client.view.GameViewUI;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

public class GameObject extends Image implements Cloneable {
	/*
	 * An issue with GWT causes decimal changes in x and y to be ignored at
	 * times, so we will keep our own record of incremental changes and apply
	 * them.
	 */
	private double frictionX = Physics.ACCEL_F;
	private double velocityX = 0; // Try 0.2 for testing friction
	private double velocityY = 0;
	private double xRemainder = 0;
	private double yRemainder = 0;
	private double startX = 0;
	private double startY = 0;
	// Set when the object is created
	private int defaultHeight = 0;
	private int defaultWidth = 0;
	// Whether the object can be moved
	private boolean isLocked = false;
	// Whether the object is on the stage as opposed to the bench
	private boolean isOnStage = false;
	/*
	 * Whether the object is physically fixed on the stage during simulation,
	 * but can be moved when editing.
	 */
	private boolean isFixed = false;
	// Whether other objects can not pass through it
	private boolean isSolid = true;
	// Whether an object can be moved off the screen
	private boolean isContrained = true;
	// The last DropController this object was in
	private DropController lastDrop;
	// The GameViewUI object this GameObject is attached to
	private GameViewUI gameView;// XXX = new GameViewUIDefaultImpl();

	// Time in seconds of object falling, only used when object not fixed
	// protected double freeFallTime = 0;

	public GameObject(ImageResource img, double startX, double startY) {
		this(img, startX, startY, false, true);
	}

	public GameObject(ImageResource img, double startX, double startY,
			boolean isOnStage) {
		this(img, startX, startY, isOnStage, true);
	}

	public GameObject(ImageResource img, double startX, double startY,
			boolean isOnStage, boolean isVisible) {
		this(new Image(img), startX, startY, isOnStage, isVisible);
	}

	// Base Constructor
	public GameObject(Image img, double startX, double startY,
			boolean isOnStage, boolean isVisible) {
		setUrl(img.getUrl());
		setStartX(startX);
		setStartY(startY);
		setVisible(isVisible);
		setOnStage(isOnStage);
		saveDefaultDimensions(img);
	}

	// Copy Constructor
	public GameObject(GameObject gameObject) {
		this(new Image(gameObject.getUrl()), gameObject.getStartX(), gameObject
				.getStartY(), gameObject.isOnStage(), gameObject.isVisible());
		setLocked(gameObject.isLocked());
		setSolid(gameObject.isSolid());
		setFixed(gameObject.isFixed());
		setContrained(gameObject.isContrained());
		setDefaultWidth(gameObject.getDefaultWidth());
		setDefaultHeight(gameObject.getDefaultHeight());
		setGameView(gameObject.gameView);
		setStyleName(gameObject.getStyleName());
	}

	// Checks

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public boolean isFixed() {
		return isFixed;
	}

	public void setFixed(boolean isFixed) {
		this.isFixed = isFixed;
	}

	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public void setContrained(boolean isContrained) {
		this.isContrained = isContrained;
	}

	public boolean isContrained() {
		return isContrained;
	}

	// Boundaries

	// CONDITIONAL ON A POSITION

	// These are functions that check whether a given x or y position indicate
	// the object will be outside the boundary of the parent panel

	private boolean isBoundaryLeftViolated(double x) {
		return isContrained() && x < Physics.LEFT_BOUNDARY;
	}

	private boolean isBoundaryRightViolated(double x) {
		return isContrained() && x + getWidth() > getParentWidth();
	}

	private boolean isBoundaryTopViolated(double y) {
		return isContrained() && y < Physics.TOP_BOUNDARY;
	}

	private boolean isBoundaryBottomViolated(double y) {
		return isContrained() && y + getHeight() > getParentHeight();
	}

	// These functions check if a boundary will be violated given a certain x or
	// y and set the position so that it is not violated

	private boolean fixBoundaryLeft(double x) {
		if (isBoundaryLeftViolated(x)) {
			// System.out.println("left violated");
			velocityX = 0;
			setXForce(Physics.LEFT_BOUNDARY);
			return true;
		}
		return false;
	}

	private boolean fixBoundaryRight(double x) {
		if (isBoundaryRightViolated(x)) {
			// System.out.println("right violated");
			velocityX = 0;
			setXForce(getParentWidth() - getWidth());
			return true;
		}
		return false;
	}

	private boolean fixBoundaryTop(double y) {
		if (isBoundaryTopViolated(y)) {
			// System.out.println("top violated");
			velocityY = 0;
			setYForce(Physics.TOP_BOUNDARY);
			return true;
		}
		return false;
	}

	private boolean fixBoundaryBottom(double y) {
		if (isBoundaryBottomViolated(y)) {
			// System.out.println("bottom violated");
			velocityY = 0;
			setYForce(getParentHeight() - getHeight());
			return true;
		}
		return false;
	}

	// UNCONDITIONAL ON A POSITION (THE CURRENT STATE)

	// These are counterparts of the conditional functions, but they are used
	// when no movement has taken place.

	public boolean isBoundaryLeftViolated() {
		return isBoundaryLeftViolated(getX());
	}

	public boolean isBoundaryRightViolated() {
		return isBoundaryRightViolated(getX());
	}

	public boolean isBoundaryTopViolated() {
		return isBoundaryTopViolated(getY());
	}

	public boolean isBoundaryBottomViolated() {
		return isBoundaryBottomViolated(getY());
	}

	public boolean fixBoundaryLeft() {
		return fixBoundaryLeft(getX());
	}

	public boolean fixBoundaryRight() {
		return fixBoundaryRight(getX());
	}

	public boolean fixBoundaryTop() {
		return fixBoundaryTop(getY());
	}

	public boolean fixBoundaryBottom() {
		return fixBoundaryBottom(getY());
	}

	// End Boundaries

	public boolean hasParent() {
		return getParent() != null;
	}

	// End Checks

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getStartY() {
		return startY;
	}

	public double getX() {
		return getElement().getOffsetLeft();
	}

	public double getY() {
		return getElement().getOffsetTop();
	}

	/**
	 * Sets the X coordinate. Uses a remainder to ensure that decimal changes
	 * can take place and are not ignored at times.
	 */
	public boolean setX(double x) {
		// If no boundaries are violated, then we can move, otherwise these
		// functions will place the object on the boundary.
		if (x != Physics.NO_MOVE && !fixBoundaryLeft(x) && !fixBoundaryRight(x)) {
			setXForce(x);
			return true;
		}
		return false;
	}

	/**
	 * Sets the Y coordinate. Uses a remainder to ensure that decimal changes
	 * can take place and are not ignored at times.
	 */
	public boolean setY(double y) {
		// If no boundaries are violated, then we can move, otherwise these
		// functions will place the object on the boundary.
		if (y != Physics.NO_MOVE && !fixBoundaryTop(y) && !fixBoundaryBottom(y)) {
			setYForce(y);
			return true;
		}
		return false;
	}

	/**
	 * Sets the X, no questions asked.
	 */
	private void setXForce(double x) {
		// XXX OLD WAY getElement().getStyle().setLeft(x, Style.Unit.PX);

		// Decimal remainder
		xRemainder += x - Math.floor(x);
		getElement().getStyle().setLeft(Math.floor(x), Style.Unit.PX);
		if (xRemainder >= 1) {
			// Remainder has accumulated above 1
			double remFloor = Math.floor(xRemainder);
			xRemainder -= remFloor;
			setXForce(getX() + remFloor);
		}
	}

	/**
	 * Sets the Y, no questions asked.
	 */
	private void setYForce(double y) {
		// XXX OLD WAY getElement().getStyle().setTop(y, Style.Unit.PX);

		// Decimal remainder
		yRemainder += y - Math.floor(y);
		getElement().getStyle().setTop(Math.floor(y), Style.Unit.PX);
		if (yRemainder >= 1) {
			// Remainder has accumulated above 1
			double remFloor = Math.floor(yRemainder);
			yRemainder -= remFloor;
			setYForce(getY() + remFloor);
		}
	}

	public double getRight() {
		return getX() + getWidth();
	}

	public double getBottom() {
		return getY() + getHeight();
	}

	// Aliases

	public double getTop() {
		return getY();
	}

	public double getLeft() {
		return getX();
	}

	public void setTop(double top) {
		setY(top);
	}

	public void setLeft(double left) {
		setX(left);
	}

	// End Aliases

	public void setRight(double right) {
		setX(right - getWidth());
	}

	public void setBottom(double bottom) {
		setY(bottom - getHeight());
	}

	public double getMiddleX() {
		return (getLeft() + getRight()) * 1 / 2;
	}

	public double getMiddleY() {
		return (getTop() + getBottom()) * 1 / 2;
	}

	public double getParentWidth() {
		if (hasParent()) {
			return getParent().getElement().getOffsetWidth();
		} else {
			return 0;
		}
	}

	public double getParentHeight() {
		if (hasParent()) {
			return getParent().getElement().getOffsetHeight();
		} else {
			return 0;
		}
	}

	public boolean isOnStage() {
		return isOnStage;
	}

	public void setOnStage(boolean isOnStage) {
		this.isOnStage = isOnStage;
	}

	/**
	 * Used to update itself and the objects around it.
	 */
	public void update(ArrayList<GameObject> obs, int delta) {
		if (!isFixed) {
			freeFall(delta);
			frictionX(delta);

			// Final movements after calculated velocities and effects
			moveX(obs, delta);
			moveY(obs, delta);
		}
	}

	/**
	 * Reduces velocityX over time.
	 */
	private void frictionX(int delta) {
		// Reduction in velocityX due to friction
		double frictionX = Physics.friction(delta);
		velocityX = approachZero(velocityX, frictionX);
	}

	private double approachZero(double variable, double change) {
		if (Math.abs(variable) < change) {
			variable = 0;
		} else if (variable < 0) {
			variable += change;
		} else if (variable > 0) {
			variable -= change;
		}
		return variable;
	}

	/**
	 * Falling due to gravity.
	 */
	private void freeFall(int delta) {
		velocityY += Physics.gVel(delta);
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		if (!isFixed) {
			this.velocityX = velocityX;
		}
	}

	public void addVelocityX(double velocityX) {
		setVelocityX(getVelocityX() + velocityX);
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		if (!isFixed) {
			this.velocityY = velocityY;
		}
	}

	public void addVelocityY(double velocityY) {
		setVelocityY(getVelocityY() + velocityY);
	}

	// These methods will move an object in any direction discretely, regardless
	// of velocity and delta

	// public boolean moveLeftDiscrete(double x) {
	// // if (!isContrained() || getLeft() - x >= Physics.LEFT_BOUNDARY) {
	// // setX(getX() - x);
	// // return true;
	// // } else {
	// // setX(Physics.LEFT_BOUNDARY);
	// // return false;
	// // }
	// return setX(getX() - x);
	// }

	// public boolean moveRightDiscrete(double x) {
	// if (!isContrained() || !hasParent()
	// || getRight() + x <= getParentWidth()) {
	// setX(getX() + x);
	// return true;
	// } else {
	// setX(getParentWidth() - getWidth());
	// return false;
	// }
	// }

	// public boolean moveUpDiscrete(double y) {
	// if (!isContrained() || getTop() - y >= Physics.TOP_BOUNDARY) {
	// setY(getY() - y);
	// return true;
	// } else {
	// setY(Physics.LEFT_BOUNDARY);
	// return false;
	// }
	// }
	//
	// public boolean moveDownDiscrete(double y) {
	// // System.out.println("moving down: " + getBottom() + y + " " +
	// // getParentHeight());
	// if (!isContrained() || getBottom() + y <= getParentHeight()) {
	// setY(getY() + y);
	// return true;
	// } else {
	// setY(getParentHeight() - getHeight());
	// return false;
	// }
	// }

	/*
	 * These methods will move an object based on velocity and delta - they will
	 * calculate the displacement for you, as opposed to the discrete functions.
	 */

	// public boolean moveLeft(double velocity, int delta) {
	// return moveLeftDiscrete(delta * velocity);
	// }
	//
	// public boolean moveRight(double velocity, int delta) {
	// return moveRightDiscrete(delta * velocity);
	// }

	private void moveX(ArrayList<GameObject> obs, int delta) {
		double displacement = velocityX * delta;
		if (displacement == Physics.NO_MOVE) {
			return;
		}

		Collision collision = checkCollision(obs, getX() + displacement, getY());
		if (collision != null && isSolid) { // TODO fix solid bugs
			GameObject other = collision.getOther();

			System.out.println(getClass() + " hit " + other.getClass());
			
			/*
			 * if (displacement < 0) { setLeft(other.getRight()); } else {
			 * System.out.println(getClass() + " moved other: " +
			 * other.getClass()); setRight(other.getLeft()); }
			 */

			if (getLeft() > other.getRight()) {
				setLeft(other.getRight());
			} else if (getLeft() < other.getRight()) {
				setRight(other.getLeft());
			}

			other.addVelocityX(velocityX);
			velocityX = 0;
			return;
		}
		setX(getX() + displacement);
	}

	private void moveY(ArrayList<GameObject> obs, int delta) {
		double displacement = velocityY * delta;
		if (displacement == Physics.NO_MOVE) {
			return;
		}

		Collision collision = checkCollision(obs, getX(), getY() + displacement);
		if (collision != null) {
			GameObject other = collision.getOther();

			if (displacement < 0) {
				setTop(other.getBottom());
			} else {
				setBottom(other.getTop());
			}

			other.addVelocityY(velocityY);
			velocityY = 0;
			return;
		}
		setY(getY() + displacement);

		/*
		 * Collision collision = checkCollision(obs, getX(), getY() +
		 * displacement); if (collision != null) { GameObject other =
		 * collision.getOther(); if (collision.isTop()) {
		 * //setTop(collided.getBottom()); other.addVelocityY(velocityY);
		 * velocityY = 0; } else if (collision.isBottom()) {
		 * //setBottom(collided.getTop()); other.addVelocityY(velocityY);
		 * velocityY = 0; } else { setY(getY() + displacement); }
		 */
	}

	public Collision checkCollision(ArrayList<GameObject> obs, double x,
			double y) {
		for (GameObject o : obs) {
			if (this == o || !o.isSolid || !o.isOnStage) {
				continue;
			}
			Collision collision = new Collision(this, o, x, y);
			if (collision.isCollided()) {
				return collision;
			}
		}
		return null;
	}

	/**
	 * Called by the Bench DropControllers to resize the GameObject when dropped
	 * in the bench. Can be overridden by subclasses if needed.
	 */
	public void resizeForBench() {
		if (!isOnStage) {
			// We call super to ensure the defaultHeight and width are not
			// updated
			super.setPixelSize(scaledWidth(), scaledHeight());
		}
	}

	/*
	 * Calculates the width after scaling, ensuring that the height/width
	 * proportion is kept.
	 */
	private int scaledWidth() {
		return (int) (((float) scaledHeight() / defaultHeight) * defaultWidth);
	}

	/*
	 * Calculates the height after scaling, ensuring that the height/width
	 * proportion is kept.
	 */
	private int scaledHeight() {
		if (gameView == null
				|| defaultHeight < gameView.getBenchContentHeight()) {
			return defaultHeight;
		} else {
			return gameView.getBenchContentHeight();
		}
	}

	public boolean isScaled() {
		return getWidth() == scaledWidth();
	}

	/**
	 * Called by the Bench DropControllers to resize the GameObject when dropped
	 * in the stage. Can be overridden by subclasses if needed.
	 */
	public void resizeForStage() {
		// System.out.println("setting pixel size to " + defaultWidth);
		if (isOnStage) {
			setPixelSize(defaultWidth, defaultHeight);
			// System.out.println("resized to " + defaultWidth + " " +
			// defaultHeight);
		}
	}

	/**
	 * Corrects the positioning due to resizing. At this point the image is
	 * still large from the stage.
	 */
	public void onEnterBench() {
		if (!isScaled()) {
			// In case the image is changed or the object is resized during
			// runtime, we need the latest size
			// saveDefaultDimensions();
		}
		if (getWidth() == defaultWidth) {
			setXForce(getX() + (defaultWidth - scaledWidth()) / 2);
			setYForce(getY() + (defaultHeight - scaledHeight()) / 2);
			// System.out.println(defaultWidth - scaledWidth());
		}
	}

	/**
	 * Corrects the positioning due to resizing. At this point the image is
	 * still small from the bench.
	 */
	public void onEnterStage() {
		if (getWidth() != defaultWidth) {
			setXForce(getX() + (getWidth() - defaultWidth) / 2);
			setYForce(getY() + (getHeight() - defaultHeight) / 2);
		}
	}

	// private void saveDefaultDimensions() {
	// // Can't be used on instantiation because this checks the actual height
	// // of the DOM object, which is conveniently 0 at that time
	// defaultHeight = getHeight();
	// defaultWidth = getWidth();
	//
	// System.out.println("save default to width " + defaultHeight);
	// }

	private void saveDefaultDimensions(ImageResource img) {
		// System.out.println("saving defaults resource" + " " +
		// this.getClass());
		// Used during instantiation
		defaultHeight = img.getHeight();
		defaultWidth = img.getWidth();
	}

	private void saveDefaultDimensions(Image img) {
		// System.out.println("saving defaults img " + img.getHeight() + " " +
		// this.getClass());
		// Used when image, not resource, is given
		defaultHeight = img.getHeight();
		defaultWidth = img.getWidth();
	}

	@Override
	public void setPixelSize(int width, int height) {
		// System.out.println("setting pixel default width height " + width +
		// " " + height);
		super.setPixelSize(width, height);

		// getElement().getStyle().setWidth(width, Unit.PX);
		// getElement().getStyle().setHeight(height, Unit.PX);

		// setWidth("500px");
		// System.out.println("set pixel default width height " + getWidth() +
		// " " + getHeight());
		defaultHeight = height;
		defaultWidth = width;
	}

	// @Override
	// public void setWidth(String width) {
	// // Ensure the default dimensions are updated
	// super.setWidth(width);
	// // XXX
	// saveDefaultDimensions();
	// }
	//
	// @Override
	// public void setHeight(String height) {
	// super.setHeight(height);
	// // XXX
	// saveDefaultDimensions();
	// }

	public int getDefaultHeight() {
		return defaultHeight;
	}

	public void setDefaultHeight(int defaultHeight) {
		this.defaultHeight = defaultHeight;
	}

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public void setDefaultWidth(int defaultWidth) {
		this.defaultWidth = defaultWidth;
	}

	/**
	 * Sets the last DropController successfully used.
	 * 
	 * @param lastDrop
	 */
	public void setLastDrop(DropController lastDrop) {
		this.lastDrop = lastDrop;
	}

	/**
	 * The last DropController successfully used.
	 */
	public DropController getLastDrop() {
		return lastDrop;
	}

	public void setGameView(GameViewUI gameView) {
		this.gameView = gameView;
	}

	public GameViewUI getGameView() {
		return gameView;
	}

	/**
	 * Ensures that the src of the image is changed, not the css background so
	 * that our scaling works.
	 */
	@Override
	public void setResource(ImageResource resource) {
		// System.out.println("set resource" + " " + this.getClass());
		this.setUrl(resource.getURL());
		this.setPixelSize(resource.getWidth(), resource.getHeight());
		// System.out.println("saveDefaultDimensions 2");
		saveDefaultDimensions(resource);
	}

	public void setFrictionX(double frictionX) {
		this.frictionX = frictionX;
	}

	public double getFrictionX() {
		return frictionX;
	}

	public Object clone() {
		return new GameObject(this);
	}

}
