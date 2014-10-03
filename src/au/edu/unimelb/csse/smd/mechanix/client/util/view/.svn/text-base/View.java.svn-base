package au.edu.unimelb.csse.smd.mechanix.client.util.view;

import java.util.ArrayList;

import au.edu.unimelb.csse.smd.mechanix.client.stage.GameObject;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class View {
	private ArrayList<Widget> gameObjects = new ArrayList<Widget>();
	private boolean isVisible;
	private AbsolutePanel panel = new AbsolutePanel();

	public View(int x, int y, int width, int height, String styleName) {
		this(x, y, width, height);
		panel.setStyleName(styleName);
	}

	public View(int x, int y, int width, int height) {
		panel.setPixelSize(width, height);
		RootPanel.get().add(panel, x, y);
		hide();
	}

	public void show() {
		panel.setVisible(true);
	}

	public void hide() {
		panel.setVisible(false);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void addObject(Widget w, int x, int y) {
		gameObjects.add(w);
		panel.add(w, x, y);
	}

	public void removeObject(Widget o) {
		gameObjects.remove(o);
		panel.remove(o);
	}

	public ArrayList<Widget> getObjects() {
		return gameObjects;
	}

	public AbsolutePanel getPanel() {
		return panel;
	}

	/*
	 * public void addWidget(Widget w, int x, int y) { widgets.add(w);
	 * panel.add(w, x, y); }
	 * 
	 * public void removeWidget(Widget w) { widgets.remove(w); panel.remove(w);
	 * }
	 * 
	 * public ArrayList<Widget> getWidgets() { return widgets; }
	 */

	public void setSize(int height, int width) {
		panel.setPixelSize(width, height);
	}
}
