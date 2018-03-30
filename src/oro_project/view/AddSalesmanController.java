package oro_project.view;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.classes.Address;
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

	}

	@FXML
	private void addSalesman(){
		String name = this.name.getText();
		String surname = this.surname.getText();
		String street = this.street.getText();
		String numberOfBuilding = this.numberOfBuilding.getText();
		String city = this.city.getText();
		LocalDate whenStarted = this.whenStarted.getValue();
		Double salary = Double.valueOf(this.salary.getText());
		Double bonus = Double.valueOf(this.bonus.getText());
		this.salesman = new Salesman(name,surname,whenStarted,
				new Address(numberOfBuilding, street, city), salary, bonus);
		dialogStage.close();
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
