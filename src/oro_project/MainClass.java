package oro_project;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oro_project.classes.*;
import javafx.scene.layout.AnchorPane;
import oro_project.view.AddProductController;
import oro_project.view.ControllerWindow;
import oro_project.view.RootWindowController;
/* Functionalities
 * 1. Add new custumer
 * 2. Add new order
 * 3. Add new product
 * 4. Add new salesman
 * 5. Delete for all
 * */

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
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClass.class.getResource("view/Add" + name + ".fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add " + name);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        Object controller =  loader.getController();
        ((ControllerWindow) controller).setDialogStage(dialogStage);
        dialogStage.showAndWait();
        return controller;
	}

	public static void connect_with_database(){
		SessionFactory factory = HibernateUtil.buildSessionFactory();
        session = factory.openSession();
	}

	public static void main(String[] args) {
		connect_with_database();
		Transaction tx = session.beginTransaction();
		Product p1 = new Product("Pepsi", 30, 3.99);
		Address a1 = new Address("123","Aleja Politechniki","Lodz");
		Salesman s1 = new Salesman("Jan","Kowalski", LocalDate.now(), a1, 3500.50, 550.0);
		Customer c1 = new Customer("Anna","Nowak", a1);
		Order o1 = new Order(p1, 20, LocalDate.now(), c1, s1);
		session.save(a1);
		session.save(p1);
		session.save(s1);
		session.save(c1);
		session.save(o1);
		tx.commit();
		launch(args);
	}

}
