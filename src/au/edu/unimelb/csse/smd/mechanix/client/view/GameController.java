package au.edu.unimelb.csse.smd.mechanix.client.view;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.stage.Physics;
import au.edu.unimelb.csse.smd.mechanix.client.util.level.Level;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observable;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observer;
import au.edu.unimelb.csse.smd.mechanix.client.util.view.ViewController;

import com.allen_sauer.gwt.dnd.client.DragEndEvent;
import com.allen_sauer.gwt.dnd.client.DragHandler;
import com.allen_sauer.gwt.dnd.client.DragStartEvent;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;

public class GameController extends ViewController implements Observer,
		ClickHandler, DragHandler {
	private GameModel game;
	private boolean isEditing = true;
	// This instance ensures GameViewUI is implemented
	private GameViewUI gameView;
	private PickupDragController dragController;
	private StageDropController dropControllerStage;
	private BenchDropController dropControllerBench;
	private BenchHorizontalDropController dropControllerBenchContent;
	private Timer timerObj;
	private int timer;

	public GameController(GamePresenter presenter, GameModel game) {
		gameView = new GameView(game);
		setView((GameView) gameView);
		setPresenter(presenter);
		this.game = game;
		this.game.addObserver(this);

		// Configure dragging
		dragController = new PickupDragController(getView().getPanel(), true);
		dragController.setBehaviorConstrainedToBoundaryPanel(true);
		dragController.setBehaviorBoundaryPanelDrop(false);
		dragController.setBehaviorDragStartSensitivity(3);
		dragController.addDragHandler(this);

		// Configure dropping
		dropControllerStage = new StageDropController(gameView.getStage());
		dropControllerBenchContent = new BenchHorizontalDropController(
				gameView.getBenchContent());

		/*
		 * When an object is dropped on the the outer panel containing the
		 * HorizonalPanel, the object is added to the
		 * BenchHorizontalDropController as usual, so they share methods for
		 * resizing an object as it enters the bench.
		 */
		dropControllerBench = new BenchDropController((GameView) gameView,
				dropControllerBenchContent);
		dragController.registerDropController(dropControllerStage);
		dragController.registerDropController(dropControllerBench);
		dragController.registerDropController(dropControllerBenchContent);

		// Bind UI
		gameView.getButtonMenu().addClickHandler(this);
		gameView.getButtonRestartLevel().addClickHandler(this);
		gameView.getButtonGo().addClickHandler(this);
		gameView.getLevelWin().addClickHandler(this);
		gameView.getGameOver().addClickHandler(this);
	}

	public void loadCurrentLevel() {
		int level = game.getCurrentLevel();
		
		System.out.println("current level: " + game.getCurrentLevel() + "/" + game.getNumLevels());
		// Clear any objects already loaded
		gameView.clearAllObjects();
		stopSimulation();
		
		Level currLevel = game.getLevel(level);
		if (currLevel != null && level >= 0 && level < game.getNumLevels()) {
			currLevel.load();
			ArrayList<GameObject> objects = currLevel.getObjects/*Copy*/();
			for (GameObject o : objects) {
				if (!o.isLocked()) {
					// Bind objects for dragging
					dragController.makeDraggable(o);
				}
				// XXX Allow objects to refer to dimensions
				o.setGameView(gameView);
			}
			// Add objects to the view
			gameView.addObjects(objects);

			// TODO make sure level transition works
			gameView.getInfoText().setText(currLevel.getLevelInfo());
			gameView.getInfoText().setVisible(true);
			gameView.getLevelWinPanel().setVisible(false);
			gameView.getGameOverPanel().setVisible(false);
		}
		// Show Go Button
		gameView.getButtonGo().setVisible(true);
	}

	private void loadNextLevel() {
		gameView.clearAllObjects();
		if (game.progressLevel()) {
			System.out.println("Load Next Level");
			loadCurrentLevel();
		} else {
			// Game is over
			System.out.println("Game Over");
			gameView.getGameOverPanel().setVisible(true);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		((GamePresenter) getPresenter()).persist();
	}

	@Override
	public void onClick(ClickEvent event) {
		Object source = event.getSource();
		if (source == gameView.getButtonMenu()) {
			((GamePresenter) getPresenter()).switchController();
		} else if (source == gameView.getButtonRestartLevel()) {
			loadCurrentLevel();
		} else if (source == gameView.getButtonGo()) {
			gameView.hideHelp();
			if (isEditing) {
				startSimulation();
			} else {
				stopSimulation();
			}
		} else if (source == gameView.getLevelWin()) {
			loadNextLevel();
			gameView.getLevelWinPanel().setVisible(false);
		} else if (source == gameView.getGameOver()) {
			game.setCurrentLevel(0);
			loadCurrentLevel();
		}
	}

	@Override
	public void onDragEnd(DragEndEvent event) {
		GameObject o = (GameObject) event.getSource();
		gameView.hideHelp();
		// Helper functions
		onDragEndScale(event);
		onDragEndBoundary(event);
	}

	// Helper functions for onDragEnd()

	/**
	 * Deals with scaling for bench and stage on object drop.
	 */
	private void onDragEndScale(DragEndEvent event) {
		GameObject o = (GameObject) event.getSource();

		// If the last drag was unsuccessful, we need to ensure the object is
		// correctly scaled
		if (event.getContext().finalDropController == null) {
			if (o.getLastDrop() != null) {
				// Restore to last drop controller
				if (o.getLastDrop() == dropControllerStage) {
					o.resizeForStage();
				} else if (o.getLastDrop() == dropControllerBench
						|| o.getLastDrop() == dropControllerBenchContent) {
					o.resizeForBench();
					o.setLastDrop(dropControllerBench);
				}
			}
		} else {
			// Save current successful drop controller for later
			o.setLastDrop(event.getContext().finalDropController);
		}
	}

	private void onDragEndBoundary(DragEndEvent event) {
		GameObject o = (GameObject) event.getSource();
		if (!o.isOnStage()) {
			return;
		}

		// TODO Fix the issue of moving below the stage
		o.fixBoundaryLeft();
		o.fixBoundaryRight();
		o.fixBoundaryTop();
		o.fixBoundaryBottom();
	}

	// End Helper functions for onDragEnd()

	@Override
	public void onDragStart(DragStartEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPreviewDragEnd(DragEndEvent event) throws VetoDragException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPreviewDragStart(DragStartEvent event)
			throws VetoDragException {
		// TODO Auto-generated method stub

	}

	// Create a timer and run update() for all objects on the stage
	private void startSimulation() {
		if (timerObj == null) {
			timerObj = new Timer() {
				@Override
				public void run() {
					timer += Physics.DELTA;
					boolean complete = simulate(gameView.getGameObjects(),
							Physics.DELTA);

					// XXX Measure delta manually
					// Date date = new Date();
					// Long delta = 0L;
					// if (lastDate != 0) {
					// delta = date.getTime() - lastDate;
					// }
					// lastDate = date.getTime();

					if (complete) {
						// Level has been completed
						System.out.println("Completed level " + game.getCurrentLevel());
						countScores();
						stopSimulation();
						gameView.getLevelWinPanel().setVisible(true);
						gameView.getButtonGo().setVisible(false);
					}
				}
			};
		}
		timerObj.scheduleRepeating(Physics.DELTA);
		gameView.changeGoButton(false);
		isEditing = false;
		timerObj.run();
		
		for (GameObject o : gameView.getGameObjects()) {
			try {
				dragController.makeNotDraggable(o);
			} catch (Exception e) {
				// Ignore
			}
		}
	}

	private void countScores() {
		if (timer < 10) {
			game.setLevelScore(game.getCurrentLevel(), 50);
		} else if (timer < 20) {
			game.setLevelScore(game.getCurrentLevel(), 20);
		} else {
			game.setLevelScore(game.getCurrentLevel(), 10);
		}

	}

	private void stopSimulation() {
		if (!isEditing) {
    		timerObj.cancel();
    		gameView.changeGoButton(true);
    		isEditing = true;
    		for (GameObject o : gameView.getGameObjects()) {
    			if (!o.isLocked()) {
    				dragController.makeDraggable(o);
    			}
    		}
		}
	}

	private boolean simulate(ArrayList<GameObject> obs, int delta) {
		for (int i = 0; i < obs.size(); i++) {
			GameObject o = obs.get(i);
			if (o.isOnStage()) {
				o.update(obs, delta);
			}
		}
		return game.getCurrentLevelObject().check();
	}
}
