package oro_project.view;

import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.MainClass;
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

		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Addresses where"
				+ " numberOfBuilding = '" + number + "' AND"
				+ " street = '" + street + "' AND"
				+ " city = '" + city + "';";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);

		query.addEntity(Address.class);
		@SuppressWarnings("unchecked")
		ArrayList<Address> results = (ArrayList<Address>) query.list();
		Address address = null;
		if(!results.isEmpty()){
			System.out.println("Mamy adres w bazie");
			address = results.get(0);
		}
		tx.commit();
		if(address == null){
			address = new Address(number,street,city);
		}
		this.customer = new Customer(name,surname, address);
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
