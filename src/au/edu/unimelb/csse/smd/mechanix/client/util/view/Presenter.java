package au.edu.unimelb.csse.smd.mechanix.client.util.view;

import java.util.ArrayList;

public class Presenter {
	private ArrayList<ViewController> viewControllers = new ArrayList<ViewController>();
	private ViewController activeController;

	public void activateController(ViewController vc) {
		if (!viewControllers.contains(vc)) {
			addController(vc);
		}
		if (activeController != null) {
			activeController.hideView();
		}
		activeController = vc;
		activeController.showView();
	}

	public ViewController getActiveController() {
		return activeController;
	}

	/*
	 * public void deactivateController() { if (activeController != null) {
	 * activeController.hideView(); activeController = null; } }
	 */

	public void addController(ViewController v) {
		viewControllers.add(v);
	}

	public void removeController(ViewController v) {
		viewControllers.remove(v);
	}
}
