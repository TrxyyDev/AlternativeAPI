package fr.trxyy.alternative.alternative_api_ui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherConsole extends TextArea {
	
	/**
	 * The Constructor
	 * @param root The Pane to add
	 */
	public LauncherConsole(Pane root) {
		root.getChildren().add(this);
	}

	/**
	 * Set the size of the console
	 * @param width_ The width
	 * @param height_ The Height
	 */
	public void setSize(int width_, int height_) {
		setPrefSize(width_, height_);
	}
	
	/**
	 * Set invisible
	 */
	public void setInvisible() {
		setBackground(null);
	}
	
	/**
	 * Set position
	 * @param posX The position X
	 * @param posY The position Y
	 */
	public void setPosition(int posX, int posY) {
		setLayoutX(posX);
		setLayoutY(posY);
	}

	/**
	 * Set the Action when clicked
	 * @param value The value
	 */
	public void setAction(EventHandler<? super MouseEvent> value) {
		onMouseClickedProperty().set(value);
	}
	
	/**
	 * Set the Action when hover
	 * @param value The value
	 */
    public final void setHover(EventHandler<? super MouseEvent> value) {
        onMouseEnteredProperty().set(value);
    }
    
	/**
	 * Set the Action when unhover
	 * @param value The value
	 */
    public final void setUnHover(EventHandler<? super MouseEvent> value) {
        onMouseExitedProperty().set(value);
    }
}
