package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractDropController;

public class BenchDropController extends AbstractDropController {

	GameView gameView;
	BenchHorizontalDropController dropControllerBenchContent;

	public BenchDropController(GameView gameView,
			BenchHorizontalDropController dropControllerBenchContent) {
		super(gameView.getBench());
		this.gameView = gameView;
		// The DragTarget of this is within the BenchDropController DragTarget
		this.dropControllerBenchContent = dropControllerBenchContent;
	}

	@Override
	public void onDrop(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.setOnStage(false);
		gameView.putOnBench(o);
		o.resizeForBench();
		super.onDrop(context);
	}

	@Override
	public void onEnter(DragContext context) {
		dropControllerBenchContent.onEnterBench(context);
		super.onEnter(context);
	}

	public void onMove(DragContext context) {
		GameObject o = (GameObject) context.draggable;
		o.setOnStage(false);
		dropControllerBenchContent.onMoveBench(context);
		super.onMove(context);
	}
}
