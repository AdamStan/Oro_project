package oro_project.view.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorBox implements Box {
	@Override
	public void showMessage(Exception e, String beforeMessage) {
		Alert alterBox = new Alert(AlertType.ERROR);
		alterBox.setContentText(beforeMessage + e.getMessage());
		alterBox.showAndWait();
	}
}
