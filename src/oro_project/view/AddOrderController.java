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
import oro_project.view.exceptions.CustomerIsNullException;
import oro_project.view.exceptions.ProductNotFoundException;

public class AddOrderController implements ControllerWindow {

	@FXML
	private TextField productName;
	@FXML
	private TextField amount;
	@FXML
	private MenuButton clients;

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
			String product = productName.getText();
			Double amount = Double.valueOf(this.amount.getText());
			Transaction tx = MainClass.session.beginTransaction();
			String sql_select = "Select * from Products where name = " + "'" + product + "'";
			SQLQuery query = MainClass.session.createSQLQuery(sql_select);

			query.addEntity(Product.class);
			@SuppressWarnings("unchecked")
			ArrayList<Product> results = (ArrayList<Product>) query.list();
			tx.commit();
			Product p = null;

			try{
				p = results.get(0);
			} catch(IndexOutOfBoundsException e){
				throw new ProductNotFoundException(e.getMessage(), product);
			}
			if(this.client == null){
				throw new CustomerIsNullException("You have not chosen Client");
			}

			this.order = new Order(p, amount, LocalDate.now(),
					this.client, this.salesman);

			dialogStage.close();
		} catch (ProductNotFoundException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Wrong value: " + e.toString());
			a.showAndWait();
		} catch(NumberFormatException | CustomerIsNullException  e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.showAndWait();
		}
	}

	@FXML
    public void handleCancel() {
        dialogStage.close();
    }

	@SuppressWarnings("unchecked")
	public void loadMenuItems(){
		clients.getItems().clear();
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Customers";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Customer.class);
		AddOrderController.customers = (ArrayList<Customer>) query.list();

		for(Customer s : customers){
			clients.getItems().add(new MenuItem(s.toString()));
		}
		for(MenuItem mi : clients.getItems()){
			mi.setOnAction(e -> {
				clients.setText(mi.getText());
				this.client =
						customers.get(clients.getItems().indexOf(mi));
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
		this.clients = c;
	}

	public void setSalesman(Salesman s){
		this.salesman = s;
	}

}