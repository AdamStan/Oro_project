package oro_project.view;

import org.hibernate.SQLQuery;
import org.hibernate.Transaction;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import oro_project.MainClass;
import oro_project.classes.Salesman;
import oro_project.view.exceptions.ErrorBox;

public class LoginWindowController {
	@FXML
	private TextField username;
	@FXML
	private PasswordField passwordField;

	private MainClass mainApp;
	public static Salesman salesman;

	public LoginWindowController(){

	}
	@FXML
	private void login(){
		String password = passwordField.getText();
		//paramerts are : Id, name, surname
		String[] parametrs = divideUsername(username.getText());
		Transaction transaction = MainClass.session.beginTransaction();
		try{
			String select = "Select * From Salesmen Where "
					+ " id = " + parametrs[0] + " AND "
					+ " name = '" + parametrs[1] + "' AND "
					+ " surname = '" + parametrs[2] + "' AND "
					+ " password = '" + password + "' ;";
			SQLQuery query = MainClass.session.createSQLQuery(select);
			query.addEntity(Salesman.class);
			System.out.println(query.list().get(0));
			LoginWindowController.salesman = (Salesman) query.list().get(0);
			transaction.commit();
			this.mainApp.showMainManu();
		}
		catch(Exception e){
			transaction.rollback();
			new ErrorBox().showMessage(e, "We can't login to database because: ");
		}
	}
	@FXML
	private void exit(){
		System.exit(0);
	}
	private static String[] divideUsername(String usernameField){
		String[] fields = new String[3];
		fields = usernameField.split(" ");
		return fields;
	}
	public void setMainApp(MainClass mainApp) {
		this.mainApp = mainApp;
	}
}
