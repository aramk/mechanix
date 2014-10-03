package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.HorizontalPanelDropController;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class BenchHorizontalDropController extends
		HorizontalPanelDropController {

	public BenchHorizontalDropController(HorizontalPanel dropTarget) {
		super(dropTarget);
	}

	public void onDrop(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.setOnStage(false);
		o.resizeForBench();
		super.onDrop(context);
	}

	public void onEnter(DragContext context) {
		onEnterBench(context);
		super.onEnter(context);
	}

	public void onMove(DragContext context) {
		onMoveBench(context);
		super.onMove(context);
	}

	// The following are used by both this class and BenchDropController, since
	// they perform the same functions

	public void onEnterBench(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.onEnterBench();
	}

	public void onMoveBench(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.resizeForBench();
	}

}
