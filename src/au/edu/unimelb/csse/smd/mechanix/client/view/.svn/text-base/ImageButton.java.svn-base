package au.edu.unimelb.csse.smd.mechanix.client.view;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class ImageButton extends PushButton {

	private Image up;
	private Image hover;

	public ImageButton(ImageResource upResource) {
		up = new Image(upResource);
		this.getUpFace().setImage(up);
	}

	public ImageButton(ImageResource upResource, ImageResource hoverResource) {
		this(upResource);
		hover = new Image(hoverResource);
		this.getUpHoveringFace().setImage(hover);
	}

	public Image getUpImage() {
		return up;
	}

	public Image getHoverImage() {
		return hover;
	}

}
