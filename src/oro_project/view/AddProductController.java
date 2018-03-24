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

	}

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
