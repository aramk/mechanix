package au.edu.unimelb.csse.smd.mechanix.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public interface MenuViewUI {
	public ImageButton getButtonGame();

	public ImageButton getButtonRestart();
	
	public ImageButton getButtonAbout();

	public FlowPanel getAboutPanel();

	public ImageButton getAboutImage();
}
