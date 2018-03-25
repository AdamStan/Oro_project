package oro_project.view;

import java.io.IOException;
import org.hibernate.Transaction;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import oro_project.MainClass;
import oro_project.classes.Product;

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

	@FXML
	private void showAddProductWindow(){
		try {
			AddProductController controller =
					(AddProductController) mainApp.showAddProductWindow("Product");
			if(controller.getProduct() != null){
				Product p1 = controller.getProduct();
				Transaction tx = mainApp.session.beginTransaction();
				mainApp.session.save(p1);
				tx.commit();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddOrderWindow(){
		try {
			mainApp.showAddWindow("Order");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddCustomerWindow(){
		try {
			mainApp.showAddWindow("Customer");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddSalasmanWindow(){
		try {
			mainApp.showAddWindow("Salesman");
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
