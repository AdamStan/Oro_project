package oro_project;

import java.io.IOException;
import java.util.Date;

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

            primaryStage.setScene(scene);
            primaryStage.setTitle("SHOP");
            primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showAddProductWindow() throws IOException{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainClass.class.getResource("view/AddProduct.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Product");
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        /*PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);*/

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
	}
	public static void connect_with_database(){
		SessionFactory factory = HibernateUtil.buildSessionFactory();
        session = factory.openSession();
	}

	public static void main(String[] args) {
		connect_with_database();
		Transaction tx = session.beginTransaction();
		Product p1 = new Product("Pepsi", 30, 3.99);
		Address a1 = new Address(123,"Aleja Politechniki","Lodz");
		Salesman s1 = new Salesman("Jan","Kowalski",new Date(), a1, 3500.50);
		Customer c1 = new Customer("Anna","Nowak", a1);
		Order o1 = new Order(p1, 20, new Date(), c1, s1);
		//p1.setId(1);
		session.save(a1);
		session.save(p1);
		session.save(s1);
		session.save(c1);
		session.save(o1);
		tx.commit();
		launch(args);
	}

}
