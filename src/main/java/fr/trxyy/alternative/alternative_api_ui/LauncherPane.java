package fr.trxyy.alternative.alternative_api_ui;

import fr.trxyy.alternative.alternative_api.GameEngine;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherPane extends Pane {

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 */
	public LauncherPane(GameEngine engine) {
		this.setPrefSize(engine.getLauncherPreferences().getWidth(), engine.getLauncherPreferences().getHeight());
	}

}
