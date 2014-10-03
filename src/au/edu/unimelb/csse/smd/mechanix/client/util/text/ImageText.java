package au.edu.unimelb.csse.smd.mechanix.client.util.text;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;

import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Creates a set of Images from a text String and a given font.
 */
public abstract class ImageText extends FlowPanel {
	private String text = "";
	private ArrayList<Character> chars = new ArrayList<Character>();
	// Images loaded from the buffer into the FlowPanel
	private ArrayList<Image> loaded = new ArrayList<Image>();
	// Manually added images
	private ArrayList<Image> manual = new ArrayList<Image>();
	private ImageTextFont font;
	private float size = 1;

	// This is used to firstly create the images, then put them into the
	// ImageText FlowPanel
	private AbsolutePanel pBuffer = new AbsolutePanel();

	public ImageText(String text, ImageTextFont font) {
		setText(text);
		setFont(font);
		addStyleName("gwt-ImageText");
		RootPanel.get().add(pBuffer, 0, 0);
		pBuffer.setVisible(false);
	}

	public ImageText(String text, ImageTextFont font, float size) {
		this(text, font);
		setSize(size);
	}

	public void setText(String text) {
		//System.out.println("setting text to " + text);
		if (!this.text.equals(text)) {
			this.text = text;
			chars.clear();
			chars.ensureCapacity(text.length());
			for (int i = 0; i < text.length(); i++) {
				chars.add(text.charAt(i));
			}
			generate();
		}
	}

	public String getText() {
		return text;
	}

	public ImageTextFont getFont() {
		return font;
	}

	public void setFont(ImageTextFont font) {
		this.font = font;
		generate();
	}

	/**
	 * The main method for redrawing text.
	 */
	public void generate() {
		if (font == null || text.length() == 0 || chars.size() == 0) {
			return;
		}

		// This is the number of images we need to add to our buffer
		int addAmount = chars.size() + manual.size() - getChildren().size()
				- pBuffer.getWidgetCount();

		if (addAmount > 0) {
			loadBuffer(addAmount);
		} else {
			updateText();
		}

	}

	/**
	 * Loads a certain number of into the buffer, then assigns each an onLoad
	 * handler to ensure they are added to the main FlowPanel once fully loaded.
	 * This avoids flickering.
	 * 
	 * @param addAmount
	 *            Number of blank Images to add
	 */
	private void loadBuffer(int addAmount) {
		if (font == null || text.length() == 0 || chars.size() == 0
				|| addAmount <= 0) {
			return;
		}

		for (int i = 0; i < addAmount; i++) {
			DelayedLoadImage img = new DelayedLoadImage(
					Resources.image.getBlank());
			pBuffer.add(img);
			HandlerRegistration handler = img.addLoadHandler(new LoadHandler() {
				@Override
				public void onLoad(LoadEvent event) {
					DelayedLoadImage source = (DelayedLoadImage) event
							.getSource();
					source.getHandler().removeHandler();
					add(source);
					loaded.add(source);
					if (isBufferEmpty()) {
						updateText();
					}
				}
			});
			img.setHandler(handler);
		}
	}

	/**
	 * Loads the correct character images based on text, maintains the existing
	 * index of manually added images and also hides unused images - but doesn't
	 * remove them to improve efficiency.
	 */
	private void updateText() {
		if (!isBufferEmpty()) {
			return;
		}

		int currChar = 0;
		for (int i = 0; i < getChildren().size(); i++) {
			Image existingImage = (Image) getChildren().get(i);
			if (manual.contains(existingImage)) {
				continue;
			} else if (currChar > chars.size() - 1) {
				existingImage.setVisible(false);
				continue;
			}

			Character c = chars.get(currChar);
			Image replaceImage = font.getChar(c);

			if (replaceImage != null) {
				if (!existingImage.getUrl().equals(replaceImage.getUrl())) {
					existingImage.setUrl(replaceImage.getUrl());

					// We don't need this handler to be removed, since it will
					// be used each time
					// the image is updated to change the scaling if needed

					/* HandlerRegistration handler = */existingImage
							.addLoadHandler(new LoadHandler() {
								@Override
								public void onLoad(LoadEvent event) {
									// System.out.println("loaded again");
									Image img = (Image) event.getSource();
									// scale(img);
								}
							});
				}
				existingImage.setVisible(true);
				currChar++;
			}
		}
	}

	private boolean isBufferEmpty() {
		return pBuffer.getWidgetCount() == 0;
	}

	public void insert(Image image, int index) {
		super.insert(image, index);
		manual.add(image);
	}

	private void scale(Image img) {
		int width = (int) (img.getWidth() * size);
		int height = (int) (img.getHeight() * size);
		System.out.println("width " + width);
		img.setPixelSize(width, height);
	}

	public void setSize(float size) {
		if (size > 1) {
			size = 1;
		} else if (size < 0) {
			size = 0;
		}
		this.size = size;
	}

	public float getSize() {
		return size;
	}
}
