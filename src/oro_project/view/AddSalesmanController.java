package oro_project.view;

import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oro_project.MainClass;
import oro_project.classes.Address;
import oro_project.classes.Salesman;
import oro_project.view.exceptions.ErrorBox;

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
		try{
			String name = this.name.getText();
			String surname = this.surname.getText();
			String street = this.street.getText();
			String numberOfBuilding = this.numberOfBuilding.getText();
			String city = this.city.getText();
			LocalDate whenStarted = this.whenStarted.getValue();
			Double salary = Double.valueOf(this.salary.getText());
			Double bonus = Double.valueOf(this.bonus.getText());

			Transaction tx = MainClass.session.beginTransaction();
			String sql_select = "Select * from Addresses where"
					+ " numberOfBuilding = '" + numberOfBuilding + "' AND"
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
				address = new Address(numberOfBuilding,street,city);
			}

			this.salesman = new Salesman(name,surname,whenStarted,
					address, salary, bonus);
			this.salesman.setPassword("12345");
			dialogStage.close();
		} catch (NumberFormatException e){
			new ErrorBox().showMessage(e, "Wrong value: ");
		}
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
