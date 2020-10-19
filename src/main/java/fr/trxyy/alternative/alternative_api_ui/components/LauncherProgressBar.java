package fr.trxyy.alternative.alternative_api_ui.components;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherProgressBar extends ProgressBar {
	
	/**
	 * The current file name downloading
	 */
	public String currentFile;
	
	/**
	 * The Constructor
	 */
	public LauncherProgressBar() {}
	
	/**
	 * The COnstructor
	 * @param contentPane The Pane to add the progress bar
	 */
	public LauncherProgressBar(Pane contentPane) {
		contentPane.getChildren().add(this);
	}
	
	/**
	 * Set the size of the Progress bar
	 * @param width_ The width
	 * @param height_ The height
	 */
	public void setSize(int width_, int height_) {
		this.setPrefSize(width_, height_);
		this.setWidth(width_);
		this.setHeight(height_);
	}
	
	/**
	 * Set the size of the Progress bar (double)
	 * @param width_ The width
	 * @param height_ The height
	 */
	public void setSize(double width_, double height_) {
		this.setPrefSize(width_, height_);
		this.setWidth(width_);
		this.setHeight(height_);
	}
	
	/**
	 * Set the progress bar invisible
	 */
	public void setInvisible() {
		this.setBackground(null);
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
	
	/**
	 * Set the position (double)
	 * @param posX The position X (double)
	 * @param posY The position Y (double)
	 */
	public void setPosition(double posX, double posY) {
		this.setLayoutX(posX);
		this.setLayoutY(posY);
	}
	/**
	 * Set the current file name
	 * @param file The file name
	 */
	public void setCurrentFile(String file) {
		this.currentFile = file;
	}
	
	/**
	 * @return The current file name
	 */
	public String getCurrentFile() {
		return this.currentFile;
	}
	
}
