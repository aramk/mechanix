package au.edu.unimelb.csse.smd.mechanix.client.view;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;
import au.edu.unimelb.csse.smd.mechanix.client.text.TextGrey;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.ScrollPanel;

public interface GameViewUI {

	// Dimensions

	public int getStageLeft();

	public int getStageTop();

	public int getStageHeight();

	public int getStageWidth();

	public int getBenchLeft();

	public int getBenchTop();

	public int getBenchHeight();

	public int getBenchWidth();

	public int getBenchSpacing();

	public int getBenchContentHeight();

	// End Diminsions

	public ImageButton getButtonMenu();
	
	public ImageButton getButtonRestartLevel();

	public SwitchImageButton getButtonGo();

	public AbsolutePanel getStage();

	public ScrollPanel getBench();

	public HorizontalPanel getBenchContent();

	public Image getHelp();

	public void hideHelp();

	public void changeGoButton(boolean isStopped);

	public ArrayList<GameObject> getGameObjects();

	public void addObjects(ArrayList<GameObject> objects);

	public void addObject(GameObject o);

	public void putOnBench(GameObject o);

	public void putOnStage(GameObject o);

	public TextGrey getInfoText();

	public ImageButton getLevelWin();

	public FlowPanel getLevelWinPanel();

	public void clearAllObjects();
	
	public void updateLocked(boolean isEditing);

	public ImageButton getGameOver();
	
	public FlowPanel getGameOverPanel();

}
