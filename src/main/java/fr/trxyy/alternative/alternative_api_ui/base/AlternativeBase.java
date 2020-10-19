package fr.trxyy.alternative.alternative_api_ui.base;

import fr.trxyy.alternative.alternative_api.utils.ResourceLocation;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Trxyy
 */
public abstract class AlternativeBase extends Application {
	/**
	 * The resource location
	 */
	private static ResourceLocation RESOURCE_LOCATION = new ResourceLocation();
	/**
	 * The start function (called by JavaFX before all)
	 */
	public abstract void start(Stage primaryStage) throws Exception;

	/**
	 * @return The resourcelocation
	 */
	public ResourceLocation getResourceLocation() {
		return RESOURCE_LOCATION;
	}
}
