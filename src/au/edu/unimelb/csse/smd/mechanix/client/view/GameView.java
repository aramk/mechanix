package au.edu.unimelb.csse.smd.mechanix.client.view;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.text.TextBoldWhite;
import au.edu.unimelb.csse.smd.mechanix.client.text.TextGrey;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observable;
import au.edu.unimelb.csse.smd.mechanix.client.util.observer.Observer;
import au.edu.unimelb.csse.smd.mechanix.client.util.resource.Resources;
import au.edu.unimelb.csse.smd.mechanix.client.util.view.View;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;

public class GameView extends View implements Observer, GameViewUI {
	private GameModel game;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	// Text
	private TextBoldWhite tLevel = new TextBoldWhite("0");
	private Image tLevelTitle = new Image(Resources.text.bw_level());

	// Help
	private Image iHelp = new Image(Resources.image.getHelp());

	private TextGrey tScore = new TextGrey("0");
	private Image tScoreTitle = new Image(Resources.text.grey_score());

	private TextGrey tName = new TextGrey("", 0.8f);

	private TextGrey tInfo = new TextGrey("", 0.8f);

	// Images
	private Image iStage = new Image(Resources.image.getStage());

	//private Image iGameWin = new Image(Resources.image.getGameWin());
	//private FlowPanel pGameOver = new FlowPanel();

	// Create UI
	private ImageButton bMenu = new ImageButton(
			Resources.image.getMenuButton(),
			Resources.image.getMenuButtonHover());
	private ImageButton bRestartLevel = new ImageButton(
			Resources.image.getRestartLevelButton(),
			Resources.image.getRestartLevelButtonHover());
	private SwitchImageButton bGo = new SwitchImageButton(
			Resources.image.getGo(), Resources.image.getGoHover(),
			Resources.image.getStop(), Resources.image.getStopHover());
	private ImageButton bLevelWin = new ImageButton(
			Resources.image.getLevelWin());
	private ImageButton bGameOver = new ImageButton(Resources.image.getGameWin());
	private FlowPanel pLevelWin = new FlowPanel();
	private FlowPanel pGameOver = new FlowPanel();

	// Panels
	private AbsolutePanel pStage = new AbsolutePanel();
	private ScrollPanel pBench = new ScrollPanel();
	private HorizontalPanel pBenchContent = new HorizontalPanel();

	public GameView(GameModel game) {
		super(GamePresenter.display.left(), GamePresenter.display.top(),
				GamePresenter.display.width(), GamePresenter.display.height(),
				"gwt-Game");
		this.game = game;
		this.game.addObserver(this);

		// Images
		addObject(iStage, 0, 0);

		addObject(pLevelWin, 0, 150);
		pLevelWin.setVisible(false);
		pLevelWin.addStyleName("gwt-LevelWin");
		pLevelWin.add(bLevelWin);

		addObject(pGameOver, 0, 150);
		pGameOver.setVisible(false);
		pGameOver.addStyleName("gwt-GameOver");
		pGameOver.add(bGameOver);

		// Panels
		pStage.setPixelSize(getStageWidth(), getStageHeight());
		pStage.addStyleName("gwt-Stage");
		getPanel().add(pStage, getStageLeft(), getStageTop());

		pBench.setPixelSize(getBenchWidth(), getBenchHeight());
		pBench.addStyleName("gwt-Bench");
		getPanel().add(pBench, getBenchLeft(), getBenchTop());

		pBenchContent.addStyleName("gwt-BenchPanel");
		pBenchContent.setSpacing(getBenchSpacing());
		pBenchContent.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		pBenchContent
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		pBench.add(pBenchContent);

		// Text
		addObject(tLevel, 0, 30);
		tLevel.insert(tLevelTitle, 0);
		tLevel.addStyleName("gwt-ImageTextCenter");

		// Help
		addObject(iHelp, 10, 420);

		addObject(tScore, 0, 45);
		tScore.insert(tScoreTitle, 0);
		tScore.addStyleName("gwt-ImageTextRight");

		addObject(tName, 0, 10);
		tName.addStyleName("gwt-ImageTextRight");

		addObject(tInfo, 0, 100);
		tInfo.addStyleName("gwt-ImageTextCenter");

		// Add UI to View
		addObject(bMenu, 0, 5);
		addObject(bRestartLevel, 0, 40);
		addObject(bGo, 650, 525);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Game levels start from 1 semantically
		tLevel.setText(" " + String.valueOf(game.getCurrentLevel() + 1));
		tScore.setText(" " + String.valueOf(game.getScore()));
		tName.setText(" " + String.valueOf(game.getNickname()));
		// Hide level if game is over
		if (game.getCurrentLevel() == game.getNumLevels()) {
			tLevel.setVisible(false);
			tLevelTitle.setVisible(false);
		} else {
			tLevel.setVisible(true);
			tLevelTitle.setVisible(true);
		}
	}

	@Override
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	@Override
	public void addObjects(ArrayList<GameObject> objects) {
		for (GameObject o : objects) {
			addObject(o);
		}
	}

	@Override
	/**
	 * Either changes the objects from the bench to the stage and vice versa, or creates a new
	 * object in the given panel. 
	 */
	public void addObject(GameObject o) {
		if (!gameObjects.contains(o)) {
			// Create new object and place on stage or bench
			gameObjects.add(o);
			if (o.isOnStage()) {
				// Add objects to the stage at their starting coordinates
				o.resizeForStage();
				pStage.add(o, (int) o.getStartX(), (int) o.getStartY());
			} else {
				// Add objects to the bench
				o.resizeForBench();
				pBenchContent.add(o);
			}
		}
	}

	@Override
	public void clearAllObjects() {
		gameObjects.clear();
		pStage.clear();
		pBenchContent.clear();
		tInfo.setText("");
	}
	
	@Override
	public void updateLocked(boolean isEditing) {
		/*for (GameObject o : gameObjects) {
			if (isEditing) {
				o.setLocked(true);
			} else {
				o.setLocked(isLocked)
			}
		}*/
	}

	@Override
	public AbsolutePanel getStage() {
		return pStage;
	}

	@Override
	public ScrollPanel getBench() {
		return pBench;
	}

	@Override
	public HorizontalPanel getBenchContent() {
		return pBenchContent;
	}

	@Override
	public void putOnStage(GameObject o) {
		if (gameObjects.contains(o)) {
			pStage.add(o);
			o.setOnStage(true);
		}
	}

	@Override
	public void putOnBench(GameObject o) {
		if (gameObjects.contains(o)) {
			pBenchContent.add(o);
			o.setOnStage(false);
		}
	}

	// BUTTONS

	@Override
	public ImageButton getButtonMenu() {
		return bMenu;
	}
	
	@Override
	public ImageButton getButtonRestartLevel() {
		return bRestartLevel;
	}

	@Override
	public SwitchImageButton getButtonGo() {
		return bGo;
	}

	@Override
	public ImageButton getLevelWin() {
		return bLevelWin;
	}

	// BUTTONS

	@Override
	public Image getHelp() {
		return iHelp;
	}

	@Override
	public void changeGoButton(boolean isStopped) {
		bGo.switchImage(isStopped);
	}

	@Override
	public void hideHelp() {
		if (iHelp.isVisible()) {
			iHelp.setVisible(false);
		}
	}

	@Override
	public int getStageHeight() {
		return 420;
	}

	@Override
	public int getStageWidth() {
		return 800;
	}

	@Override
	public int getBenchHeight() {
		return 100;
	}

	@Override
	public int getBenchWidth() {
		return 640;
	}

	@Override
	public int getBenchSpacing() {
		return 5;
	}

	@Override
	public int getBenchContentHeight() {
		return getBenchHeight() - 2 * getBenchSpacing();
	}

	@Override
	public int getStageLeft() {
		return 0;
	}

	@Override
	public int getStageTop() {
		return 80;
	}

	@Override
	public int getBenchLeft() {
		return 0;
	}

	@Override
	public int getBenchTop() {
		return getStageTop() + getStageHeight();
	}

	@Override
	public TextGrey getInfoText() {
		return tInfo;
	}

	@Override
	public FlowPanel getLevelWinPanel() {
		return pLevelWin;
	}

	@Override
	public ImageButton getGameOver() {
		return bGameOver;
	}
	
	@Override
	public FlowPanel getGameOverPanel() {
		return pGameOver;
	}
}
