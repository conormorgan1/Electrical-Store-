/*
 * Conor Morgan
 * R00139200
 * OOP Final Project 
 */
package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.CustomerGUI;
import view.OrderDetailsGUI;
import view.OrderGUI;
import view.PhoneGUI;
import view.TVGUI;
public class Main extends Application {
	
	static TabPane tp = new TabPane(); 
	public static void main(String[] args) throws SQLException {

		launch(args);	

	}

	public void start(Stage primaryStage) throws Exception {

		try {
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,700,500);

			PhoneGUI phone = new PhoneGUI();
			tp.getTabs().add(phone);
			
			TVGUI tv = new TVGUI();
			tp.getTabs().add(tv);
			
			OrderGUI order = new OrderGUI();
			tp.getTabs().add(order);
			
			OrderDetailsGUI od = new OrderDetailsGUI();
			tp.getTabs().add(od);

			CustomerGUI customer = new CustomerGUI();
			tp.getTabs().add(customer);
			
			mainPane.setCenter(tp);

			mainPane.prefHeightProperty().bind(scene.heightProperty());
			mainPane.prefWidthProperty().bind(scene.widthProperty());

			root.getChildren().add(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}	