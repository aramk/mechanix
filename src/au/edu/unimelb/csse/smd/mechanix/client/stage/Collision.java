package au.edu.unimelb.csse.smd.mechanix.client.stage;

public class Collision {
	private GameObject self, other;
	double x = 0, y = 0;
	private boolean alignedLeft = false;
	private boolean alignedRight = false;
	private boolean alignedTop = false;
	private boolean alignedBottom = false;

	/*public Collision(GameObject object, boolean alignedLeft,
			boolean alignedRight, boolean alignedTop, boolean alignedBottom) {
		this.object = object;
		this.alignedLeft = alignedLeft;
		this.alignedRight = alignedRight;
		this.alignedTop = alignedTop;
		this.alignedBottom = alignedBottom;
	}*/
	
	public Collision(GameObject self, GameObject other, double x, double y) {
		this.self = self;
		this.other = other;
		this.x = x;
		this.y = y;
		/*alignedLeft = (self.getLeft() >= other.getLeft() && self.getLeft() <= other.getRight());
				//|| (other.getLeft() >= self.getLeft() && other.getLeft() <= self.getRight());

		alignedRight = (self.getRight() >= other.getLeft() && self.getRight() <= other.getRight());
				//|| (other.getRight() >= self.getLeft() && other.getRight() <= self.getRight());

		alignedTop = (self.getTop() >= other.getTop() && self.getTop() <= other.getBottom());
				//|| (other.getTop() >= self.getTop() && other.getTop() <= self.getBottom());

		alignedBottom = (self.getBottom() >= other.getTop() && self.getBottom() <= other.getBottom());
				//|| (other.getBottom() >= self.getTop() && self.getBottom() <= other.getBottom());*/
		
	}

	public GameObject getSelf() {
		return self;
	}
	
	public GameObject getOther() {
		return other;
	}
	
	public boolean isOnBottomBound() {
		return self.getBottom() == other.getTop();
	}
	
	public boolean isOnTopBound() {
		return other.getBottom() == self.getTop();
	}
	
	public boolean isLeft() {
		return (alignedTop || alignedBottom) && alignedLeft;
	}
	
	public boolean isRight() {
		return (alignedTop || alignedBottom) && alignedRight;
	}
	
	public boolean isTop() {
		return (alignedLeft || alignedRight) && alignedTop;
	}
	
	public boolean isBottom() {
		return (alignedLeft || alignedRight) && alignedBottom;
	}

	public boolean isAlignedLeft() {
		return alignedLeft;
	}

	public void setAlignedLeft(boolean alignedLeft) {
		this.alignedLeft = alignedLeft;
	}

	public boolean isAlignedRight() {
		return alignedRight;
	}

	public void setAlignedRight(boolean alignedRight) {
		this.alignedRight = alignedRight;
	}

	public boolean isAlignedTop() {
		return alignedTop;
	}

	public void setAlignedTop(boolean alignedTop) {
		this.alignedTop = alignedTop;
	}

	public boolean isAlignedBottom() {
		return alignedBottom;
	}

	public void setAlignedBottom(boolean alignedBottom) {
		this.alignedBottom = alignedBottom;
	}

	public boolean isCollided() {
		double left = x, right = x + self.getWidth(), top = y, bottom = y + self.getHeight();
		return left < other.getRight() &&
				right > other.getLeft() &&
				bottom > other.getTop() &&
				top < other.getBottom();
		//return isLeft() || isRight() || isTop() || isBottom();
		/*return self.getLeft() < other.getRight() &&
				self.getRight() > other.getLeft() &&
				self.getBottom() > other.getTop() &&
				self.getTop() < other.getBottom();*/
	}
}
