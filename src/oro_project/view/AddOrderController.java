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

	public AddOrderController(){

	}

	@FXML
	public void addOrder(){
		String product = productName.getText();
		Double amount = Double.valueOf(this.amount.getText());
		String client = clients.getText();
		String[] values = client.split(", ");
		Customer cus = new Customer(values[0], values[1],
				values[2], values[3], values[4]);

		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Products where name = " + "'" + product + "'";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		System.out.println("SSS");
		System.out.println(query.list());
		query.addEntity(Product.class);
		@SuppressWarnings("unchecked")
		ArrayList<Product> results = (ArrayList<Product>) query.list();
		System.out.println("SSS_SSS");
		Product p = new Product(results.get(0));
		tx.commit();

		this.order = new Order(p, amount, LocalDate.now(),  cus, this.salesman);

		dialogStage.close();
	}

	@FXML
    public void handleCancel() {
        dialogStage.close();
    }

	public void loadMenuItems(){
		clients.getItems().clear();
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Customers";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Customer.class);
		ArrayList<Customer> results = (ArrayList<Customer>) query.list();

		for(Customer s : results){
			clients.getItems().add(new MenuItem(s.toString()));
		}
		for(MenuItem mi : clients.getItems()){
			mi.setOnAction(e -> {
				clients.setText(mi.getText());
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