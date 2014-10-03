package au.edu.unimelb.csse.smd.mechanix.client.server;

import java.io.Serializable;
import java.util.Vector;

import au.edu.unimelb.csse.mugle.client.api.Services;
import au.edu.unimelb.csse.mugle.shared.api.KeyError;
import au.edu.unimelb.csse.smd.mechanix.client.model.GameModel;
import au.edu.unimelb.csse.smd.mechanix.client.view.GameController;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This class handles all the communication with the MUGLE server. As such, it
 * is responsible for getting the game state from the MUGLE server and also for
 * submitting any changes in the game state to the server.
 */
public class MugleClient {
	/**
	 * The game token is a unique string which identifies the game to the
	 * server, and should be kept secret. You will need to pass this string to
	 * most API calls. For this workshop, everybody can use the same string;
	 * later each team will be given a unique game token.
	 */
	private static final String gameToken = "0df442e7-b0d7-4594-8a60-fe06422ef9a5";

	/**
	 * Submits those parts of the game state to the MUGLE server that are marked
	 * as "dirty".
	 * 
	 * @param game
	 *            The game state to submit to the server.
	 */
	public void persist(GameModel game) {
		// System.out.println("persist");
		// If it is dirty, then the game state is newer than the server, so we
		// need to update the server
		if (game.isDirtyCurrentLevel()) {
			Services.keyvalue.put(gameToken, "currentLevel",
					game.getCurrentLevel(), new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
						}
					});
		}

		if (game.isDirtyHighestLevel()) {
			Services.keyvalue.put(gameToken, "highestLevel",
					game.getHighestLevel(), new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
						}
					});
		}

		// XXX Changed

		if (game.isDirtyLevelScores()) {
			Services.keyvalue.put(gameToken, "scores", game.getLevelScores(),
					new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
						}
					});
		}

		if (game.isDirtyHighscore()) {
			Services.highscore.saveScore(gameToken, game.getHighscore(),
					new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
						}
					});
		}

		// TODO Persist all other "dirty" parts of the game state
		//game.clean();
	}

	/**
	 * Updates a given game state object with the data obtained from the MUGLE
	 * server.
	 * 
	 * @param game
	 *            The game state to update.
	 */
	public void populate(final GameModel game, final GameController gameController) {
		// System.out.println("populate");
		// This gets the nickname from the server, then the callback sets the
		// nickname
		Services.user.getUserNickName(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				game.setNickname(result);
			}
		});

		Services.keyvalue.get(gameToken, "currentLevel",
				new AsyncCallback<Serializable>() {
					@Override
					public void onFailure(Throwable caught) {
						// Check whether the error occurs because there is no
						// value associated
						// to this key yet.
						if (caught instanceof KeyError) {
							game.setCurrentLevel(0);
							gameController.loadCurrentLevel();
						}
					}

					@Override
					public void onSuccess(Serializable result) {
						game.setCurrentLevel((Integer) result);
						gameController.loadCurrentLevel();
					}
				});

		Services.keyvalue.get(gameToken, "highestLevel",
				new AsyncCallback<Serializable>() {
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof KeyError) {
							game.setHighestLevel(game.getCurrentLevel());
						}
					}

					@Override
					public void onSuccess(Serializable result) {
						game.setHighestLevel((Integer) result);
					}
				});

		// /*
		Services.keyvalue.get(gameToken, "scores",
				new AsyncCallback<Serializable>() {
					@Override
					public void onFailure(Throwable caught) {
					}

					@SuppressWarnings("unchecked")
					@Override
					public void onSuccess(Serializable result) {
						game.setLevelScores((Vector<Integer>) result);
					}
				});
		// */

		Services.highscore.getHighScore(gameToken,
				new AsyncCallback<Integer>() {
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof KeyError) {
							game.setHighscore(0);
						}
					}

					@Override
					public void onSuccess(Integer result) {
						game.setHighscore(result);
					}
				});

		/*Services.badges.isAchieved(gameToken, "rescueSnow",
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof KeyError) {
							// System.out.println("rescueSnow failed");
							game.setBadge(0, false);
						}
					}

					@Override
					public void onSuccess(Boolean result) {
						// System.out.println("rescueSnow " + result);
						game.setBadge(0, result);
					}
				});

		Services.badges.isAchieved(gameToken, "rescueDwarves",
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof KeyError) {
							// System.out.println("rescueDwarves failed");
							game.setBadge(1, false);
						}
					}

					@Override
					public void onSuccess(Boolean result) {
						// System.out.println("rescueDwarves " + result);
						game.setBadge(1, result);
					}
				});

		Services.badges.getProgress(gameToken, "rescueDwarves",
				new AsyncCallback<Integer>() {
					@Override
					public void onFailure(Throwable caught) {
						if (caught instanceof KeyError) {
							// System.out.println("rescueDwarves failed");
						}
					}

					@Override
					public void onSuccess(Integer result) {
						// System.out.println("rescueDwarves " + result);
					}
				});*/

	}

	/**
	 * This method increments the points earned towards the second badge on the
	 * server and on success of that call changes the local game state.
	 * 
	 * @param game
	 *            The local game state.
	 */
	public void incrementSecondBadge(final GameModel game) {
		// XXX Changed
		Services.badges.incrementProgress(gameToken, "rescueDwarves",
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						game.setBadge(1, result);
					}
				});
	}

	/**
	 * This method informs the server about the achievement of a badge and on
	 * success of that call changes the local game state.
	 * 
	 * @param game
	 *            The local game state.
	 * @param name
	 *            The name of that badge, i.e. "rescueSnow" or "rescueDwarves"
	 */
	/*public void achieveBadge(final GameModel game, String name) {
		// XXX Changed
		if (name == "rescueSnow") {
			Services.badges.setAchieved(gameToken, "rescueSnow",
					new AsyncCallback<Void>() {
						@Override
						public void onFailure(Throwable caught) {
							Window.alert(caught.getMessage());
						}

						@Override
						public void onSuccess(Void result) {
							// System.out.println("snow achieved");
							game.setBadge(0, true);
						}
					});
		}
	}*/
}
