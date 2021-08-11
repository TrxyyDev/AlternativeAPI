package fr.trxyy.alternative.alternative_api_ui.base;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.trxyy.alternative.alternative_api.ApiConstants;
import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.maintenance.Maintenance;
import fr.trxyy.alternative.alternative_api.utils.Logger;
import fr.trxyy.alternative.alternative_api.utils.Mover;
import fr.trxyy.alternative.alternative_api.utils.OperatingSystem;
import fr.trxyy.alternative.alternative_api.utils.ResourceLocation;
import fr.trxyy.alternative.alternative_api_ui.LauncherAlert;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
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
	 * The GameEngine instance
	 */
	public GameEngine gameEngine;

	/**
	 * The Constructor
	 * @param stage The primary stage
	 * @param scene The Scene
	 * @param style The Stage Style (Transparent etc...
	 * @param engine The GameEngine instance
	 */
	public LauncherBase(final Stage stage, Scene scene, StageStyle style, GameEngine engine) {
		this.gameEngine = engine;
		
		if (OperatingSystem.getCurrentPlatform() == OperatingSystem.OSX || OperatingSystem.getCurrentPlatform() == OperatingSystem.LINUX || OperatingSystem.getCurrentPlatform() == OperatingSystem.SOLARIS) {
			Logger.log("Il semblerait que vous utilisiez Mac/Linux.");
			Logger.log("Si vous rencontrez des difficultés, rendez-vous sur:");
			Logger.log("https://forum.alternative-api.fr/installer-jfx-sur-linux-aapi");
		}
		
		engine.reg(stage);

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
		if (engine.getLauncherPreferences().isMoveable().equals(Mover.MOVE)) {
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
	public void setIconImage(Stage primaryStage, String imgName) {
		Image img = loadImage(this.gameEngine, imgName);
		primaryStage.getIcons().add(img);
	}
	
	/**
	 * @param engine The GameEngine instance
	 * @param image The image as a string
	 * @return The Image from the string
	 */
	public Image loadImage(GameEngine engine, String image) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(ResourceLocation.class.getResource(String.valueOf(engine.getLauncherPreferences().getResourceLocation()) + image));
		} catch (IOException iOException) {
		}
		return SwingFXUtils.toFXImage(bufferedImage, null);
	}

	/**
	 * Display some copyrights
	 */
	private void displayCopyrights() {
		Logger.log("========================================");
		Logger.log("|           AlternativeUpdater         |");
		Logger.log("|            Version: " + ApiConstants.getVersion() + "            |");
		Logger.log("========================================");
	}

}