package au.edu.unimelb.csse.smd.mechanix.client.view;

import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observable;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observer;
import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;
import au.edu.unimelb.csse.smd.mechanix.client.util.view.View;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class MenuView extends View implements Observer, MenuViewUI {
	private GameModel game;

	// Images
	private Image iMenu = new Image(Resources.image.getMenu());

	// Create UI
	private ImageButton bGame = new ImageButton(Resources.image.getStart(),
			Resources.image.getStartHover());

	private ImageButton bRestart = new ImageButton(Resources.image.getRestart(),
			Resources.image.getRestartHover());
	
	private ImageButton bAbout = new ImageButton(Resources.image.getAbout(),
			Resources.image.getAboutHover());

	private FlowPanel pMenu = new FlowPanel();

	private FlowPanel pAbout = new FlowPanel();
	private ImageButton bAboutInfo = new ImageButton(
			Resources.image.getAboutScreen());

	public MenuView(GameModel game) {
		super(GamePresenter.display.left(), GamePresenter.display.top(),
				GamePresenter.display.width(), GamePresenter.display.height(),
				"gwt-Menu");
		this.game = game;
		this.game.addObserver(this);

		// Images
		addObject(iMenu, 0, 0);

		// Add UI to View
		pMenu.addStyleName("gwt-MenuPanel");
		
		addObject(pMenu, 0, 220);
		pMenu.add(bGame);
		pMenu.add(bRestart);
		pMenu.add(bAbout);

		// About Panel
		// pAbout.setPixelSize((int) (GamePresenter.display.width() * 0.75),
		// (int) (GamePresenter.display.height() * 0.75));
		addObject(pAbout, 0, 100);
		pAbout.setVisible(false);
		pAbout.addStyleName("gwt-AboutPanel");
		pAbout.add(bAboutInfo);
		// pAbout.getElement().getStyle()
		// .setBackgroundImage(Resources.image.getDark().getURL());
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	public ImageButton getButtonGame() {
		return bGame;
	}
	
	@Override
	public ImageButton getButtonRestart() {
		return bRestart;
	}

	@Override
	public ImageButton getButtonAbout() {
		return bAbout;
	}

	@Override
	public FlowPanel getAboutPanel() {
		return pAbout;
	}

	@Override
	public ImageButton getAboutImage() {
		return bAboutInfo;
	}
}
