package au.edu.unimelb.csse.smd.mechanix.client.util.view;

public class ViewController {
	private Presenter presenter;
	private View view;

	public ViewController() {
		this(0, 0, 0, 0);
	}

	public ViewController(int x, int y, int width, int height) {
		view = new View(x, y, width, height);
	}

	public ViewController(View view) {
		this.view = view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}

	/*
	 * TODO so that we are sure a view always exists? public void removeView() {
	 * hideView(); view = null; }
	 */

	public boolean isViewVisible() {
		return (view != null) ? view.isVisible() : false;
	}

	public void showView() {
		if (view != null) {
			view.show();
		}
	}

	public void hideView() {
		if (view != null) {
			view.hide();
		}
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	public Presenter getPresenter() {
		return presenter;
	}
}
