package oro_project;

import java.io.IOException;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oro_project.classes.*;
import javafx.scene.layout.AnchorPane;
import oro_project.view.AddOrderController;
import oro_project.view.ControllerWindow;
import oro_project.view.RootWindowController;
import oro_project.view.ShowProductsController;
import oro_project.view.exceptions.ProductNotFoundException;


public class MainClass extends Application{
	public static Session session;
	public static BorderPane rootLayout;
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainClass.class.getResource("view/RootLayout.fxml"));
        try {
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			RootWindowController controller = loader.getController();
	        controller.setMainApp(this);
	        controller.loadMenuItems();
	        primaryStage.setScene(scene);
            primaryStage.setTitle("SHOP");
            primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object showAddWindow(String name) throws IOException{
		Object controller = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainClass.class.getResource("view/Add" + name + ".fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Add " + name);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);
		controller =  loader.getController();
		((ControllerWindow) controller).setDialogStage(dialogStage);
		if(controller.getClass() == AddOrderController.class){
			((AddOrderController) controller).loadMenuItems();
		}
		dialogStage.showAndWait();
		return controller;
	}

	public static void connectWithDatabase(){
		SessionFactory factory = HibernateUtil.buildSessionFactory();
        session = factory.openSession();
	}
	public static void onFirstUse(){
		Transaction tx = session.beginTransaction();
		Product product = new Product("Pepsi", 30, 3.99);
		Address address = new Address("123","Aleja Politechniki","Lodz");
		Salesman salesman = new Salesman("Jan","Kowalski", LocalDate.now(), address, 3500.50, 550.0);
		Customer client = new Customer("Anna","Nowak", address);
		Order order = new Order(product, 20.0, LocalDate.now(), client, salesman);
		session.save(address);
		session.save(product);
		session.save(salesman);
		session.save(client);
		session.save(order);
		tx.commit();
	}
	public void showProducts() {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainClass.class.getResource("view/ShowProducts.fxml"));
	        BorderPane showProduct = (BorderPane) loader.load();
	        rootLayout.setCenter(showProduct);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void showOrders() {
		try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainClass.class.getResource("view/ShowOrders.fxml"));
	        BorderPane showOrder = (BorderPane) loader.load();
	        rootLayout.setCenter(showOrder);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		connectWithDatabase();
		//onFirstUse();
		launch(args);
	}
}
