package oro_project.view;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import oro_project.MainClass;
import oro_project.classes.*;
import oro_project.view.exceptions.ErrorBox;
import oro_project.view.exceptions.MenuButtonIsNullException;

public class RootWindowController {

	@FXML
	private MenuButton whoAreYou;
	private MainClass mainApp;
	private static ArrayList<Salesman> results;
	private static Salesman salesman;

	public RootWindowController(){

	}

	@FXML
	private void exitOnClick(){
		System.exit(0);
	}
	@SuppressWarnings("unchecked")
	public void loadMenuItems(){
		whoAreYou.getItems().clear();
		Transaction transaction = MainClass.session.beginTransaction();
		String sql_select = "Select * from Salesmen";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Salesman.class);
		RootWindowController.results = (ArrayList<Salesman>) query.list();

		for(Salesman salesman : results){
			whoAreYou.getItems().add(new MenuItem(salesman.toString()));
		}

		for(MenuItem menuItem : whoAreYou.getItems()){
			menuItem.setOnAction(e -> {
				whoAreYou.setText(menuItem.getText());
				RootWindowController.salesman =
						results.get(whoAreYou.getItems().indexOf(menuItem));
			});
		}
		transaction.commit();
	}

	@FXML
	private void showAddProductWindow(){
		try {
			AddProductController controller =
					(AddProductController) mainApp.showAddWindow("Product");
			if(controller.getProduct() != null){
				Product product = controller.getProduct();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.save(product);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddOrderWindow(){
		try {
			AddOrderController controller =
					(AddOrderController) mainApp.showAddWindow("Order");
			if(controller.getOrder() != null){
				Order order = controller.getOrder();
				order.setSalesman(RootWindowController.salesman);
				if(order.getSalesman() == null){
					throw new MenuButtonIsNullException("You have not chosen salesman");
				}
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.update(order.getProduct());
				MainClass.session.update(order.getCustomer());
				MainClass.session.update(order.getSalesman());
				MainClass.session.save(order);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MenuButtonIsNullException e){
			new ErrorBox().showMessage(e, "");
		}
	}
	@FXML
	private void showAddCustomerWindow(){
		try {
			AddCustomerController controller =
					(AddCustomerController) mainApp.showAddWindow("Customer");
			if(controller.getCustomer() != null){
				Customer customer = controller.getCustomer();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.saveOrUpdate(customer.getAddress());
				MainClass.session.save(customer);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddSalasmanWindow(){
		try {
			AddSalesmanController controller =
					(AddSalesmanController) mainApp.showAddWindow("Salesman");
			if(controller.getSalesman() != null){
				Salesman salesman = controller.getSalesman();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.saveOrUpdate(salesman.getAddress());
				MainClass.session.save(salesman);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showOrdersTable(){
		mainApp.showOrders();
	}
	@FXML
	private void showProductsTable(){
		mainApp.showProducts();
	}
	public void setMainApp(MainClass mainApp) {
        this.mainApp = mainApp;
    }
}
