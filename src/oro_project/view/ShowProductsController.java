package oro_project.view;

import java.util.ArrayList;
import org.hibernate.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import oro_project.MainClass;
import oro_project.classes.Product;

public class ShowProductsController {
	@FXML
	private TableView<Product> table;
	@FXML
	private TableColumn<Product, String> idColumn;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, String> priceColumn;
	@FXML
	private TableColumn<Product, String> amountColumn;

	private MainClass mainApp;

	@FXML
	private void initialize(){
		idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());

		String command = "From Product";
		Query query = MainClass.session.createQuery(command);
		ArrayList<Product> results = (ArrayList<Product>) query.list();
		ObservableList<Product> productData = FXCollections.observableArrayList();

		for(Product p : results){
			productData.add(p);
		}

		table.setItems(productData);
	}

	public ShowProductsController(){

	}

	public void setMainApp(MainClass mainClass) {
		this.mainApp = mainClass;
	}

}
