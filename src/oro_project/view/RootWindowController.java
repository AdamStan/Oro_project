package oro_project.view;

import java.io.IOException;
import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import oro_project.MainClass;
import oro_project.classes.*;
import oro_project.view.exceptions.MenuButtonIsNullException;
import oro_project.view.exceptions.ProductNotFoundException;

public class RootWindowController {

	@FXML
	private MenuButton who_are_you;


	private MainClass mainApp;
	private static ArrayList<Salesman> results;
	private static Salesman salesman;

	public RootWindowController(){

	}

	@FXML
	private void exitOnClick(){
		System.exit(0);
	}

	/*
	 * It's loading menu items to who_are_you from database
	 */
	@SuppressWarnings("unchecked")
	public void loadMenuItems(){
		who_are_you.getItems().clear();
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Salesmen";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Salesman.class);
		RootWindowController.results = (ArrayList<Salesman>) query.list();

		for(Salesman s : results){
			who_are_you.getItems().add(new MenuItem(s.toString()));
		}
		for(MenuItem mi : who_are_you.getItems()){
			mi.setOnAction(e -> {
				who_are_you.setText(mi.getText());
				RootWindowController.salesman =
						results.get(who_are_you.getItems().indexOf(mi));
			});
		}
		tx.commit();
	}

	@FXML
	private void showAddProductWindow(){
		try {
			AddProductController controller =
					(AddProductController) mainApp.showAddWindow("Product");
			if(controller.getProduct() != null){
				Product p1 = controller.getProduct();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.save(p1);
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
				Order o1 = controller.getOrder();
				o1.setSalesman(RootWindowController.salesman);
				if(o1.getSalesman() == null){
					throw new MenuButtonIsNullException("You have not chosen salesman");
				}
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.update(o1.getProduct());
				MainClass.session.update(o1.getCustomer());
				MainClass.session.update(o1.getSalesman());
				MainClass.session.save(o1);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MenuButtonIsNullException e){
			e.showMessage();
		}
	}
	@FXML
	private void showAddCustomerWindow(){
		try {
			AddCustomerController controller =
					(AddCustomerController) mainApp.showAddWindow("Customer");
			if(controller.getCustomer() != null){
				Customer c1 = controller.getCustomer();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.saveOrUpdate(c1.getAddress());
				MainClass.session.save(c1);
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
				Salesman s1 = controller.getSalesman();
				Transaction tx = MainClass.session.beginTransaction();
				MainClass.session.saveOrUpdate(s1.getAddress());
				MainClass.session.save(s1);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showOrdersTable(){

	}
	@FXML
	private void showProductsTable(){

	}


	public void setMainApp(MainClass mainApp) {
        this.mainApp = mainApp;
    }
}
