package fr.trxyy.alternative.alternative_api_ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author Trxyy
 */
public class LauncherAlert {

	/**
	 * The Constructor
	 * @param text The message to display
	 * @param type The Type of the Alert
	 */
	public LauncherAlert(String text, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Error/Erreur");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
	}

	/**
	 * The Constructor
	 * @param title The title of the Alert
	 * @param text The message to display
	 */
	public LauncherAlert(String title, String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erreur");
		alert.setHeaderText("Argh! Une erreur est survenue.");
		alert.setContentText(title);

		Label label = new Label("L'exception est la suivante:");
		TextArea textArea = new TextArea(text);
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);
		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
}
