package au.edu.unimelb.csse.smd.mechanix.client.view;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

public class SwitchImageButton extends ImageButton {

	private boolean switched = false;
	private Image switchUp;
	private Image switchHover;

	public SwitchImageButton(ImageResource upResource,
			ImageResource switchUpResource) {
		super(upResource);
		switchUp = new Image(switchUpResource);
	}

	public SwitchImageButton(ImageResource upResource,
			ImageResource hoverResource, ImageResource switchUpResource,
			ImageResource switchHoverResource) {
		super(upResource, hoverResource);
		switchUp = new Image(switchUpResource);
		switchHover = new Image(switchHoverResource);
	}

	public Image getSwitchUpImage() {
		return switchUp;
	}

	public Image getSwitchHoverImage() {
		return switchHover;
	}

	public void switchImage() {
		switchImageMain(switched);
	}

	public void switchImage(boolean switched) {
		switchImageMain(switched);
	}

	public void switchImageMain(boolean switched) {
		if (switched) {
			this.getUpFace().setImage(getUpImage());
			if (getHoverImage() != null) {
				this.getUpHoveringFace().setImage(getHoverImage());
			}
		} else {
			this.getUpFace().setImage(getSwitchUpImage());
			if (getSwitchHoverImage() != null) {
				this.getUpHoveringFace().setImage(getSwitchHoverImage());
			}
		}
	}

}
