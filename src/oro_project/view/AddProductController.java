package oro_project.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import oro_project.classes.Product;

public class AddProductController {
	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextField price;

	private Stage dialogStage;
	private Product product;
    private boolean okClicked = false;

	public AddProductController(){

	}

	@FXML
	private void addProduct(){
		String name = this.name.getText();
		Integer amount = Integer.valueOf(this.amount.getText());
		Double price = Double.valueOf(this.price.getText());
		product = new Product(name, amount, price);
		this.okClicked = true;
		dialogStage.close();
	}

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

	public Product getProduct(){
		return this.product;
	}
	public void setDialogStage(Stage st){
		this.dialogStage = st;
	}
}
