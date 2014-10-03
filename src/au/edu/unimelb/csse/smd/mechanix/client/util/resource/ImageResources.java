package au.edu.unimelb.csse.smd.mechanix.client.util.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ImageResources extends ClientBundle {
	// Refresh project in Eclipse when adding new images

	// TEST

	@Source("img/other/duck.png")
	ImageResource getDuck();

	@Source("img/other/rabbit.png")
	ImageResource getRabbit();

	@Source("img/machine/box.png")
	ImageResource getBox();

	// SIMPLE MACHINES
	@Source("img/machine/fan_left.png")
	ImageResource getFanLeft();

	@Source("img/machine/fan_right.png")
	ImageResource getFanRight();

	@Source("img/machine/cb_left.png")
	ImageResource getBeltLeft();

	@Source("img/machine/cb_right.png")
	ImageResource getBeltRight();

	@Source("img/machine/spring.png")
	ImageResource getSpring();

	@Source("img/machine/bin.png")
	ImageResource getBin();

	@Source("img/machine/lift.png")
	ImageResource getLift();
	
	@Source("img/machine/metal.jpg")
	ImageResource getMetal();
	
	@Source("img/machine/concrete.jpg")
	ImageResource getConcrete();
	
	// OTHER

	@Source("img/other/blank.png")
	ImageResource getBlank();

	@Source("img/other/purple.png")
	ImageResource getPurple();

	@Source("img/game/help.png")
	ImageResource getHelp();

	@Source("img/other/arrow_right.png")
	ImageResource getArrowRight();

	// MENU

	@Source("img/menu/menu.png")
	ImageResource getMenu();

	@Source("img/menu/start.png")
	ImageResource getStart();

	@Source("img/menu/start_.png")
	ImageResource getStartHover();

	@Source("img/menu/resume.png")
	ImageResource getResume();

	@Source("img/menu/resume_.png")
	ImageResource getResumeHover();

	@Source("img/menu/restart.png")
	ImageResource getRestart();

	@Source("img/menu/restart_.png")
	ImageResource getRestartHover();
	
	@Source("img/menu/about.png")
	ImageResource getAbout();

	@Source("img/menu/about_.png")
	ImageResource getAboutHover();

	@Source("img/menu/dark.png")
	ImageResource getDark();

	@Source("img/menu/about_screen.png")
	ImageResource getAboutScreen();

	// STAGE

	@Source("img/game/stage.png")
	ImageResource getStage();

	@Source("img/game/go.png")
	ImageResource getGo();

	@Source("img/game/go_.png")
	ImageResource getGoHover();

	@Source("img/game/stop.png")
	ImageResource getStop();

	@Source("img/game/stop_.png")
	ImageResource getStopHover();

	@Source("img/game/menu.png")
	ImageResource getMenuButton();

	@Source("img/game/menu_.png")
	ImageResource getMenuButtonHover();
	
	@Source("img/game/restart_level.png")
	ImageResource getRestartLevelButton();

	@Source("img/game/restart_level_.png")
	ImageResource getRestartLevelButtonHover();

	@Source("img/game/level_win.png")
	ImageResource getLevelWin();

	@Source("img/game/game_win.png")
	ImageResource getGameWin();

}
