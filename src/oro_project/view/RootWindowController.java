package oro_project.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import oro_project.MainClass;

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
			mainApp.showAddProductWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddOrderWindow(){
		try {
			mainApp.showAddOrderWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddCustomerWindow(){
		try {
			mainApp.showAddCustomerWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	private void showAddSalasmanWindow(){
		try {
			mainApp.showAddSalesmanWindow();
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
