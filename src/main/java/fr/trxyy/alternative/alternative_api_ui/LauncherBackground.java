package fr.trxyy.alternative.alternative_api_ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import fr.trxyy.alternative.alternative_api.GameEngine;
import fr.trxyy.alternative.alternative_api.utils.ResourceLocation;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * @author Trxyy
 */
public class LauncherBackground {
	/**
	 * The position X
	 */
	public int posX;
	/**
	 * The position Y
	 */
	public int posY;
	/**
	 * The MediaPlayer, video for background
	 */
	public static MediaPlayer player;
	/**
	 * The opacity of the video
	 */
	public static double opacity = 1.0D;
	/**
	 * The MediaView, required for the Player
	 */
	public static MediaView viewer;

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 * @param mediaName The media
	 * @param root The Pane to add the background
	 */
	public LauncherBackground(GameEngine engine, String mediaName, Pane root) {
		this.posX = 0;
		this.posY = 0;
		if (mediaName.endsWith(".mp4") || mediaName.endsWith(".avi") || mediaName.endsWith(".mov")) {
			player = new MediaPlayer(getMedia(engine, mediaName));
			viewer = new MediaView(player);
			viewer.setFitWidth(engine.getLauncherPreferences().getWidth());
			viewer.setFitHeight(engine.getLauncherPreferences().getHeight());
			player.setAutoPlay(true);
			viewer.setPreserveRatio(false);
			viewer.setOpacity(opacity);
			player.setCycleCount(-1);
			player.play();
			root.getChildren().add(viewer);
		} else {
			ImageView backgroundImage = new ImageView();
			backgroundImage.setImage(loadImage(engine, mediaName));
			backgroundImage.setFitWidth(engine.getLauncherPreferences().getWidth());
			backgroundImage.setFitHeight(engine.getLauncherPreferences().getHeight());
			backgroundImage.setLayoutX(0);
			backgroundImage.setLayoutY(0);
			root.getChildren().add(backgroundImage);
		}
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
	
	public Media getMedia(GameEngine engine, String media) {
		Media theMedia = null;
		URL resourceUrl = ResourceLocation.class.getResource(String.valueOf(engine.getLauncherPreferences().getResourceLocation()) + media);
		try {
			theMedia = new Media(resourceUrl.toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return theMedia;
	}

	/**
	 * The Constructor
	 * @param engine The GameEngine instance
	 * @param f The media
	 * @param opa The opacity
	 * @param root The Pane to add the background
	 */
	public LauncherBackground(GameEngine engine, Media f, double opa, Pane root) {
		this.posX = 0;
		this.posY = 0;
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
		viewer.setFitWidth(engine.getLauncherPreferences().getWidth());
		viewer.setFitHeight(engine.getLauncherPreferences().getHeight());
		player.setAutoPlay(true);
		viewer.setPreserveRatio(false);
		viewer.setOpacity(opa);
		player.setCycleCount(-1);
		player.play();

		root.getChildren().add(viewer);
	}

	/**
	 * The Constructor
	 * @param f The media
	 * @param posX The position X
	 * @param posY The position Y
	 * @param sizeX The size X 
	 * @param sizeYThe size Y
	 * @param root The Pane to add the background
	 */
	public LauncherBackground(Media f, int posX, int posY, int sizeX, int sizeY, Pane root) {
		this.posX = 0;
		this.posY = 0;
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
		viewer.setFitWidth(sizeX);
		viewer.setFitHeight(sizeY);
		viewer.setLayoutX(posX);
		viewer.setLayoutX(posY);
		player.setAutoPlay(true);
		viewer.setPreserveRatio(false);
		viewer.setOpacity(opacity);
		player.setCycleCount(-1);
		player.play();

		root.getChildren().add(viewer);
	}

	/**
	 * The Constructor
	 * @param f The media
	 * @param posX The position X
	 * @param posY The position Y
	 * @param sizeX The size X 
	 * @param sizeYThe size Y
	 * @param opa The opacity
	 * @param root The Pane to add the background
	 */
	public LauncherBackground(Media f, int posX, int posY, int sizeX, int sizeY, double opa, Pane root) {
		this.posX = 0;
		this.posY = 0;
		MediaPlayer player = new MediaPlayer(f);
		MediaView viewer = new MediaView(player);
		viewer.setFitWidth(sizeX);
		viewer.setFitHeight(sizeY);
		viewer.setLayoutX(posX);
		viewer.setLayoutX(posY);
		player.setAutoPlay(true);
		viewer.setPreserveRatio(false);
		viewer.setOpacity(opa);
		player.setCycleCount(-1);
		player.play();

		root.getChildren().add(viewer);
	}

	/**
	 * Set the opacity
	 * @param opaci The opacity 
	 */
	public static void setOpacity(double opaci) {
		opacity = opaci;
	}

	/**
	 * @return The MediaPlayer
	 */
	public static MediaPlayer getPlayer() {
		return player;
	}

	/**
	 * @return The MediaView
	 */
	public static MediaView getViewer() {
		return viewer;
	}
}
