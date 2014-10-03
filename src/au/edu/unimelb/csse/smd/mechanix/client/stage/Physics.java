package au.edu.unimelb.csse.smd.mechanix.client.stage;

public class Physics {
	// Direction.
	// XXX why boolean?
	public static final boolean CLOCKWISE = true;
	public static final boolean ANTICLOCKWISE = false;
	public static final boolean UP = true;
	public static final boolean DOWN = false;
	public static final boolean LEFT = false;
	public static final boolean RIGHT = true;

	// Constant
	// TODO 9.8?
	public static final double GRAVITY = 0.98;

	// Maximum falling speed.
	public static final double MAXFALLING = 20;

	// Time of a single draw interval in msec
	public static final int DELTA = 40;
	// Delta in sec
	// public static final double DELTA_S = (double) DELTA * 1000;

	// Direction and Movement
	public static final double NO_MOVE = 0;
	public static final int NO_DIRECTION = 0;
	public static final int DIR_LEFT = -1;
	public static final int DIR_RIGHT = 1;

	// Boundaries
	public static final int LEFT_BOUNDARY = 0;
	public static final int TOP_BOUNDARY = 0;

	// Used to scale the numbers from the real world to pixel land and also to
	// scale them in general to control overall speeds
	private static final double MULTIPLIER = 0.00005;

	// Acceleration due to gravity
	public static final double ACCEL_G = multiScale(9.8);
	// Default friction in X axis
	public static final double ACCEL_F = multiScale(6);

	// Different accelerations
	public static final double ACCEL_LOW = multiScale(8);
	public static final double ACCEL_MEDIUM = multiScale(10);
	public static final double ACCEL_HIGH = multiScale(15);
	public static final double ACCEL_HIGHER = multiScale(20);

	// Different constant speeds (velocity taken at single delta)
	public static final double SPEED_LOW = vLow(2 * DELTA);
	public static final double SPEED_MEDIUM = vMedium(2 * DELTA);
	public static final double SPEED_HIGH = vHigh(DELTA);
	public static final double SPEED_HIGHER = vHigher(DELTA);

	// Velocity due to friction
	public static double friction(int msec) {
		return vel(ACCEL_F, msec);
	}

	// // Velocity due to friction
	// public static double friction(double sec) {
	// return vel(F_ACCEL, sec);
	// }

	// // Velocity due to gravity
	// public static double gVel(double sec) {
	// return vel(G_ACCEL, sec);
	// }

	// Velocity due to gravity
	public static double gVel(int msec) {
		return vel(ACCEL_G, msec);
	}

	// Displacement due to gravity
	public static double gDisp(int msec) {
		return ACCEL_G * msec * msec;
	}

	// Speeds

	public static double vLow(int msec) {
		return vel(ACCEL_LOW, msec);
	}

	public static double vMedium(int msec) {
		return vel(ACCEL_MEDIUM, msec);
	}

	public static double vHigh(int msec) {
		return vel(ACCEL_HIGH, msec);
	}

	public static double vHigher(int msec) {
		return vel(ACCEL_HIGHER, msec);
	}

	// Utility functions

	public static double vel(double accel, int msec) {
		return accel * msec;
	}

	// public static double vel(double accel, double sec) {
	// return accel * sec;
	// }

	// public static double milliSecToSec(double sec) {
	// return sec / 1000f;
	// }

	/**
	 * Scales values used throughout for adjustment
	 */
	private static double multiScale(double value) {
		return value * MULTIPLIER;
	}
}