package fr.trxyy.alternative.alternative_api_uiv2.components;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Trxyy
 */
public class LauncherButton extends Button {

	/**
	 * The Constructor
	 * @param root The Pane to add the button
	 */
	public LauncherButton(Pane root) {
		this.setUnHover(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				setOpacity(1.0);
			}
		});
		this.setHover(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				setOpacity(0.80);
			}
		});
		root.getChildren().add(this);
	}
	
	/**
	 * The Constructor
	 * @param root The Pane to add the button
	 */
	public LauncherButton(String text, Pane root) {
		this.setText(text);
		this.setUnHover(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				setOpacity(1.0);
			}
		});
		this.setHover(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				setOpacity(0.80);
			}
		});
		root.getChildren().add(this);
	}

	/**
	 * Set the size of the button
	 * @param width_ The width
	 * @param height_ The height
	 */
	public void setSize(int width_, int height_) {
		this.setPrefSize(width_, height_);
	}

	/**
	 * Set the button invisible
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
	 * Set the position
	 * @param posX The position X
	 * @param posY The position Y
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
