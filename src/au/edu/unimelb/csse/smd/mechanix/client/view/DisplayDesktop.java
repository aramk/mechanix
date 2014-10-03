package au.edu.unimelb.csse.smd.mechanix.client.view;

public class DisplayDesktop implements Display {

	@Override
	public int width() {
		return 800;
	}

	@Override
	public int height() {
		return 600;
	}

	@Override
	public int left() {
		return 0;
	}

	@Override
	public int top() {
		return 10;
	}

}
