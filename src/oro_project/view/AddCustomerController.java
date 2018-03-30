package oro_project.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.classes.Address;
import oro_project.classes.Customer;

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

	private Customer customer;
	private Stage dialogStage;

	public AddCustomerController(){

	}

	@FXML
	public void addCustomer(){
		String name = this.name.getText();
		String surname = this.surname.getText();
		String number = this.numberOfBuilding.getText();
		String street = this.street.getText();
		String city = this.city.getText();
		this.customer = new Customer(name,surname, new Address(number,street,city));
		dialogStage.close();
	}

	@FXML
    public void handleCancel() {
        dialogStage.close();
    }

	@Override
	public void setDialogStage(Stage mainWindow) {
		this.dialogStage = mainWindow;
	}

	public Customer getCustomer(){
		return this.customer;
	}
}
