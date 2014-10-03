package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.server.MugleClient;
import au.edu.unimelb.csse.smd.mechanix.client.util.view.Presenter;

public class GamePresenter extends Presenter {
	private GameModel game;
	private MugleClient mugle;
	private GameController gameController;
	private MenuController menuController;
	public static Display display;

	public GamePresenter() {
		display = new DisplayDesktop();
		game = new GameModel();
		mugle = new MugleClient();
		gameController = new GameController(this, game);
		menuController = new MenuController(this, game);
		activateController(menuController);
		populate();
	}

	public void switchController() {
		if (getActiveController() == gameController) {
			activateController(menuController);
		} else {
			activateController(gameController);
		}
	}

	public void populate() {
		mugle.populate(game, gameController);
	}

	public void persist() {
		mugle.persist(game);
	}
}
