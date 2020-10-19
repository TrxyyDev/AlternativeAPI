package fr.trxyy.alternative.alternative_api_ui.components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Trxyy
 */
public class LauncherRectangle extends Rectangle {

	/**
	 * The Constructor
	 * @param root The pane to ad the rectangle
	 * @param x The position X
	 * @param y The position Y
	 * @param sX The size X
	 * @param sY The size Y
	 */
	public LauncherRectangle(Pane root, int x, int y, int sX, int sY) {
		this.setX(x);
		this.setY(y);
		this.setWidth(sX);
		this.setHeight(sY);
		root.getChildren().add(this);
	}

	/**
	 * The Constructor
	 * @param width The width
	 * @param height The height
	 */
	public LauncherRectangle(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Set the color of the rectangle
	 * @param color The color
	 */
	public void setColor(Color color) {
		this.setStroke(color);
	}
}
