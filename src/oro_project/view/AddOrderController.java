package oro_project.view;

import java.time.LocalDate;
import java.util.ArrayList;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.MainClass;
import oro_project.classes.*;

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

	private static Customer client;
	private static ArrayList<Customer> customers;

	public AddOrderController(){

	}

	@FXML
	public void addOrder(){
		String product = productName.getText();
		Double amount = Double.valueOf(this.amount.getText());
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Products where name = " + "'" + product + "'";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);

		query.addEntity(Product.class);
		@SuppressWarnings("unchecked")
		ArrayList<Product> results = (ArrayList<Product>) query.list();

		Product p = results.get(0);
		tx.commit();

		this.order = new Order(p, amount, LocalDate.now(),
				AddOrderController.client, this.salesman);

		dialogStage.close();
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
				AddOrderController.client =
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