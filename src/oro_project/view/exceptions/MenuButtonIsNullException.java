package oro_project.view.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuButtonIsNullException extends Exception implements AlertBox{

	private static final long serialVersionUID = -7919581598565907909L;

	public MenuButtonIsNullException(String message){
		super(message);
	}
	public void showMessage(){
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(this.toString());
		a.showAndWait();
	}
}
