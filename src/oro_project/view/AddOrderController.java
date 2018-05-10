package oro_project.view;

import java.time.LocalDate;
import java.util.ArrayList;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import oro_project.MainClass;
import oro_project.classes.*;
import oro_project.view.exceptions.ErrorBox;
import oro_project.view.exceptions.MenuButtonIsNullException;
import oro_project.view.exceptions.ProductNotFoundException;

public class AddOrderController implements ControllerWindow {

	@FXML
	private TextField productName;
	@FXML
	private TextField amount;
	@FXML
	private MenuButton menuButtonClients;

	private Stage dialogStage;
	private Order order;
	private Salesman salesman;

	private Customer client;
	private static ArrayList<Customer> customers;

	public AddOrderController(){

	}

	@FXML
	public void addOrder(){
		try{
			String productNameLocal = productName.getText();
			Double amount = Double.valueOf(this.amount.getText());
			Transaction tx = MainClass.session.beginTransaction();
			String sql_select = "Select * from Products where name = "
					+ "'" + productNameLocal + "'";
			SQLQuery query = MainClass.session.createSQLQuery(sql_select);
			query.addEntity(Product.class);
			@SuppressWarnings("unchecked")
			ArrayList<Product> results = (ArrayList<Product>) query.list();
			tx.commit();
			Product product = null;
			try{
				product = results.get(0);
			} catch(IndexOutOfBoundsException e){
				throw new ProductNotFoundException(e.getMessage(), productNameLocal);
			}
			if(this.client == null){
				throw new MenuButtonIsNullException("You have not chosen Client");
			}
			this.order = new Order(product, amount, LocalDate.now(),
					this.client, this.salesman);
			dialogStage.close();
		} catch (ProductNotFoundException e) {
			new ErrorBox().showMessage(e, "Wrong value: ");
		} catch(NumberFormatException | MenuButtonIsNullException  e) {
			new ErrorBox().showMessage(e, "");
		}
	}

	@FXML
    public void handleCancel() {
        dialogStage.close();
    }

	@SuppressWarnings("unchecked")
	public void loadMenuItems(){
		menuButtonClients.getItems().clear();
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Customers";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Customer.class);
		AddOrderController.customers = (ArrayList<Customer>) query.list();
		for(Customer s : customers){
			menuButtonClients.getItems().add(new MenuItem(s.toString()));
		}
		for(MenuItem mi : menuButtonClients.getItems()){
			mi.setOnAction(e -> {
				menuButtonClients.setText(mi.getText());
				this.client =
					customers.get(menuButtonClients.getItems().indexOf(mi));
			});
		}
		tx.commit();
	}

	@Override
	public void setDialogStage(Stage mainWindow) {
		this.dialogStage = mainWindow;
	}

	public Order getOrder(){
		return this.order;
	}

	public void setClients(MenuButton c){
		this.menuButtonClients = c;
	}

	public void setSalesman(Salesman s){
		this.salesman = s;
	}

}