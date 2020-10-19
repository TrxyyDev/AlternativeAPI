package fr.trxyy.alternative.alternative_api_ui.components;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherPasswordField extends PasswordField {

	/**
	 * The Constructor
	 * @param root The Pane to add the field
	 */
	public LauncherPasswordField(Pane root) {
		this.setSize(100, 30);
		this.setPosition(0, 0);
		root.getChildren().add(this);
	}

	/**
	 * The Constructor
	 * @param w The width
	 * @param h The height
	 * @param pX The position X
	 * @param pY The position Y
	 * @param root The Pane to add the field
	 */
	public LauncherPasswordField(int w, int h, int pX, int pY, Pane root) {
		this.setSize(w, h);
		this.setPosition(pX, pY);
		root.getChildren().add(this);
	}

	/**
	 * The Constructor
	 * @param s The text by default
	 * @param w The width
	 * @param h The height
	 * @param pX The position X
	 * @param pY The position Y
	 * @param root The Pane to add the field
	 */
	public LauncherPasswordField(String s, int w, int h, int pX, int pY, Pane root) {
		this.setText(s);
		this.setSize(w, h);
		this.setPosition(pX, pY);
		root.getChildren().add(this);
	}

	/**
	 * Set the size of the field
	 * @param width_ The width
	 * @param height_ The height
	 */
	public void setSize(int width_, int height_) {
		this.setPrefSize(width_, height_);
	}

	/**
	 * Set the position of the field
	 * @param posX The position X
	 * @param posY The position Y
	 */
	public void setPosition(int posX, int posY) {
		this.setLayoutX(posX);
		this.setLayoutY(posY);
	}
	
	/**
	 * Set the text to display when field is void
	 * @param s The text to display
	 */
	public void setVoidText(String s) {
		this.setPromptText(s);
	}

}
