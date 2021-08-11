package fr.trxyy.alternative.alternative_api_ui;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.utils.WindowStyle;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * @author Trxyy
 */
public class LauncherPane extends Pane {

	/**
	 * The Constructor
	 * @param gameEngine The GameEngine instance
	 */
	
	private GameEngine gameEngine;
	
	public LauncherPane(GameEngine engine) {
		this.gameEngine = engine;
		this.setPrefSize(engine.getLauncherPreferences().getWidth(), engine.getLauncherPreferences().getHeight());
	}
	
	public LauncherPane(GameEngine engine, double size, WindowStyle style) {
		this.gameEngine = engine;
		this.setPrefSize(engine.getLauncherPreferences().getWidth(), engine.getLauncherPreferences().getHeight());
		
		if (size != 0) {
			this.setCorners(size);
		}
		
		if (style.equals(WindowStyle.TRANSPARENT)) {
			this.setTransparent();
		}
		
	}
	
	public void setCorners(double size) {
		Rectangle rect = new Rectangle(this.gameEngine.getLauncherPreferences().getWidth(), this.gameEngine.getLauncherPreferences().getHeight());
		rect.setArcHeight(size);
		rect.setArcWidth(size);
		this.setClip(rect);
	}
	
	public void setTransparent() {
		this.setStyle("-fx-background-color: transparent;");
	}

}
