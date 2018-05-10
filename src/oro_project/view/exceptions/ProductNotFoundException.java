package oro_project.view.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProductNotFoundException extends Exception implements AlertBox {

	private static final long serialVersionUID = -5539083891283103280L;
	private String nameOfProduct;

	public ProductNotFoundException(String message, String name){
		super(message);
		this.nameOfProduct = name;
	}

	@Override
	public String toString() {
		return "ProductNotFoundException nameOfProduct = " + nameOfProduct;
	}

	@Override
	public void showMessage() {
		Alert alertBox = new Alert(AlertType.ERROR);
		alertBox.setContentText(this.toString());
		alertBox.showAndWait();
	}

}
