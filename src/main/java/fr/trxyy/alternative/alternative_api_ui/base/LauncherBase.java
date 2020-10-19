package fr.trxyy.alternative.alternative_api_ui.base;

import java.awt.Point;
import java.io.IOException;

import fr.trxyy.alternative.alternative_api.ApiConstants;
import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.maintenance.Maintenance;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.OperatingSystem;
import fr.trxyy.alternative.alternative_api_ui.LauncherAlert;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * @author Trxyy
 */
public class LauncherBase {

	/**
	 * The point where the mouse is clicked
	 */
	final Point dragDelta = new Point();

	/**
	 * The Constructor
	 * @param stage The primary stage
	 * @param scene The Scene
	 * @param style The Stage Style (Transparent etc...
	 * @param engine The GameEngine instance
	 */
	public LauncherBase(final Stage stage, Scene scene, StageStyle style, GameEngine engine) {
		
		if (OperatingSystem.getCurrentPlatform() == OperatingSystem.OSX || OperatingSystem.getCurrentPlatform() == OperatingSystem.LINUX || OperatingSystem.getCurrentPlatform() == OperatingSystem.SOLARIS) {
			Logger.log("Il semblerait que vous utilisiez Mac/Linux.");
			Logger.log("Si vous rencontrez des difficultés, rendez-vous sur:");
			Logger.log("https://forum.alternative-api.fr/installer-jfx-sur-linux-aapi");
		}

		stage.initStyle(style);
		if (style.equals(StageStyle.TRANSPARENT)) {
			scene.setFill(Color.TRANSPARENT);
		}
		stage.setResizable(false);
		stage.setTitle(engine.getLauncherPreferences().getName());
		stage.setWidth(engine.getLauncherPreferences().getWidth());
		stage.setHeight(engine.getLauncherPreferences().getHeight());
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent windowEvent) {
				Platform.exit();
				System.exit(0);
			}
		});
		if (engine.getLauncherPreferences().isMoveable()) {
			scene.setOnMousePressed(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					dragDelta.x = (int) (stage.getX() - mouseEvent.getScreenX());
					dragDelta.y = (int) (stage.getY() - mouseEvent.getScreenY());
				}
			});
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent mouseEvent) {
					stage.setX(mouseEvent.getScreenX() + dragDelta.x);
					stage.setY(mouseEvent.getScreenY() + dragDelta.y);
				}
			});
		}
		stage.setScene(scene);
		this.displayCopyrights();
		
		if (engine.getGameMaintenance() != null) {
			if (engine.getGameMaintenance().getMaintenance().equals(Maintenance.USE)) {
				String result = "";
				try {
					result = engine.getGameMaintenance().readMaintenance();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (!result.equals("Ok")) {
					engine.getGameMaintenance().setAccessBlocked(true);
					new LauncherAlert("" + result, AlertType.WARNING);
				} else {
					stage.show();
				}
			} else {
				stage.show();
			}
		} else {
			stage.show();
		}

	}

	/**
	 * Set the Icon Image of the window
	 * @param primaryStage The Stage to set the icon
	 * @param img The image
	 */
	public void setIconImage(Stage primaryStage, Image img) {
		primaryStage.getIcons().add(img);
	}

	/**
	 * Display some copyrights
	 */
	private void displayCopyrights() {
		Logger.log("========================================");
		Logger.log("|    Thanks for using AlternativeAPI   |");
		Logger.log("|         AlternativeAPI " + ApiConstants.getVersion() + "        |");
		Logger.log("|           Version: " + ApiConstants.getVersionType() + "           |");
		Logger.log("|            Author: " + ApiConstants.getAuthor() + "             |");
		Logger.log("|           Helper(s): " + ApiConstants.getHelpers() + "         |");
		Logger.log("========================================");
	}	

}