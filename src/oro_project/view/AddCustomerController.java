package oro_project.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.MainClass;
import oro_project.classes.Salesman;

public class AddCustomerController implements ControllerWindow{
	@FXML
	private TextField name;
	@FXML
	private TextField surname;
	@FXML
	private TextField street;
	@FXML
	private TextField numberOfBuilding;
	@FXML
	private TextField city;

	private Salesman salesman;
	private MainClass mainApp;
	private Stage dialogStage;

	public AddSalesmanController(){

	}

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

	@Override
	public void setDialogStage(Stage mainWindow) {
		this.dialogStage = mainWindow;
	}

	public Salesman getSalesman(){
		return this.salesman;
	}
}
