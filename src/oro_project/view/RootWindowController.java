package oro_project.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import oro_project.MainClass;
import oro_project.classes.*;

public class RootWindowController {

	@FXML
	private MenuButton who_are_you;

	private MainClass mainApp;
	private ArrayList<Salesman> results;

	public RootWindowController(){

	}

	@FXML
	private void exitOnClick(){
		System.exit(0);
	}

	/*
	 * It's loading menu items to who_are_you from database
	 */
	public void loadMenuItems(){
		who_are_you.getItems().clear();
		Transaction tx = MainClass.session.beginTransaction();
		String sql_select = "Select * from Salesmans";
		SQLQuery query = MainClass.session.createSQLQuery(sql_select);
		query.addEntity(Salesman.class);
		this.results = (ArrayList<Salesman>) query.list();

		for(Salesman s : results){
			who_are_you.getItems().add(new MenuItem(s.toString()));
		}
		for(MenuItem mi : who_are_you.getItems()){
			mi.setOnAction(e -> {
				who_are_you.setText(mi.getText());
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
				Transaction tx = mainApp.session.beginTransaction();
				mainApp.session.save(o1);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
				MainClass.session.save(c1.getAddress());
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
				MainClass.session.save(s1.getAddress());
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
