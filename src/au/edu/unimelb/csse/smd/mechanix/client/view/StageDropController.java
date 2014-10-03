package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbsolutePositionDropController;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class StageDropController extends AbsolutePositionDropController {

	public StageDropController(AbsolutePanel stage) {
		super(stage);
	}

	@Override
	public void onDrop(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.setOnStage(true);
		o.resizeForStage();
		super.onDrop(context);
	}

	@Override
	public void onEnter(DragContext context) {
		((GameObject) context.draggable).onEnterStage();
		super.onEnter(context);
	}

	public void onMove(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.resizeForStage();
		o.setOnStage(true);
		super.onMove(context);
	}
}
