package fr.trxyy.alternative.alternative_api_ui.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherImage extends ImageView {
	
	/**
	 * The Constructor
	 * @param root The Pane to add the image
	 */
	public LauncherImage(Pane root) {
		root.getChildren().add(this);
	}
	
	/**
	 * The Constructor
	 * @param root The Pane to add the image
	 * @param image The image
	 */
	public LauncherImage(Pane root, Image image) {
		super(image);
		root.getChildren().add(this);
	}

	/**
	 * Set the size of the image
	 * @param width_ The width
	 * @param height_ The height
	 */
	public void setSize(int width_, int height_) {
		this.setFitWidth(width_);
		this.setFitHeight(height_);
	}

	/**
	 * Set the position
	 * @param posX The position X
	 * @param posY The position Y
	 */
	public void setPosition(int posX, int posY) {
		this.setLayoutX(posX);
		this.setLayoutY(posY);
	}

}
