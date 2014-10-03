package au.edu.unimelb.csse.smd.mechanix.client.util.text;

import java.util.HashMap;

import com.google.gwt.user.client.ui.Image;

/**
 * Maps Characters to Images.
 */
public abstract class ImageTextFont {
	private HashMap<Character, Image> charSet = new HashMap<Character, Image>();

	public void addChar(Character character, Image img) {
		charSet.put(character, img);
	}

	public void removeChar(Character character) {
		charSet.remove(character);
	}

	public Image getChar(Character character) {
		// Clone a copy of the image to allow duplication
		Image img = charSet.get(character);
		if (img != null) {
			return new Image(img.getUrl());
		} else {
			return null;
		}
	}

	public HashMap<Character, Image> getCharSet() {
		return charSet;
	}

	public void setCharSet(HashMap<Character, Image> charSet) {
		this.charSet = charSet;
	}
}
