package oro_project.view;

import java.io.IOException;
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
			AddOrderController controller =
					(AddOrderController) mainApp.showAddWindow("Customer");
			if(controller.getCustomer() != null){
				Order o1 = controller.getCustomer();
				Transaction tx = mainApp.session.beginTransaction();
				mainApp.session.save(o1);
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
				Salesman o1 = controller.getSalesman();
				Transaction tx = mainApp.session.beginTransaction();
				mainApp.session.save(o1);
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
