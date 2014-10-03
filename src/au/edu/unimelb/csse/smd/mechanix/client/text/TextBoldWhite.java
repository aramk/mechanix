package au.edu.unimelb.csse.smd.mechanix.client.text;

import au.edu.unimelb.csse.smd.mechanix.client.util.text.ImageText;

public class TextBoldWhite extends ImageText {

	public TextBoldWhite(String text) {
		super(text.toUpperCase(), FontCollection.boldWhite());
	}

}
