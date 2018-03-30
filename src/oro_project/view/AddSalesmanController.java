package oro_project.view;

import java.sql.Date;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.MainClass;
import oro_project.classes.Salesman;

public class AddSalesmanController implements ControllerWindow {
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
	@FXML
	private DatePicker whenStarted;
	@FXML
	private TextField salary;
	@FXML
	private TextField bonus;

	private Salesman salesman;
	private Stage dialogStage;

	public AddSalesmanController(){
		String name = this.name.getText();
		String surname = this.name.getText();
		String street = this.street.getText();
		String numberOfBuildingString = this.numberOfBuilding.getText();
		String city = this.city.getText();
		LocalDate whenStarted = this.whenStarted.getValue();
	}

	@FXML
	private void addSalesman(){

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
