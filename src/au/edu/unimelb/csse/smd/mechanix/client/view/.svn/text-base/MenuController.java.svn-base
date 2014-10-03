package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observable;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observer;
import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;
import au.edu.unimelb.csse.smd.mechanix.client.util.view.ViewController;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

public class MenuController extends ViewController implements Observer,
		ClickHandler {
	private GameModel game;
	// This instance ensures MenuViewUI is implemented
	private MenuViewUI menuView;
	// Whether the game has started
	private boolean started = false;

	public MenuController(GamePresenter presenter, GameModel game) {
		menuView = new MenuView(game);
		setView((MenuView) menuView);
		setPresenter(presenter);
		this.game = game;
		this.game.addObserver(this);

		// Bind UI
		menuView.getButtonGame().addClickHandler(this);
		menuView.getButtonRestart().addClickHandler(this);
		menuView.getButtonRestart().setVisible(false);
		menuView.getButtonAbout().addClickHandler(this);
		menuView.getAboutImage().addClickHandler(this);

		// Check if game has been played before
		if (game.getCurrentLevel() != 0) {
			changeStartButton();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == menuView.getButtonGame()) {
			((GamePresenter) getPresenter()).switchController();
			if (!started || game.getCurrentLevel() == 0) {
				changeStartButton();
			}
		} else if (event.getSource() == menuView.getButtonRestart()) {
			game.setCurrentLevel(0);
			((GamePresenter) getPresenter()).switchController();
			// TODO not very clean
			GameController gc = (GameController) getPresenter().getActiveController();
			gc.loadCurrentLevel();
		} else if (event.getSource() == menuView.getButtonAbout()) {
			menuView.getAboutPanel().setVisible(true);
		} else if (event.getSource() == menuView.getAboutImage()) {
			menuView.getAboutPanel().setVisible(false);
		}
	}

	private void changeStartButton() {
		started = true;
		menuView.getButtonRestart().setVisible(true);
		menuView.getButtonGame().getUpFace()
				.setImage(new Image(Resources.image.getResume()));
		menuView.getButtonGame().getUpHoveringFace()
				.setImage(new Image(Resources.image.getResumeHover()));
	}
}
