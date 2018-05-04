package oro_project.view;

import java.util.ArrayList;

import org.hibernate.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import oro_project.MainClass;
import oro_project.classes.Order;
import oro_project.classes.Product;

public class ShowOrdersController {
	@FXML
	private TableView<Order> table;
	@FXML
	private TableColumn<Order, String> idColumn;
	@FXML
	private TableColumn<Order, String> dateColumn;
	@FXML
	private TableColumn<Order, String> productNameColumn;
	@FXML
	private TableColumn<Order, String> clientNameColumn;
	@FXML
	private TableColumn<Order, String> salesmanNameColumn;

	public ShowOrdersController(){

	}

	@FXML
	private void initialize(){
		idColumn.setCellValueFactory(cellData
				-> cellData.getValue().idProperty());
        dateColumn.setCellValueFactory(cellData
        		-> cellData.getValue().dateProperty());
        productNameColumn.setCellValueFactory(cellData
        		-> cellData.getValue().productNameProperty());
        clientNameColumn.setCellValueFactory(cellData
        		-> cellData.getValue().clientNameProperty());
        salesmanNameColumn.setCellValueFactory(cellData
        		-> cellData.getValue().salesmanNameProperty());

        String command = "From Order";
		Query query = MainClass.session.createQuery(command);
		ArrayList<Order> results = (ArrayList<Order>) query.list();
		ObservableList<Order> orderData = FXCollections.observableArrayList();

		for(Order p : results){
			orderData.add(p);
		}

		table.setItems(orderData);
	}

}
