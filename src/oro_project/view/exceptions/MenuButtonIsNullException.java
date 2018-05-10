package oro_project.view.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuButtonIsNullException extends Exception implements AlertBox{

	private static final long serialVersionUID = -7919581598565907909L;

	public MenuButtonIsNullException(String message){
		super(message);
	}
	public void showMessage(){
		Alert alertBox = new Alert(AlertType.ERROR);
		alertBox.setContentText(this.toString());
		alertBox.showAndWait();
	}
}
