package au.edu.unimelb.csse.smd.mechanix.client.stage;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.resources.client.ImageResource;

public abstract class SwitchMachine extends DynamicMachine {

	private int direction = Physics.DIR_LEFT;
	private ImageResource leftImage;
	private ImageResource rightImage;

	public SwitchMachine(ImageResource leftImage, ImageResource rightImage,
			int startX, int startY, boolean isOnStage) {
		super(leftImage, startX, startY, isOnStage);
		setLeftImage(leftImage);
		setRightImage(rightImage);
		addDoubleClick();
	}
	
	/*public SwitchMachine(SwitchMachine switchMachine) {
		super(switchMachine);
		setDirection(switchMachine.getDirection());
		setLeftImage(switchMachine.getLeftImage());
		setRightImage(switchMachine.getRightImage());
		addDoubleClick();
	}*/
	
	private void addDoubleClick() {
		this.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				switchDirection();
			}
		});
	}

	private void setDirectionImage() {
		int defaultWidth = getDefaultWidth();
		int defaultHeight = getDefaultHeight();
		if (direction == Physics.DIR_RIGHT && rightImage != null) {
			setResource(rightImage);
		} else if (direction == Physics.DIR_LEFT && leftImage != null) {
			setResource(leftImage);
		}
		setPixelSize(defaultWidth, defaultHeight);
	}

	public void switchDirection() {
		if (direction == Physics.DIR_LEFT) {
			direction = Physics.DIR_RIGHT;
		} else {
			direction = Physics.DIR_LEFT;
		}
		setDirectionImage();
		resizeForBench();
		resizeForStage();
	}

	public void setDirection(int direction) {
		if (direction == Physics.NO_DIRECTION) {
			direction = Physics.DIR_LEFT;
		}
		this.direction = direction;
		setDirectionImage();
	}

	public int getDirection() {
		return direction;
	}

	public void setLeftImage(ImageResource leftImage) {
		this.leftImage = leftImage;
	}

	public ImageResource getLeftImage() {
		return leftImage;
	}

	public void setRightImage(ImageResource rightImage) {
		this.rightImage = rightImage;
	}

	public ImageResource getRightImage() {
		return rightImage;
	}

}
