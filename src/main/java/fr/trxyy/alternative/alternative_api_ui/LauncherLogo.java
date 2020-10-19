package fr.trxyy.alternative.alternative_api_ui;

import java.awt.MouseInfo;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherLogo {

	/**
	 * The logo image
	 */
	public static ImageView logo = new ImageView();
	/**
	 * The position X
	 */
	public int posX = 0;
	/**
	 * The position Y
	 */
	public int posY = 0;
	/**
	 * The opacity of the image
	 */
	public static double opacity = 1.0;

	/**
	 * The Constructor
	 * @param img The image
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 * @param root The Pane to add
	 */
	public LauncherLogo(Image img, int sizeX, int sizeY, Pane root) {
		logo.setImage(img);
		logo.setFitWidth(sizeX);
		logo.setFitHeight(sizeY);
		logo.setOpacity(opacity);
		root.getChildren().add(logo);
		animateLogo();
	}

	/**
	 * The Constructor
	 * @param img The image
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 * @param posX The position X
	 * @param posY The position Y
	 * @param root The Pane to add
	 */
	public LauncherLogo(Image img, int sizeX, int sizeY, int posX, int posY, Pane root) {
		logo.setImage(img);
		logo.setFitWidth(sizeX);
		logo.setFitHeight(sizeY);
		logo.setOpacity(opacity);
		logo.setLayoutX(posX);
		logo.setLayoutY(posY);
		root.getChildren().add(logo);
	}

	/**
	 * The Constructor
	 * @param img The image
	 * @param sizeX The size X
	 * @param sizeY The size Y
	 * @param opacit The opacity
	 * @param root The Pane to add
	 */
	public LauncherLogo(Image img, int sizeX, int sizeY, double opacit, Pane root) {
		logo.setImage(img);
		logo.setFitWidth(sizeX);
		logo.setFitHeight(sizeY);
		logo.setOpacity(opacit);
		root.getChildren().add(logo);
	}

	/**
	 * Animate the logo to move when mouse move
	 */
	private void animateLogo() {
		AnimationTimer animate = new AnimationTimer() {
			@Override
			public void handle(long now) {
				float multplicatorSize = 54.25f;
				int mouseX = MouseInfo.getPointerInfo().getLocation().x;
				int mouseY = MouseInfo.getPointerInfo().getLocation().y;
				logo.setLayoutX(posX - (mouseX / multplicatorSize));
				logo.setLayoutY(posY - (mouseY / multplicatorSize));
			}
		};
		animate.start();
	}
	
	/**
	 * Set the opacity
	 * @param opaci The opacity value
	 */
	public static void setOpacity(double opaci) {
		opacity = opaci;
	}

	/**
	 * Set the image position
	 * @param x The position X
	 * @param y The position Y
	 */
	public void setImagePos(int x, int y) {
		posX = x;
		posY = y;
	}

	/**
	 * @return The logo as ImageView
	 */
	public ImageView getLogo() {
		return logo;
	}

}
