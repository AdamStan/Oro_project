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

/*
 * Do zrobienia:
 * 2. Pokazanie Produktów
 * 3. Pokazanie Zamówieñ
 * 4. ZnajdŸ produkt, zamówienie, klienta
 */
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainClass.class.getResource("view/RootLayout.fxml"));
        try {
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);

			RootWindowController controller = loader.getController();
	        controller.setMainApp(this);
	        //function for loading salesman from database
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

	public static void connect_with_database(){
		SessionFactory factory = HibernateUtil.buildSessionFactory();
        session = factory.openSession();
	}
	public static void on_first_use(){
		Transaction tx = session.beginTransaction();
		Product p1 = new Product("Pepsi", 30, 3.99);
		Address a1 = new Address("123","Aleja Politechniki","Lodz");
		Salesman s1 = new Salesman("Jan","Kowalski", LocalDate.now(), a1, 3500.50, 550.0);
		Customer c1 = new Customer("Anna","Nowak", a1);
		Order o1 = new Order(p1, 20.0, LocalDate.now(), c1, s1);
		session.save(a1);
		session.save(p1);
		session.save(s1);
		session.save(c1);
		session.save(o1);
		tx.commit();
	}
	public void showProducts() {
	    try {
	        // Load person overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainClass.class.getResource("view/ShowProducts.fxml"));
	        BorderPane showProduct = (BorderPane) loader.load();

	        // Set person overview into the center of root layout.
	        rootLayout.setCenter(showProduct);

	        // Give the controller access to the main app.
	        ShowProductsController controller = loader.getController();
	        controller.setMainApp(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
		connect_with_database();
		//on_first_use();
		launch(args);
	}

}
