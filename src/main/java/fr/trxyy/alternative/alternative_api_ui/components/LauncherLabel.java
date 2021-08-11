package fr.trxyy.alternative.alternative_api_ui.components;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherLabel extends Label {

	/**
	 * The Constructor
	 * @param root The Pane to add the label
	 */
	public LauncherLabel(Pane root) {
		root.getChildren().add(this);
	}

	/**
	 * Set the label size
	 * @param width_ The width 
	 * @param height_ The height
	 */
	public void setSize(int width_, int height_) {
		this.setPrefSize(width_, height_);
	}
	
	/**
	 * Set the label invisible
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
	 * Set the bounds (double)
	 * @param posX The position X (double)
	 * @param posY The position Y (double)
	 */
	public void setBounds(double posX, double posY, int width_, int height_) {
		this.setLayoutX(posX);
		this.setLayoutY(posY);
		this.setPrefSize(width_, height_);
	}
	
	/**
	 * Set the bounds (int)
	 * @param posX The position X (int)
	 * @param posY The position Y (int)
	 */
	public void setBounds(int posX, int posY, int width_, int height_) {
		this.setLayoutX(posX);
		this.setLayoutY(posY);
		this.setPrefSize(width_, height_);
	}

	/**
	 * Set the Action when clicked
	 * @param value The value
	 */
	public void setAction(EventHandler<? super MouseEvent> value) {
		this.onMouseClickedProperty().set(value);
	}
	
	/**
	 * Set the Action when hover
	 * @param value The value
	 */
    public final void setHover(EventHandler<? super MouseEvent> value) {
    	this.onMouseEnteredProperty().set(value);
    }
    
	/**
	 * Set the Action when unhover
	 * @param value The value
	 */
    public final void setUnHover(EventHandler<? super MouseEvent> value) {
    	this.onMouseExitedProperty().set(value);
    }
    
	/**
	 * Set the Style
	 * @param value The value
	 */
    public final void addStyle(String value) {
    	String finalValue = this.getStyle() + value;
        styleProperty().set(finalValue);
    }
}
